package com.api.repositorios;

import com.api.entidades.Evaluador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioEvaluador extends JpaRepository<Evaluador, Long> {
}
