package com.api.repositorios;

import com.api.entidades.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioArchivo extends JpaRepository<Archivo,Long> {
}
