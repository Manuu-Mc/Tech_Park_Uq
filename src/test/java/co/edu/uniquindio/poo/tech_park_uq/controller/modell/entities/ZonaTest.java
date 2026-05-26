package co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZonaTest {

    private Zona zona;

    @BeforeEach
    void setUp() {
        zona = new Zona("Zona Infantil", 50);
    }

    @Test
    void testCreacionZona() {
        assertNotNull(zona);
        assertEquals("Zona Infantil", zona.getNombre());
        assertEquals(50, zona.getCapacidadMaxima());
        assertEquals(0, zona.getVisitantesActuales());
    }

    @Test
    void testPuedeIngresarVisitante() {
        assertTrue(zona.puedeIngresarVisitante());
        
        for (int i = 0; i < 50; i++) {
            zona.registrarIngresoVisitante();
        }
        
        assertFalse(zona.puedeIngresarVisitante());
    }

    @Test
    void testRegistrarIngresoVisitante() {
        zona.registrarIngresoVisitante();
        assertEquals(1, zona.getVisitantesActuales());
        assertEquals(49, zona.getEspaciosDisponibles());
    }

    @Test
    void testRegistrarSalidaVisitante() {
        zona.registrarIngresoVisitante();
        zona.registrarIngresoVisitante();
        assertEquals(2, zona.getVisitantesActuales());
        
        zona.registrarSalidaVisitante();
        assertEquals(1, zona.getVisitantesActuales());
        assertEquals(49, zona.getEspaciosDisponibles());
    }

    @Test
    void testNoPermitirSalidaSinVisitantes() {
        assertEquals(0, zona.getVisitantesActuales());
        zona.registrarSalidaVisitante();
        assertEquals(0, zona.getVisitantesActuales());
    }

    @Test
    void testGetEspaciosDisponibles() {
        assertEquals(50, zona.getEspaciosDisponibles());
        
        zona.registrarIngresoVisitante();
        zona.registrarIngresoVisitante();
        zona.registrarIngresoVisitante();
        
        assertEquals(47, zona.getEspaciosDisponibles());
    }

    @Test
    void testCapacidadMaximaAlcanzada() {
        for (int i = 0; i < 50; i++) {
            assertTrue(zona.puedeIngresarVisitante());
            zona.registrarIngresoVisitante();
        }
        
        assertEquals(50, zona.getVisitantesActuales());
        assertEquals(0, zona.getEspaciosDisponibles());
        assertFalse(zona.puedeIngresarVisitante());
    }
}
