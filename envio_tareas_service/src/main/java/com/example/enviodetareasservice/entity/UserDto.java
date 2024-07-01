package com.example.enviodetareasservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private long id;

    private String password;
    private String nombre;
    private String email;
    private String rol;
}
