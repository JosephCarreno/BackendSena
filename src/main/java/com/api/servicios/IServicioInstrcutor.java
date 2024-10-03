package com.api.servicios;

import com.api.entidades.Instructor;

import java.util.List;

public interface IServicioInstrcutor {

    List<Instructor> listaInstructor();

    Instructor buscarInstructorPorId(Long id);

    Instructor guardarInstructor(Instructor instructor);

    void eliminarInstructor(Long id);
}
