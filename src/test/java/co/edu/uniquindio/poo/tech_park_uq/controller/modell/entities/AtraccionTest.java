package co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoActual;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoAtraccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtraccionTest {

    private Atraccion atraccion;
    private Visitante visitante;

    @BeforeEach
    void setUp() {
        atraccion = new Atraccion(
                "ATR001",
                "Montaña Rusa",
                20,
                1.40f,
                12,
                5000f,
                TipoAtraccion.MECANICA_ALTURA
        );
        
        visitante = new Visitante("Juan Perez", "123456", 15, 1.50f, 10000f);
    }

    @Test
    void testCreacionAtraccion() {
        assertNotNull(atraccion);
        assertEquals("Montaña Rusa", atraccion.getNombre());
        assertEquals(EstadoActual.ACTIVA, atraccion.getEstadoActual());
        assertEquals(50, atraccion.getCostoEnPuntos()); // MECANICA_ALTURA = 50 puntos
    }

    @Test
    void testVerificarRequisitosVisitantes() {
        assertTrue(atraccion.verificarRequisitosVisitantes(visitante));
        
        Visitante visitanteNoApto = new Visitante("Pedro", "789", 10, 1.30f, 5000f);
        assertFalse(atraccion.verificarRequisitosVisitantes(visitanteNoApto));
    }

    @Test
    void testRegistrarIngreso() {
        int visitasInicial = atraccion.getVisitasHoy();
        float ingresosInicial = atraccion.getIngresosTotales();
        
        atraccion.registrarIngreso();
        
        assertEquals(visitasInicial + 1, atraccion.getVisitasHoy());
        assertEquals(ingresosInicial + 5000f, atraccion.getIngresosTotales());
    }

    @Test
    void testMantenimientoAutomatico() {
        for (int i = 0; i < 500; i++) {
            atraccion.registrarIngreso();
        }
        
        assertEquals(EstadoActual.EN_MANTENIMIENTO, atraccion.getEstadoActual());
    }

    @Test
    void testAgregarPersonaCola() {
        assertEquals(0, atraccion.getTiempoEspera());
        
        atraccion.agregarPersonaCola();
        assertEquals(2, atraccion.getTiempoEspera());
        assertEquals(1, atraccion.getPersonasEnCola());
        
        atraccion.agregarPersonaCola();
        assertEquals(4, atraccion.getTiempoEspera());
        assertEquals(2, atraccion.getPersonasEnCola());
    }

    @Test
    void testAgregarPersonaColaFastPass() {
        atraccion.agregarPersonaCola();
        assertEquals(2, atraccion.getTiempoEspera());
        
        atraccion.agregarPersonaColaFastPass();
        assertEquals(2, atraccion.getTiempoEspera()); // No aumenta
        assertEquals(2, atraccion.getPersonasEnCola());
    }

    @Test
    void testRemoverPersonaCola() {
        atraccion.agregarPersonaCola();
        atraccion.agregarPersonaCola();
        assertEquals(4, atraccion.getTiempoEspera());
        
        atraccion.removerPersonaCola();
        assertEquals(2, atraccion.getTiempoEspera()); // Se reduce 2 min
        assertEquals(1, atraccion.getPersonasEnCola());
    }

    @Test
    void testTiempoEsperaMinimoEsCero() {
        atraccion.agregarPersonaCola();
        atraccion.removerPersonaCola();
        atraccion.removerPersonaCola();
        
        assertEquals(0, atraccion.getTiempoEspera());
    }

    @Test
    void testCambiarEstado() {
        atraccion.setEstadoActual(EstadoActual.CERRADO);
        assertEquals(EstadoActual.CERRADO, atraccion.getEstadoActual());
        assertFalse(atraccion.validarRequisitos());
    }

    @Test
    void testResetearVisitasHoy() {
        atraccion.registrarIngreso();
        atraccion.registrarIngreso();
        assertEquals(2, atraccion.getVisitasHoy());
        
        atraccion.resetearVisitasHoy();
        assertEquals(0, atraccion.getVisitasHoy());
    }
}
