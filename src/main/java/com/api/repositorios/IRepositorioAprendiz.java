package com.api.repositorios;

import com.api.entidades.Aprendiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioAprendiz extends JpaRepository<Aprendiz,Long> {
}
