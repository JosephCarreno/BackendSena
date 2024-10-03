package com.api.repositorios;


import com.api.entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioEquipo extends JpaRepository<Equipo, Long> {
}
