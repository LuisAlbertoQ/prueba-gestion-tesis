package com.example.servicio_tareas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
