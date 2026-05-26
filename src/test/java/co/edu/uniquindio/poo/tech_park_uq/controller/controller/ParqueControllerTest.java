package co.edu.uniquindio.poo.tech_park_uq.controller.controller;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.*;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoAtraccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParqueControllerTest {

    private ParqueController controller;
    private Visitante visitante;
    private Atraccion atraccion;
    private Operador operador;

    @BeforeEach
    void setUp() {
        controller = new ParqueController();
        visitante = new Visitante("Ana", "222", 18, 1.60f, 50000f);
        atraccion = new Atraccion("ATR001", "Carrusel", 30, 1.0f, 3, 0f, TipoAtraccion.INFANTIL);
        operador = new Operador("Pedro", "333", 30, "OP001", null, null);
    }

    @Test
    void testRegistrarVisitante() {
        controller.registrarVisitante(visitante);
        assertTrue(controller.getVisitantes().contains(visitante));
    }

    @Test
    void testRegistrarOperador() {
        controller.registrarOperador(operador);
        assertTrue(controller.getOperadores().contains(operador));
    }

    @Test
    void testAgregarAtraccion() {
        controller.agregarAtraccion(atraccion);
        assertTrue(controller.getAtracciones().contains(atraccion));
    }

    @Test
    void testAgregarSolicitud() {
        SolicitudAcceso solicitud = new SolicitudAcceso(visitante, atraccion, 20);
        controller.agregarSolicitud(solicitud);
        assertTrue(controller.getSolicitudesPendientes().contains(solicitud));
    }

    @Test
    void testRemoverSolicitud() {
        SolicitudAcceso solicitud = new SolicitudAcceso(visitante, atraccion, 20);
        controller.agregarSolicitud(solicitud);
        controller.removerSolicitud(solicitud);
        assertFalse(controller.getSolicitudesPendientes().contains(solicitud));
    }

    @Test
    void testPuedeIngresarAlParque() {
        for (int i = 0; i < 999; i++) {
            Visitante v = new Visitante("V" + i, "ID" + i, 20, 1.70f, 10000f);
            controller.registrarVisitante(v);
            controller.registrarIngresoParque();
        }
        assertTrue(controller.puedeIngresarAlParque());
        
        controller.registrarIngresoParque();
        assertFalse(controller.puedeIngresarAlParque());
    }

    @Test
    void testRegistrarIngresoYSalidaParque() {
        int espaciosInicial = controller.getEspaciosDisponiblesParque();
        
        controller.registrarIngresoParque();
        assertEquals(espaciosInicial - 1, controller.getEspaciosDisponiblesParque());
        
        controller.registrarSalidaParque();
        assertEquals(espaciosInicial, controller.getEspaciosDisponiblesParque());
    }

    @Test
    void testGetEspaciosDisponiblesParque() {
        assertEquals(1000, controller.getEspaciosDisponiblesParque());
    }

    @Test
    void testBuscarVisitantePorCedula() {
        controller.registrarVisitante(visitante);
        Visitante encontrado = controller.buscarVisitantePorCedula("222");
        assertNotNull(encontrado);
        assertEquals("Ana", encontrado.getNombre());
        
        Visitante noEncontrado = controller.buscarVisitantePorCedula("999");
        assertNull(noEncontrado);
    }
}
