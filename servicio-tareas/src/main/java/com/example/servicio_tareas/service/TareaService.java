package com.example.servicio_tareas.service;

import com.example.servicio_tareas.entity.TareaEstado;
import com.example.servicio_tareas.entity.Tareas;

import java.util.List;

public interface TareaService {
    Tareas createTareas(Tareas tareas, String requesterRole)throws Exception;
    Tareas getTareasById(Long id)throws Exception;
    List<Tareas> getAllTareas(TareaEstado estado);
    Tareas updateTareas(Long id, Tareas updatedTareas, Long userId)throws Exception;
    void deleteTareas(Long id) throws Exception;
    Tareas assignedToUser(Long userId, Long TareasId)throws  Exception;
    List<Tareas>assignedUsersTareas(Long userId, TareaEstado estado);
    Tareas completeTareas(Long tareasId) throws Exception;
}
