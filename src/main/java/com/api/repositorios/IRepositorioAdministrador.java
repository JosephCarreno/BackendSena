package com.api.repositorios;

import com.api.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioAdministrador extends JpaRepository<Administrador, Long> {
}
