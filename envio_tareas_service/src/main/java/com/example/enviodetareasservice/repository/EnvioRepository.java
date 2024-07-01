package com.example.enviodetareasservice.repository;

import com.example.enviodetareasservice.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

    List <Envio> findByTareaId(Long tareaId);
}
