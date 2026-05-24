package co.edu.uniquindio.poo.techparkuq.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoTicketTest {

    @Test
    void testNombresTipoTicket() {
        assertEquals("Sin Ticket", TipoTicket.NINGUNO.getNombre());
        assertEquals("General", TipoTicket.GENERAL.getNombre());
        assertEquals("Familiar", TipoTicket.FAMILIAR.getNombre());
        assertEquals("Fast-Pass", TipoTicket.FAST_PASS.getNombre());
    }

    @Test
    void testDescuentosPuntos() {
        assertEquals(0, TipoTicket.NINGUNO.getDescuentoPuntos());
        assertEquals(0, TipoTicket.GENERAL.getDescuentoPuntos());
        assertEquals(25, TipoTicket.FAMILIAR.getDescuentoPuntos());
        assertEquals(0, TipoTicket.FAST_PASS.getDescuentoPuntos());
    }

    @Test
    void testCalcularPrecioPuntosSinDescuento() {
        float precioBase = 10000f;
        
        assertEquals(10000f, TipoTicket.NINGUNO.calcularPrecioPuntos(100, precioBase));
        assertEquals(10000f, TipoTicket.GENERAL.calcularPrecioPuntos(100, precioBase));
        assertEquals(10000f, TipoTicket.FAST_PASS.calcularPrecioPuntos(100, precioBase));
    }

    @Test
    void testCalcularPrecioPuntosConDescuento() {
        float precioBase = 10000f;
        float precioEsperado = 7500f;
        
        assertEquals(precioEsperado, TipoTicket.FAMILIAR.calcularPrecioPuntos(100, precioBase));
    }

    @Test
    void testCalcularPrecioPuntosVariasCantidades() {
        assertEquals(5000f, TipoTicket.GENERAL.calcularPrecioPuntos(50, 5000f));
        assertEquals(3750f, TipoTicket.FAMILIAR.calcularPrecioPuntos(50, 5000f));
        assertEquals(20000f, TipoTicket.FAST_PASS.calcularPrecioPuntos(200, 20000f));
    }
}
