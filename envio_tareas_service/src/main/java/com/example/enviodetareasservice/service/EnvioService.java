package com.example.enviodetareasservice.service;

import com.example.enviodetareasservice.entity.Envio;

import java.util.List;

public interface EnvioService {
    Envio enviarTarea(Long tareaId,String githubLink, Long userId, String jwt) throws Exception;

    Envio getEnvioTareaById(Long envioId) throws Exception;

    List<Envio> getAllEnvioTarea();

    List<Envio> getEnvioTareaByTareaId(Long tareaId);

    Envio aceptarRechazarEnvio(Long id, String estado) throws Exception;
}
