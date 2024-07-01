package com.example.msdocumento.repository;

import com.example.msdocumento.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    Optional<Documento> findByNombreDocumento(String nombreDocumento);
}
