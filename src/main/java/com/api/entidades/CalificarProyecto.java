package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CalificarProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificarProyecto;
    @Column(nullable = false,length = 100)
    private String calidadCodigo;
    @Column(nullable = false,length = 100)
    private String funcionalidad;
    @Column(nullable = false,length = 100)
    private String interfaazUsuario;
    @Column(nullable = false,length = 100)
    private String innovacion;
    @Column(nullable = false,length = 100)
    private String escalabilidad;
    @Column(nullable = false,length = 100)
    private String trabajoEquipo;
    @ManyToOne(targetEntity = Evaluador.class)
    @JoinColumn(name = "fk_id_evaluador",referencedColumnName = "idEvaluador")
    private Evaluador eval;
}
