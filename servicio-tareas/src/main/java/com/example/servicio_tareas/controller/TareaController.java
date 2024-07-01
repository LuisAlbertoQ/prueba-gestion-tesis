package com.example.servicio_tareas.controller;

import com.example.servicio_tareas.entity.TareaEstado;
import com.example.servicio_tareas.entity.Tareas;
import com.example.servicio_tareas.entity.UserDto;
import com.example.servicio_tareas.service.TareaService;
import com.example.servicio_tareas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    @Autowired
    private TareaService tareaService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Tareas> createTareas(@RequestBody Tareas tareas,
                                               @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        System.out.println(user.toString());
        Tareas createdtareas = tareaService.createTareas(tareas, user.getRol());
        return new ResponseEntity<>(createdtareas, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tareas> getTareasById(@PathVariable Long id,
                                                @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        Tareas tareas = tareaService.getTareasById(id);
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }


    @GetMapping("/user")
    public ResponseEntity<List<Tareas>> getAssignedUsersTareas(
            @RequestParam(required = false) TareaEstado estado,
            @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);

        List<Tareas> tareass = tareaService.assignedUsersTareas(user.getId(), estado);

        return new ResponseEntity<>(tareass, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Tareas>> getAllTareas(
            @RequestParam(required = false) TareaEstado estado,
            @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user =userService.getUserProfile(jwt);

        List<Tareas> tareass=tareaService.getAllTareas(estado);

        return new ResponseEntity<>(tareass, HttpStatus.OK);
    }


    @PutMapping("/{id}/user/{userid}/assigned")
    public ResponseEntity<Tareas> assignedTareasToUser(
            @PathVariable Long id,
            @PathVariable Long userid,
            @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);

        Tareas tareass = tareaService.assignedToUser(userid, id);

        return new ResponseEntity<>(tareass, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tareas> updateTareas(
            @PathVariable Long id,
            @RequestBody Tareas req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);

        Tareas tareass = tareaService.updateTareas(id,req,user.getId());

        return new ResponseEntity<>(tareass, HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Tareas> completeTareas(
            @PathVariable Long id) throws Exception {


        Tareas tareass = tareaService.completeTareas(id);

        return new ResponseEntity<>(tareass, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTareas(
            @PathVariable Long id) throws Exception {


        tareaService.deleteTareas(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
