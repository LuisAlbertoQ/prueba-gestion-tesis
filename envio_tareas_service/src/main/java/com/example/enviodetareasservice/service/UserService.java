package com.example.enviodetareasservice.service;

import com.example.enviodetareasservice.entity.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", path="/api/user")
public interface UserService {
    @GetMapping("/perfil")
    public UserDto getUserProfile(@RequestHeader("Authorization")String jwt);
}

