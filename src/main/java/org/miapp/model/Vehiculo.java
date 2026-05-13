package org.miapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marca no puede estar vacía")
    private String marca;

    @NotBlank(message = "El modelo no puede estar vacío")
    private String modelo;

    // evito usar 'año' en el nombre de la variable para no tener problemas con encoding; uso 'anio'
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2100, message = "El año debe ser menor o igual a 2100")
    private int anio;

    @NotBlank(message = "El color no puede estar vacío")
    private String color;

    // matrícula/placa - única y requerida
    @NotBlank(message = "La matrícula no puede estar vacía")
    @Column(unique = true)
    private String matricula;
}