package com.alexandre.tempus.repository;

import com.alexandre.tempus.model.RegistroPonto;
import com.alexandre.tempus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {

    List<RegistroPonto> findByUsuarioAndHorarioBetween(Usuario usuario, LocalDateTime inicio, LocalDateTime fim);
}
