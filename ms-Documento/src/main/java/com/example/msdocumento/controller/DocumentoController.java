package com.example.msdocumento.controller;

import com.example.msdocumento.entity.Documento;
import com.example.msdocumento.entity.UserDto;
import com.example.msdocumento.service.DocumentoService;
import com.example.msdocumento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/doctesis")
public class DocumentoController {

    @Autowired
    public DocumentoService documentoService;

    @Autowired
    public UserService userService;

    @PostMapping("/subir")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "replace", defaultValue = "false") boolean replace,
                                             @RequestHeader("Authorization") String jwt) {
        try {
            UserDto user = userService.getUserProfile(jwt);
            documentoService.saveFile(file, replace);
            return ResponseEntity.status(HttpStatus.OK).body("Archivo Subido Exitosamente! :D");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en Subir Archivo :(");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/descargar/{idDocumento}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long idDocumento,
                                               @RequestHeader("Authorization") String jwt) {
        try {
            UserDto user = userService.getUserProfile(jwt);
            Documento documento = documentoService.tomarDocumento(idDocumento);
            if (documento != null) {
                File file = new File(documento.getRutaArchivo());
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] data = fileInputStream.readAllBytes();
                fileInputStream.close();

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.getNombreDocumento() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, documento.getTipoArchivo())
                        .body(data);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Documento>> listDocuments(@RequestHeader("Authorization") String jwt) {
        try {
            UserDto user = userService.getUserProfile(jwt);
            List<Documento> documentos = documentoService.listardocumento();
            return ResponseEntity.ok().body(documentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}