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
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFicha;
    @Column(nullable = false)
    private Integer numeroFicha;
    @Column(nullable = false,length = 50)
    private String nombreFicha;
    @Column(nullable = false)
    private LocalDate fechaIncio;
    @Column(nullable = false)
    private LocalDate fechaFinal;
    @OneToOne(targetEntity = Aprendiz.class)
    @JoinColumn( name = "fk_id_aprendiz",referencedColumnName = "idAprendiz")
    private Aprendiz apren;
}
