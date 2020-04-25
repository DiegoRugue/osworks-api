package com.algaworks.osworks.osworksapi.domain.repository;

import com.algaworks.osworks.osworksapi.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepsitory extends JpaRepository<Comentario, Long> {
}
