package com.api.servicios;

import com.api.entidades.Ficha;
import com.api.repositorios.IRepositorioFicha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioFicha implements IServicioFicha{
    @Autowired
    private IRepositorioFicha iRepositorioFicha;

    @Override
    public List<Ficha> listaFicha() {
        return this.iRepositorioFicha.findAll();
    }

    @Override
    public Ficha buscarFichaPorId(Long id) {
        return this.iRepositorioFicha.findById(id).orElse(null);
    }

    @Override
    public Ficha guardarFicha(Ficha ficha) {
        return this.iRepositorioFicha.save(ficha);
    }

    @Override
    public void eliminarFicha(Long id) {
    this.iRepositorioFicha.deleteById(id);
    }
}
