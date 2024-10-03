package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Criterio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCriterio;
    @Column(nullable = false)
    private LocalDate fechaIncio;
    @Column(nullable = false)
    private LocalDate fechaFinal;
    @Column(nullable = false, length = 50)
    private String titulo;
    @Column(nullable = false,length = 10)
    private String calificacion;
    @Column(nullable = false,length = 100)
    private String descripcion;
    @ManyToOne(targetEntity = Instructor.class)
    @JoinColumn(name = "fk_is_instructor",referencedColumnName = "idInstructor")
    private Instructor ins;
}
