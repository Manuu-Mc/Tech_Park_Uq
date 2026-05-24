package co.edu.uniquindio.poo.techparkuq.controller;

import co.edu.uniquindio.poo.techparkuq.model.entities.Atraccion;
import co.edu.uniquindio.poo.techparkuq.model.entities.Visitante;
import co.edu.uniquindio.poo.techparkuq.model.enums.TipoAtraccion;
import co.edu.uniquindio.poo.techparkuq.model.enums.TipoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitanteControllerTest {

    private VisitanteController controller;
    private Visitante visitante;
    private ParqueController parqueController;
    private Atraccion atraccion;

    @BeforeEach
    void setUp() {
        parqueController = new ParqueController();
        visitante = new Visitante("Carlos", "111", 25, 1.75f, 100000f);
        controller = new VisitanteController(visitante, parqueController);
        
        atraccion = new Atraccion("ATR001", "Montaña", 20, 1.40f, 12, 3000f, TipoAtraccion.MECANICA_ALTURA);
        parqueController.agregarAtraccion(atraccion);
    }

    @Test
    void testRecargarSaldo() {
        float saldoInicial = visitante.getSaldoVirtual();
        controller.recargarSaldo(50000f);
        assertEquals(saldoInicial + 50000f, visitante.getSaldoVirtual());
    }

    @Test
    void testComprarTicketGeneral() {
        float saldoInicial = visitante.getSaldoVirtual();
        controller.comprarTicketGeneral();
        
        assertEquals(TipoTicket.GENERAL, visitante.getTipoTicket());
        assertEquals(saldoInicial - 50000f, visitante.getSaldoVirtual());
    }

    @Test
    void testComprarTicketFamiliar() {
        float saldoInicial = visitante.getSaldoVirtual();
        controller.comprarTicketFamiliar();
        
        assertEquals(TipoTicket.FAMILIAR, visitante.getTipoTicket());
        assertEquals(saldoInicial - 150000f, visitante.getSaldoVirtual());
    }

    @Test
    void testComprarTicketFastPass() {
        float saldoInicial = visitante.getSaldoVirtual();
        controller.comprarTicketFastPass();
        
        assertEquals(TipoTicket.FAST_PASS, visitante.getTipoTicket());
        assertTrue(visitante.tieneFastPass());
        assertEquals(saldoInicial - 80000f, visitante.getSaldoVirtual());
    }

    @Test
    void testComprarPuntosSinTicket() {
        int puntosInicial = visitante.getPuntosTicket();
        controller.comprarPuntos(50);
        
        assertEquals(puntosInicial, visitante.getPuntosTicket());
    }

    @Test
    void testComprarPuntosConTicket() {
        visitante.comprarTicket(TipoTicket.GENERAL);
        visitante.setSaldoVirtual(10000f);
        
        controller.comprarPuntos(50);
        assertEquals(50, visitante.getPuntosTicket());
        assertEquals(5000f, visitante.getSaldoVirtual());
    }

    @Test
    void testComprarPuntosConDescuento() {
        visitante.comprarTicket(TipoTicket.FAMILIAR);
        visitante.setSaldoVirtual(10000f);
        
        controller.comprarPuntos(100);
        assertEquals(100, visitante.getPuntosTicket());
        assertEquals(2500f, visitante.getSaldoVirtual()); // 7500 descontados (25% descuento)
    }

    @Test
    void testAgregarAtraccionFavorita() {
        controller.agregarAtraccionFavorita(atraccion);
        assertTrue(visitante.getFavoritos().contains(atraccion));
    }

    @Test
    void testEliminarAtraccionFavorita() {
        visitante.registrarAtraccionFavorita(atraccion);
        controller.eliminarAtraccionFavorita(atraccion);
        assertFalse(visitante.getFavoritos().contains(atraccion));
    }

    @Test
    void testGetAtraccionesDisponibles() {
        assertNotNull(controller.getAtraccionesDisponibles());
        assertEquals(1, controller.getAtraccionesDisponibles().size());
    }

    @Test
    void testGetVisitante() {
        assertEquals(visitante, controller.getVisitante());
    }
}
