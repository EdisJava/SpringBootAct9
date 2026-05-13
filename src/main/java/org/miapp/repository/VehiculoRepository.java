package org.miapp.repository;

import org.miapp.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // Al extender JpaRepository ya tienes save(), findAll(), findById(), deleteById(), etc.
    // Añadido este metodo derivado para que VehiculoService pueda llamar repository.findByMarca(...)
    //Es la forma mas sencilla que encontre
    List<Vehiculo> findByMarca(String marca);
}