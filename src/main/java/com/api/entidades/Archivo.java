package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArchivo;
    @Lob
    @Column(nullable = false)
    private byte[] contenido;
    @Column(nullable = false)
    private String nombreArchivo;
    @Column(nullable = false)
    private String tipoArchivo;
}
