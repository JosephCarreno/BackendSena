package com.api.repositorios;

import com.api.entidades.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioFicha extends JpaRepository<Ficha, Long> {
}
