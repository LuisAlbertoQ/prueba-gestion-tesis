package com.example.servicio_tareas.service.impl;

import com.example.servicio_tareas.entity.TareaEstado;
import com.example.servicio_tareas.entity.Tareas;
import com.example.servicio_tareas.repository.Tareasrepository;
import com.example.servicio_tareas.service.TareaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TareasServiceImpl implements TareaService {
    @Autowired
    private Tareasrepository tareasrepository;
    @Override
    public Tareas createTareas(Tareas tareas, String requesterRole) throws Exception {
        if(!requesterRole.equals(("ROLE ADMIN"))){
            throw new Exception("solo el admin puede crear tareas");
        }

        tareas.setStatus(TareaEstado.PENDIENTE);
        tareas.setCreadoAt(LocalDateTime.now());
        return tareasrepository.save(tareas);
    }

    @Override
    public Tareas getTareasById(Long id) throws Exception {
        return tareasrepository.findById(id).orElseThrow(()->new Exception("tarea no encontrada con id"+id));
    }

    @Override
    public List<Tareas> getAllTareas(TareaEstado estado) {
        List<Tareas> allTareas=tareasrepository.findAll();
        List<Tareas> filtrartareas=allTareas.stream().filter(
                tareas -> estado==null || tareas.getStatus().name().equalsIgnoreCase(estado.toString())
        ).collect(Collectors.toList());
        return filtrartareas;
    }

    @Override
    public Tareas updateTareas(Long id, Tareas updatedTareas, Long userId) throws Exception {
        Tareas existenlasTareas = getTareasById(id);

        if (updatedTareas.getTitle()!=null){
            existenlasTareas.setTitle(updatedTareas.getTitle());
        }
        if (updatedTareas.getImage()!=null){
            existenlasTareas.setImage(updatedTareas.getImage());
        }
        if (updatedTareas.getDescripcion()!=null){
            existenlasTareas.setDescripcion(updatedTareas.getDescripcion());
        }
        if (updatedTareas.getStatus()!=null){
            existenlasTareas.setStatus(updatedTareas.getStatus());
        }
        if (updatedTareas.getFechaLimite()!=null){
            existenlasTareas.setFechaLimite(updatedTareas.getFechaLimite());
        }
        return null;
    }

    @Override
    public void deleteTareas(Long id) throws Exception {
        getTareasById(id);
        tareasrepository.deleteById(id);

    }

    @Override
    public Tareas assignedToUser(Long userId, Long TareasId) throws Exception {
        Tareas tareas = getTareasById(TareasId);
        tareas.setAsignacionUsuarioId(userId);
        tareas.setStatus(TareaEstado.HECHO);
        return null;
    }

    @Override
    public List<Tareas> assignedUsersTareas(Long userId, TareaEstado estado) {
        List<Tareas> allTareas=tareasrepository.findByAsignacionUsuarioId(userId);
        List<Tareas> filtrartareas=allTareas.stream().filter(
                tareas -> estado==null || tareas.getStatus().name().equalsIgnoreCase(estado.toString())
        ).collect(Collectors.toList());
        return filtrartareas;
    }

    @Override
    public Tareas completeTareas(Long tareasId) throws Exception {
        Tareas tareas=getTareasById(tareasId);
        tareas.setStatus(TareaEstado.HECHO);

        return tareasrepository.save(tareas);
    }
}
