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
}