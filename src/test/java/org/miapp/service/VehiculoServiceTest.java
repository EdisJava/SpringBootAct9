package org.miapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.miapp.model.Vehiculo;
import org.miapp.repository.VehiculoRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {

    @Mock
    private VehiculoRepository repository;

    @InjectMocks
    private VehiculoService service;

    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        vehiculo = new Vehiculo();
        vehiculo.setId(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
        vehiculo.setAnio(2020);
        vehiculo.setColor("Blanco");
        vehiculo.setMatricula("ABC123");
    }

    @Test
    void testGuardarVehiculo() {
        when(repository.save(any(Vehiculo.class))).thenReturn(vehiculo);

        Vehiculo guardado = service.guardarVehiculo(new Vehiculo());

        assertNotNull(guardado);
        assertEquals("Toyota", guardado.getMarca());
        verify(repository, times(1)).save(any(Vehiculo.class));
    }

    @Test
    void testObtenerTodos() {
        when(repository.findAll()).thenReturn(List.of(vehiculo));

        List<Vehiculo> lista = service.obtenerTodos();

        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        verify(repository, times(1)).findAll();
    }
}