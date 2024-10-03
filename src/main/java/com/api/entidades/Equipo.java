package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipo;
    @Column(nullable = false,length = 30)
    private String nombreProyecto;
    @Column(nullable = false,length = 50)
    private String nombreSoftware;
    @Column(nullable = false,length = 150)
    private String integrantes;
    @Column(nullable = false,length = 50)
    private String lema;
    @Column(nullable = false,length = 150)
    private String descripcion;
    @ManyToOne(targetEntity = Aprendiz.class)
    @JoinColumn(name = "fk_id_aprendiz",referencedColumnName = "idAprendiz")
    private Aprendiz apren;
}
