package com.api.servicios;

import com.api.entidades.Reporte;
import com.api.repositorios.IRepositorioReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioReporte implements IServicioReporte{
    @Autowired
    private IRepositorioReporte iRepositorioReporte;

    @Override
    public List<Reporte> listaReporte() {
        return this.iRepositorioReporte.findAll();
    }

    @Override
    public Reporte buscarReportePorId(Long id) {
        return this.iRepositorioReporte.findById(id).orElse(null);
    }

    @Override
    public Reporte guardarReporte(Reporte reporte) {
        return this.iRepositorioReporte.save(reporte);
    }

    @Override
    public void eliminarReporte(Long id) {
        this.iRepositorioReporte.deleteById(id);
    }
}
