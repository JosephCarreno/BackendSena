package com.api.repositorios;

import com.api.entidades.CalificarProyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioCalificarProyecto extends JpaRepository<CalificarProyecto, Long> {
}
