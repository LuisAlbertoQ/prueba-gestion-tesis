package com.example.servicio_tareas.controller;

import com.example.servicio_tareas.entity.TareaEstado;
import com.example.servicio_tareas.entity.Tareas;
import com.example.servicio_tareas.entity.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @GetMapping("/tareas")
    public ResponseEntity<String> getAssignedUsersTareas(){

        return new ResponseEntity<>("bienvenido a servicio de tareas", HttpStatus.OK);
    }
}
