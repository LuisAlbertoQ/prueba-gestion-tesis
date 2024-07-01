package com.example.servicio_tareas.service;

import com.example.servicio_tareas.entity.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", path="/api/user")
public interface UserService {
    @GetMapping("/perfil")
    public UserDto getUserProfile(@RequestHeader("Authorization")String jwt);
}
