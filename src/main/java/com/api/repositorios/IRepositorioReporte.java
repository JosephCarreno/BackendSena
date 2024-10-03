package com.api.repositorios;

import com.api.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioReporte extends JpaRepository<Reporte, Long> {
}
