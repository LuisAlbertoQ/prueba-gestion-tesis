package com.example.enviodetareasservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TareasDto {

    private Long id;
    private String title;
    private String descripcion;
    private String image;
    private Long asignacionUsuarioId;
    private List<String> tags=new ArrayList<>();
    private TareaEstado status;
    private LocalDateTime FechaLimite;
    private LocalDateTime creadoAt;

}
