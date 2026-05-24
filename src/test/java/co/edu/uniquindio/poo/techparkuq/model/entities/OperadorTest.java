package co.edu.uniquindio.poo.techparkuq.model.entities;

import co.edu.uniquindio.poo.techparkuq.model.enums.EspecialidadOperador;
import co.edu.uniquindio.poo.techparkuq.model.enums.EstadoActual;
import co.edu.uniquindio.poo.techparkuq.model.enums.TipoAtraccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperadorTest {

    private Operador operador;
    private Zona zona;
    private Atraccion atraccion;
    private Visitante visitante;

    @BeforeEach
    void setUp() {
        zona = new Zona("Zona Aventura", 100);
        operador = new Operador("Roberto", "555", 28, "OP001", zona, EspecialidadOperador.MECANICA);
        atraccion = new Atraccion("ATR001", "Montaña Rusa", 20, 1.40f, 12, 5000f, TipoAtraccion.MECANICA_ALTURA);
        visitante = new Visitante("Sofia", "666", 16, 1.55f, 20000f);
    }

    @Test
    void testCreacionOperador() {
        assertNotNull(operador);
        assertEquals("Roberto", operador.getNombre());
        assertEquals("OP001", operador.getIdEmpleado());
        assertEquals(zona, operador.getZonaAsignada());
        assertEquals(EspecialidadOperador.MECANICA, operador.getEspecialidad());
    }

    @Test
    void testValidarAccesoExitoso() {
        assertTrue(operador.validarAcceso(visitante, atraccion));
    }

    @Test
    void testValidarAccesoFallidoPorEdad() {
        Visitante visitanteJoven = new Visitante("Niño", "777", 8, 1.50f, 10000f);
        assertFalse(operador.validarAcceso(visitanteJoven, atraccion));
    }

    @Test
    void testValidarAccesoFallidoPorAltura() {
        Visitante visitanteBajo = new Visitante("Bajo", "888", 15, 1.30f, 10000f);
        assertFalse(operador.validarAcceso(visitanteBajo, atraccion));
    }

    @Test
    void testRegistrarRevisionTecnica() {
        atraccion.setEstadoActual(EstadoActual.EN_MANTENIMIENTO);
        for (int i = 0; i < 500; i++) {
            atraccion.registrarIngreso();
        }
        
        operador.registrarRevisionTecnica(atraccion);
        
        assertEquals(EstadoActual.ACTIVA, atraccion.getEstadoActual());
        assertEquals(0, atraccion.getContadorVisitantes());
    }

    @Test
    void testCambiarEstadoAtraccion() {
        operador.cambiarEstadoAtraccion(atraccion, EstadoActual.CERRADO);
        assertEquals(EstadoActual.CERRADO, atraccion.getEstadoActual());
        
        operador.cambiarEstadoAtraccion(atraccion, EstadoActual.ACTIVA);
        assertEquals(EstadoActual.ACTIVA, atraccion.getEstadoActual());
    }

    @Test
    void testPuedeGestionarAtraccionMecanica() {
        Atraccion atraccionMecanica = new Atraccion("ATR002", "Rueda", 30, 1.20f, 5, 0f, TipoAtraccion.MECANICA_ALTURA);
        assertTrue(operador.puedeGestionar(atraccionMecanica));
    }

    @Test
    void testPuedeGestionarAtraccionAcuatica() {
        Operador operadorAcuatico = new Operador("Ana", "999", 25, "OP002", zona, EspecialidadOperador.ACUATICA);
        Atraccion atraccionAcuatica = new Atraccion("ATR003", "Tobogán", 15, 1.30f, 8, 2000f, TipoAtraccion.ACUATICA);
        assertTrue(operadorAcuatico.puedeGestionar(atraccionAcuatica));
    }

    @Test
    void testOperadorGeneralPuedeGestionarTodo() {
        Operador operadorGeneral = new Operador("Juan", "1010", 30, "OP003", zona, EspecialidadOperador.GENERAL);
        
        Atraccion mecanica = new Atraccion("ATR004", "Montaña", 20, 1.40f, 12, 5000f, TipoAtraccion.MECANICA_ALTURA);
        Atraccion acuatica = new Atraccion("ATR005", "Piscina", 50, 1.0f, 5, 0f, TipoAtraccion.ACUATICA);
        Atraccion infantil = new Atraccion("ATR006", "Carrusel", 30, 0.9f, 3, 0f, TipoAtraccion.INFANTIL);
        
        assertTrue(operadorGeneral.puedeGestionar(mecanica));
        assertTrue(operadorGeneral.puedeGestionar(acuatica));
        assertTrue(operadorGeneral.puedeGestionar(infantil));
    }
}
