package org.miapp.controller;

import org.miapp.model.Vehiculo;
import org.miapp.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    // Volvemos a usar Inyección de Dependencias para traer el Service
    @Autowired
    private VehiculoService service;

    // Endpoint para guardar (POST: http://localhost:8080/api/vehiculos)
    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return service.guardarVehiculo(vehiculo);
    }

    // Endpoint para listar (GET: http://localhost:8080/api/vehiculos)
    @GetMapping
    public List<Vehiculo> listarTodos() {
        return service.obtenerTodos();
    }
}