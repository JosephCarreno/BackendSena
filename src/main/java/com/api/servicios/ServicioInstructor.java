package com.api.servicios;

import com.api.entidades.Instructor;
import com.api.repositorios.IRepositorioInstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioInstructor implements IServicioInstrcutor{
    @Autowired
    private IRepositorioInstructor iRepositorioInstructor;

    @Override
    public List<Instructor> listaInstructor() {
        return this.iRepositorioInstructor.findAll();
    }

    @Override
    public Instructor buscarInstructorPorId(Long id) {
        return this.iRepositorioInstructor.findById(id).orElse(null);
    }

    @Override
    public Instructor guardarInstructor(Instructor instructor) {
        return this.iRepositorioInstructor.save(instructor);
    }

    @Override
    public void eliminarInstructor(Long id) {
    this.iRepositorioInstructor.deleteById(id);}
}
