package co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoAcceso;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoAtraccion;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudAccesoTest {

    private SolicitudAcceso solicitud;
    private Visitante visitante;
    private Atraccion atraccion;

    @BeforeEach
    void setUp() {
        visitante = new Visitante("Luis", "444", 22, 1.70f, 30000f);
        atraccion = new Atraccion("ATR001", "Tobogán", 25, 1.30f, 8, 2000f, TipoAtraccion.ACUATICA);
        solicitud = new SolicitudAcceso(visitante, atraccion, 30);
    }

    @Test
    void testCreacionSolicitud() {
        assertNotNull(solicitud);
        assertEquals(visitante, solicitud.getVisitante());
        assertEquals(atraccion, solicitud.getAtraccion());
        assertEquals(30, solicitud.getPuntosDescontados());
        assertEquals(EstadoAcceso.EN_PROCESO, solicitud.getEstado());
        assertNotNull(solicitud.getFechaHora());
    }

    @Test
    void testGetNombreVisitante() {
        assertEquals("Luis", solicitud.getNombreVisitante());
    }

    @Test
    void testGetCedulaVisitante() {
        assertEquals("444", solicitud.getCedulaVisitante());
    }

    @Test
    void testGetNombreAtraccion() {
        assertEquals("Tobogán", solicitud.getNombreAtraccion());
    }

    @Test
    void testGetEstadoTexto() {
        assertEquals("EN_PROCESO", solicitud.getEstadoTexto());
        
        solicitud.setEstado(EstadoAcceso.APROBADO);
        assertEquals("APROBADO", solicitud.getEstadoTexto());
    }

    @Test
    void testTieneFastPassFalse() {
        assertFalse(solicitud.tieneFastPass());
    }

    @Test
    void testTieneFastPassTrue() {
        visitante.comprarTicket(TipoTicket.FAST_PASS);
        SolicitudAcceso solicitudFastPass = new SolicitudAcceso(visitante, atraccion, 30);
        assertTrue(solicitudFastPass.tieneFastPass());
    }

    @Test
    void testGetTipoTicket() {
        assertEquals("Sin Ticket", solicitud.getTipoTicket());
        
        visitante.comprarTicket(TipoTicket.GENERAL);
        SolicitudAcceso solicitudConTicket = new SolicitudAcceso(visitante, atraccion, 30);
        assertEquals("Ticket General", solicitudConTicket.getTipoTicket());
    }

    @Test
    void testGetPrioridad() {
        assertEquals(2, solicitud.getPrioridad()); // Sin Fast-Pass
        
        visitante.comprarTicket(TipoTicket.FAST_PASS);
        SolicitudAcceso solicitudFastPass = new SolicitudAcceso(visitante, atraccion, 30);
        assertEquals(1, solicitudFastPass.getPrioridad()); // Con Fast-Pass
    }

    @Test
    void testCambiarEstado() {
        solicitud.setEstado(EstadoAcceso.APROBADO);
        assertEquals(EstadoAcceso.APROBADO, solicitud.getEstado());
        
        solicitud.setEstado(EstadoAcceso.DENEGADO);
        assertEquals(EstadoAcceso.DENEGADO, solicitud.getEstado());
    }
}
