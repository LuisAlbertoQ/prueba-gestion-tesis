package com.example.enviodetareasservice.service;

import com.example.enviodetareasservice.entity.TareasDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "tareas-service", path = "/api/tareas")
public interface TareaService {

    @GetMapping("/{id}")
    public TareasDto getTareasById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/{id}/complete")
    public TareasDto completeTareas(
            @PathVariable Long id) throws Exception;

}
