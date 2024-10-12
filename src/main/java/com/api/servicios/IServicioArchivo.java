package com.api.servicios;

import com.api.entidades.Archivo;

import java.util.List;

public interface IServicioArchivo {

    List<Archivo> listaArchivo();

    Archivo buscarArchivoPorId(Long id);

    Archivo guardarArchivo(Archivo archivo);

    void eliminarArchivo(Long id);
}
