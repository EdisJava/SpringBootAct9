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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {

    @Mock
    private VehiculoRepository repository;

    @InjectMocks
    private VehiculoService service;

    private Vehiculo vehiculo;
    private Vehiculo vehiculo2;

    @BeforeEach
    void setUp() {
        vehiculo = new Vehiculo();
        vehiculo.setId(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
        vehiculo.setAnio(2020);
        vehiculo.setColor("Blanco");
        vehiculo.setMatricula("ABC123");

        vehiculo2 = new Vehiculo();
        vehiculo2.setId(2L);
        vehiculo2.setMarca("Honda");
        vehiculo2.setModelo("Civic");
        vehiculo2.setAnio(2021);
        vehiculo2.setColor("Negro");
        vehiculo2.setMatricula("XYZ789");
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
        when(repository.findAll()).thenReturn(List.of(vehiculo, vehiculo2));

        List<Vehiculo> lista = service.obtenerTodos();

        assertFalse(lista.isEmpty());
        assertEquals(2, lista.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(vehiculo));

        Optional<Vehiculo> resultado = service.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Toyota", resultado.get().getMarca());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdNoExiste() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        Optional<Vehiculo> resultado = service.buscarPorId(999L);

        assertFalse(resultado.isPresent());
        verify(repository, times(1)).findById(999L);
    }

    @Test
    void testEliminarPorId() {
        service.eliminarPorId(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testBuscarPorMarca() {
        when(repository.findByMarca("Toyota")).thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarPorMarca("Toyota");

        assertEquals(1, resultado.size());
        assertEquals("Toyota", resultado.get(0).getMarca());
        verify(repository, times(1)).findByMarca("Toyota");
    }

    @Test
    void testBuscarPorModelo() {
        when(repository.findByModelo("Corolla")).thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarPorModelo("Corolla");

        assertEquals(1, resultado.size());
        assertEquals("Corolla", resultado.get(0).getModelo());
        verify(repository, times(1)).findByModelo("Corolla");
    }

    @Test
    void testBuscarPorAnio() {
        when(repository.findByAnio(2020)).thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarPorAnio(2020);

        assertEquals(1, resultado.size());
        assertEquals(2020, resultado.get(0).getAnio());
        verify(repository, times(1)).findByAnio(2020);
    }

    @Test
    void testBuscarPorColor() {
        when(repository.findByColor("Blanco")).thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarPorColor("Blanco");

        assertEquals(1, resultado.size());
        assertEquals("Blanco", resultado.get(0).getColor());
        verify(repository, times(1)).findByColor("Blanco");
    }

    @Test
    void testBuscarPorMatricula() {
        when(repository.findByMatricula("ABC123")).thenReturn(Optional.of(vehiculo));

        Optional<Vehiculo> resultado = service.buscarPorMatricula("ABC123");

        assertTrue(resultado.isPresent());
        assertEquals("ABC123", resultado.get().getMatricula());
        verify(repository, times(1)).findByMatricula("ABC123");
    }

    @Test
    void testBuscarPorMarcaIgnoreCase() {
        when(repository.findByMarcaIgnoreCase("toyota")).thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarPorMarcaIgnoreCase("toyota");

        assertEquals(1, resultado.size());
        verify(repository, times(1)).findByMarcaIgnoreCase("toyota");
    }

    @Test
    void testBuscarPorMarcaYModelo() {
        when(repository.findByMarcaAndModelo("Toyota", "Corolla")).thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarPorMarcaYModelo("Toyota", "Corolla");

        assertEquals(1, resultado.size());
        assertEquals("Toyota", resultado.get(0).getMarca());
        assertEquals("Corolla", resultado.get(0).getModelo());
        verify(repository, times(1)).findByMarcaAndModelo("Toyota", "Corolla");
    }

    @Test
    void testBuscarPorMarcaYRangoAnio() {
        when(repository.findByMarcaAndAnioBetween("Toyota", 2015, 2022))
            .thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarPorMarcaYRangoAnio("Toyota", 2015, 2022);

        assertEquals(1, resultado.size());
        verify(repository, times(1)).findByMarcaAndAnioBetween("Toyota", 2015, 2022);
    }

    @Test
    void testBuscarAvanzadoSoloMarca() {
        when(repository.buscarAvanzado("Toyota", null, null, null, null))
            .thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarAvanzado("Toyota", null, null, null, null);

        assertEquals(1, resultado.size());
        assertEquals("Toyota", resultado.get(0).getMarca());
        verify(repository, times(1)).buscarAvanzado("Toyota", null, null, null, null);
    }

    @Test
    void testBuscarAvanzadoMultiplesFiltros() {
        when(repository.buscarAvanzado("Toyota", "Corolla", 2020, "Blanco", null))
            .thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarAvanzado("Toyota", "Corolla", 2020, "Blanco", null);

        assertEquals(1, resultado.size());
        assertEquals("Toyota", resultado.get(0).getMarca());
        assertEquals("Corolla", resultado.get(0).getModelo());
        assertEquals(2020, resultado.get(0).getAnio());
        assertEquals("Blanco", resultado.get(0).getColor());
        verify(repository, times(1)).buscarAvanzado("Toyota", "Corolla", 2020, "Blanco", null);
    }

    @Test
    void testBuscarAvanzadoSinResultados() {
        when(repository.buscarAvanzado("NoExiste", null, null, null, null))
            .thenReturn(List.of());

        List<Vehiculo> resultado = service.buscarAvanzado("NoExiste", null, null, null, null);

        assertTrue(resultado.isEmpty());
        verify(repository, times(1)).buscarAvanzado("NoExiste", null, null, null, null);
    }

    @Test
    void testBuscarAvanzadoTodosFiltros() {
        when(repository.buscarAvanzado("Toyota", "Corolla", 2020, "Blanco", "ABC123"))
            .thenReturn(List.of(vehiculo));

        List<Vehiculo> resultado = service.buscarAvanzado("Toyota", "Corolla", 2020, "Blanco", "ABC123");

        assertEquals(1, resultado.size());
        verify(repository, times(1)).buscarAvanzado("Toyota", "Corolla", 2020, "Blanco", "ABC123");
    }
}