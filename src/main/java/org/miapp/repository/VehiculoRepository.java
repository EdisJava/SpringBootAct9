package org.miapp.repository;

import org.miapp.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // Al extender JpaRepository ya tienes save(), findAll(), findById(), deleteById(), etc.

    // Métodos derivados para búsqueda individual
    List<Vehiculo> findByMarca(String marca);

    List<Vehiculo> findByModelo(String modelo);

    List<Vehiculo> findByAnio(int anio);

    List<Vehiculo> findByColor(String color);

    Optional<Vehiculo> findByMatricula(String matricula);

    // Búsqueda caso-insensible por marca
    List<Vehiculo> findByMarcaIgnoreCase(String marca);

    // Búsqueda combinada por marca y modelo
    List<Vehiculo> findByMarcaAndModelo(String marca, String modelo);

    // Búsqueda por marca y rango de años
    List<Vehiculo> findByMarcaAndAnioBetween(String marca, int anioMin, int anioMax);

    // Búsqueda avanzada con múltiples filtros usando @Query
    @Query("SELECT v FROM Vehiculo v WHERE " +
           "(:marca IS NULL OR LOWER(v.marca) LIKE LOWER(CONCAT('%', :marca, '%'))) AND " +
           "(:modelo IS NULL OR LOWER(v.modelo) LIKE LOWER(CONCAT('%', :modelo, '%'))) AND " +
           "(:anio IS NULL OR v.anio = :anio) AND " +
           "(:color IS NULL OR LOWER(v.color) LIKE LOWER(CONCAT('%', :color, '%'))) AND " +
           "(:matricula IS NULL OR LOWER(v.matricula) LIKE LOWER(CONCAT('%', :matricula, '%')))")
    List<Vehiculo> buscarAvanzado(
        @Param("marca") String marca,
        @Param("modelo") String modelo,
        @Param("anio") Integer anio,
        @Param("color") String color,
        @Param("matricula") String matricula
    );
}