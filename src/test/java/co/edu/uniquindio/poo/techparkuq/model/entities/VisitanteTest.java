package co.edu.uniquindio.poo.techparkuq.model.entities;

import co.edu.uniquindio.poo.techparkuq.model.enums.TipoAtraccion;
import co.edu.uniquindio.poo.techparkuq.model.enums.TipoTicket;
import co.edu.uniquindio.poo.techparkuq.model.enums.TipoNotificacion;
import co.edu.uniquindio.poo.techparkuq.model.records.Notificacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VisitanteTest {

    private Visitante visitante;
    private Atraccion atraccion;

    @BeforeEach
    void setUp() {
        visitante = new Visitante("Maria Lopez", "987654", 20, 1.65f, 50000f);
        atraccion = new Atraccion("ATR001", "Rueda", 30, 1.20f, 5, 0f, TipoAtraccion.INFANTIL);
    }

    @Test
    void testCreacionVisitante() {
        assertNotNull(visitante);
        assertEquals("Maria Lopez", visitante.getNombre());
        assertEquals(50000f, visitante.getSaldoVirtual());
        assertEquals(0, visitante.getPuntosTicket());
        assertEquals(TipoTicket.NINGUNO, visitante.getTipoTicket());
        assertFalse(visitante.tieneFotoPerfil());
    }

    @Test
    void testComprarTicketGeneral() {
        visitante.comprarTicket(TipoTicket.GENERAL);
        assertEquals(TipoTicket.GENERAL, visitante.getTipoTicket());
        assertTrue(visitante.tieneTicket());
        assertFalse(visitante.tieneFastPass());
        assertEquals(0, visitante.getDescuentoPuntos());
    }

    @Test
    void testComprarTicketFamiliar() {
        visitante.comprarTicket(TipoTicket.FAMILIAR);
        assertEquals(TipoTicket.FAMILIAR, visitante.getTipoTicket());
        assertTrue(visitante.tieneTicket());
        assertEquals(25, visitante.getDescuentoPuntos());
    }

    @Test
    void testComprarTicketFastPass() {
        visitante.comprarTicket(TipoTicket.FAST_PASS);
        assertTrue(visitante.tieneFastPass());
        assertEquals(1, visitante.getPrioridadCola());
    }

    @Test
    void testCalcularPrecioPuntosSinDescuento() {
        visitante.comprarTicket(TipoTicket.GENERAL);
        float precio = visitante.calcularPrecioPuntos(100);
        assertEquals(10000f, precio);
    }

    @Test
    void testCalcularPrecioPuntosConDescuento() {
        visitante.comprarTicket(TipoTicket.FAMILIAR);
        float precio = visitante.calcularPrecioPuntos(100);
        assertEquals(7500f, precio);
    }

    @Test
    void testAgregarPuntos() {
        visitante.agregarPuntos(50);
        assertEquals(50, visitante.getPuntosTicket());
        
        visitante.agregarPuntos(30);
        assertEquals(80, visitante.getPuntosTicket());
    }

    @Test
    void testUsarPuntosParaAtraccion() {
        visitante.agregarPuntos(100);
        
        assertTrue(visitante.usarPuntosParaAtraccion(30));
        assertEquals(70, visitante.getPuntosTicket());
        
        assertFalse(visitante.usarPuntosParaAtraccion(100));
        assertEquals(70, visitante.getPuntosTicket());
    }

    @Test
    void testTieneSaldoSuficiente() {
        assertTrue(visitante.tieneSaldoSuficiente(30000f));
        assertFalse(visitante.tieneSaldoSuficiente(60000f));
    }

    @Test
    void testDescontarSaldo() {
        assertTrue(visitante.descontarSaldo(20000f));
        assertEquals(30000f, visitante.getSaldoVirtual());
        
        assertFalse(visitante.descontarSaldo(40000f));
        assertEquals(30000f, visitante.getSaldoVirtual());
    }

    @Test
    void testRegistrarAtraccionFavorita() {
        visitante.registrarAtraccionFavorita(atraccion);
        assertEquals(1, visitante.getFavoritos().size());
        assertTrue(visitante.getFavoritos().contains(atraccion));
        
        visitante.registrarAtraccionFavorita(atraccion);
        assertEquals(1, visitante.getFavoritos().size());
    }

    @Test
    void testEliminarAtraccionFavorita() {
        visitante.registrarAtraccionFavorita(atraccion);
        visitante.eliminarAtraccionFavorita(atraccion);
        assertEquals(0, visitante.getFavoritos().size());
    }

    @Test
    void testRegistrarIngresoAtraccion() {
        visitante.registrarIngresoAtraccion(atraccion);
        assertEquals(1, visitante.getIngresosAtraccion("Rueda"));
        
        visitante.registrarIngresoAtraccion(atraccion);
        assertEquals(2, visitante.getIngresosAtraccion("Rueda"));
    }

    @Test
    void testGetTotalIngresos() {
        Atraccion atraccion2 = new Atraccion("ATR002", "Carrusel", 20, 1.0f, 3, 0f, TipoAtraccion.INFANTIL);
        
        visitante.registrarIngresoAtraccion(atraccion);
        visitante.registrarIngresoAtraccion(atraccion);
        visitante.registrarIngresoAtraccion(atraccion2);
        
        assertEquals(3, visitante.getTotalIngresos());
    }

    @Test
    void testRecibirNotificacion() {
        Notificacion notif = new Notificacion(
            "Alerta de prueba",
            LocalDateTime.now(),
            TipoNotificacion.ALERTA
        );
        
        visitante.recibirNotificacion(notif);
        assertEquals(1, visitante.getNotificaciones().size());
        assertEquals("Alerta de prueba", visitante.getNotificaciones().get(0).mensaje());
    }

    @Test
    void testPrioridadCola() {
        assertEquals(2, visitante.getPrioridadCola());
        
        visitante.comprarTicket(TipoTicket.FAST_PASS);
        assertEquals(1, visitante.getPrioridadCola());
    }
    
    @Test
    void testFotoPerfil() {
        assertNull(visitante.getRutaFotoPerfil());
        assertFalse(visitante.tieneFotoPerfil());
        
        visitante.setRutaFotoPerfil("/ruta/foto.jpg");
        assertEquals("/ruta/foto.jpg", visitante.getRutaFotoPerfil());
        assertTrue(visitante.tieneFotoPerfil());
        
        visitante.setRutaFotoPerfil("");
        assertFalse(visitante.tieneFotoPerfil());
    }
}
