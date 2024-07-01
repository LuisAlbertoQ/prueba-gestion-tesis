package com.example.servicio_tareas.repository;

import com.example.servicio_tareas.entity.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Tareasrepository extends JpaRepository<Tareas, Long> {

    public List<Tareas> findByAsignacionUsuarioId(Long userId);
}
