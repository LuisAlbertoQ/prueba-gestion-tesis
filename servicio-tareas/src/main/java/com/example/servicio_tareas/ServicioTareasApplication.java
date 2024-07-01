package com.example.servicio_tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServicioTareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioTareasApplication.class, args);
	}

}
