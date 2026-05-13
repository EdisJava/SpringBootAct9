package org.miapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private String modelo;

    // evito usar 'año' en el nombre de la variable para no tener problemas con encoding; uso 'anio'
    private int anio;

    private String color;

    // matrícula/placa - si debe ser única puedes añadir @Column(unique = true)
    private String matricula;
}