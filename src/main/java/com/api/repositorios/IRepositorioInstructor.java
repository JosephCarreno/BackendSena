package com.api.repositorios;

import com.api.entidades.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioInstructor extends JpaRepository<Instructor, Long> {
}
