package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;
    @Column(nullable = false,length = 50)
    private String descripcion;
    @Column(nullable = false,length = 50)
    private String titulo;
    @ManyToOne(targetEntity = Administrador.class)
    @JoinColumn(name = "fk_id_administrador",referencedColumnName = "idAdministrador")
    private Administrador admin;
}
