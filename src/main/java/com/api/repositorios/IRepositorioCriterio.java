package com.api.repositorios;

import com.api.entidades.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioCriterio extends JpaRepository<Criterio, Long> {
}
