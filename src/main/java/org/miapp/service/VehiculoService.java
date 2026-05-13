package org.miapp.service;

import org.miapp.model.Vehiculo;
import org.miapp.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository repository;

    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }

    public List<Vehiculo> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Vehiculo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    public List<Vehiculo> buscarPorMarca(String marca) {
        return repository.findByMarca(marca);
    }

    public List<Vehiculo> buscarPorModelo(String modelo) {
        return repository.findByModelo(modelo);
    }

    public List<Vehiculo> buscarPorAnio(int anio) {
        return repository.findByAnio(anio);
    }

    public List<Vehiculo> buscarPorColor(String color) {
        return repository.findByColor(color);
    }

    public Optional<Vehiculo> buscarPorMatricula(String matricula) {
        return repository.findByMatricula(matricula);
    }

    public List<Vehiculo> buscarPorMarcaIgnoreCase(String marca) {
        return repository.findByMarcaIgnoreCase(marca);
    }

    public List<Vehiculo> buscarPorMarcaYModelo(String marca, String modelo) {
        return repository.findByMarcaAndModelo(marca, modelo);
    }

    public List<Vehiculo> buscarPorMarcaYRangoAnio(String marca, int anioMin, int anioMax) {
        return repository.findByMarcaAndAnioBetween(marca, anioMin, anioMax);
    }

    /**
     * Búsqueda avanzada con múltiples filtros opcionales.
     * Todos los parámetros son opcionales (null).
     *
     * @param marca Filtro por marca (búsqueda parcial, case-insensitive)
     * @param modelo Filtro por modelo (búsqueda parcial, case-insensitive)
     * @param anio Filtro por año exacto
     * @param color Filtro por color (búsqueda parcial, case-insensitive)
     * @param matricula Filtro por matrícula (búsqueda parcial, case-insensitive)
     * @return Lista de vehículos que cumplan con todos los filtros proporcionados
     */
    public List<Vehiculo> buscarAvanzado(String marca, String modelo, Integer anio,
                                         String color, String matricula) {
        return repository.buscarAvanzado(marca, modelo, anio, color, matricula);
    }
}