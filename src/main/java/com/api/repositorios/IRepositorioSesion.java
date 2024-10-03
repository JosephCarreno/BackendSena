package com.api.repositorios;

import com.api.entidades.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioSesion extends JpaRepository<Sesion, Long> {
}
