package org.miapp.controller;

import org.miapp.model.Vehiculo;
import org.miapp.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    // Volvemos a usar Inyección de Dependencias para traer el Service
    @Autowired
    private VehiculoService service;

    // Endpoint para guardar (POST: http://localhost:8080/api/vehiculos)
    @PostMapping
    public ResponseEntity<Vehiculo> crear(@RequestBody Vehiculo vehiculo) {
        Vehiculo guardado = service.guardarVehiculo(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // Endpoint para listar todos (GET: http://localhost:8080/api/vehiculos)
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // Endpoint para obtener por ID (GET: http://localhost:8080/api/vehiculos/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = service.buscarPorId(id);
        return vehiculo.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para eliminar (DELETE: http://localhost:8080/api/vehiculos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para actualizar (PUT: http://localhost:8080/api/vehiculos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizar(@PathVariable Long id,
                                               @RequestBody Vehiculo vehiculoActualizado) {
        Optional<Vehiculo> vehiculoExistente = service.buscarPorId(id);
        if (vehiculoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Vehiculo v = vehiculoExistente.get();
        v.setMarca(vehiculoActualizado.getMarca());
        v.setModelo(vehiculoActualizado.getModelo());
        v.setAnio(vehiculoActualizado.getAnio());
        v.setColor(vehiculoActualizado.getColor());
        v.setMatricula(vehiculoActualizado.getMatricula());
        return ResponseEntity.ok(service.guardarVehiculo(v));
    }

    // Búsqueda simple por marca (GET: http://localhost:8080/api/vehiculos/marca?valor=Toyota)
    @GetMapping("/marca")
    public ResponseEntity<List<Vehiculo>> buscarPorMarca(@RequestParam String valor) {
        return ResponseEntity.ok(service.buscarPorMarca(valor));
    }

    // Búsqueda por modelo (GET: http://localhost:8080/api/vehiculos/modelo?valor=Corolla)
    @GetMapping("/modelo")
    public ResponseEntity<List<Vehiculo>> buscarPorModelo(@RequestParam String valor) {
        return ResponseEntity.ok(service.buscarPorModelo(valor));
    }

    // Búsqueda por año (GET: http://localhost:8080/api/vehiculos/anio?valor=2020)
    @GetMapping("/anio")
    public ResponseEntity<List<Vehiculo>> buscarPorAnio(@RequestParam int valor) {
        return ResponseEntity.ok(service.buscarPorAnio(valor));
    }

    // Búsqueda por color (GET: http://localhost:8080/api/vehiculos/color?valor=Blanco)
    @GetMapping("/color")
    public ResponseEntity<List<Vehiculo>> buscarPorColor(@RequestParam String valor) {
        return ResponseEntity.ok(service.buscarPorColor(valor));
    }

    // Búsqueda por matrícula (GET: http://localhost:8080/api/vehiculos/matricula?valor=ABC123)
    @GetMapping("/matricula")
    public ResponseEntity<Vehiculo> buscarPorMatricula(@RequestParam String valor) {
        Optional<Vehiculo> vehiculo = service.buscarPorMatricula(valor);
        return vehiculo.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Búsqueda avanzada con filtros combinables y opcionales.
     * Ejemplos:
     * GET /api/vehiculos/buscar?marca=Toyota
     * GET /api/vehiculos/buscar?marca=Toyota&modelo=Corolla
     * GET /api/vehiculos/buscar?marca=Toyota&anio=2020&color=Blanco
     * GET /api/vehiculos/buscar?matricula=ABC123
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Vehiculo>> buscarAvanzado(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String matricula) {
        List<Vehiculo> resultados = service.buscarAvanzado(marca, modelo, anio, color, matricula);
        return ResponseEntity.ok(resultados);
    }
}