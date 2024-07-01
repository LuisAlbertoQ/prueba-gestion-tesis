package com.example.msdocumento.service;

import com.example.msdocumento.entity.Documento;
import com.example.msdocumento.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    private final String uploadDir = "uploads/";

    public Documento saveFile(MultipartFile file, boolean replace) throws IOException {
        // Verificar si el archivo ya existe
        String fileName = file.getOriginalFilename();
        Optional<Documento> existingDocumentOpt = documentoRepository.findByNombreDocumento(fileName);
        Documento documento;

        if (existingDocumentOpt.isPresent()) {
            if (!replace){
                throw new IllegalArgumentException("El archivo ya existe en la base de datos. Usa 'replace=true' para reemplazarlo");
            }else {
                documento = existingDocumentOpt.get();
                Files.deleteIfExists(Paths.get(documento.getRutaArchivo()));
            }
        }else{
            documento = new Documento();
        }

        // Crear el directorio de carga si no existe
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        documento.setNombreDocumento(fileName);
        documento.setRutaArchivo(filePath.toString());
        documento.setTipoArchivo(file.getContentType());

        return documentoRepository.save(documento);
    }

    public Documento tomarDocumento(Long idDocumento) {
        return documentoRepository.findById(idDocumento).orElse(null);
    }

    public List<Documento> listardocumento() {
        return documentoRepository.findAll();
    }
}
