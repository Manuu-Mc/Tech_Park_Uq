package co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties;

import co.edu.uniquindio.poo.techparkuq.model.abstracts.Empleado;
import co.edu.uniquindio.poo.techparkuq.model.enums.EspecialidadOperador;
import co.edu.uniquindio.poo.techparkuq.model.enums.EstadoActual;

public class Operador extends Empleado {

    private static final long serialVersionUID = 1L;

    private Zona zonaAsignada;
    private EspecialidadOperador especialidad;

    public Operador(String nombre,
                    String cedula,
                    int edad,
                    String idEmpleado,
                    Zona zonaAsignada,
                    EspecialidadOperador especialidad) {

        super(nombre, cedula, edad, idEmpleado);
        this.zonaAsignada = zonaAsignada;
        this.especialidad = especialidad;
    }

    // Constructor legacy (sin especialidad) - asigna GENERAL por defecto
    public Operador(String nombre,
                    String cedula,
                    int edad,
                    String idEmpleado,
                    Zona zonaAsignada) {
        this(nombre, cedula, edad, idEmpleado, zonaAsignada, EspecialidadOperador.GENERAL);
    }

    public boolean validarAcceso(Visitante visitante, Atraccion atraccion){

        return atraccion.verificarRequisitosVisitantes(visitante);
    }

    public void cambiarEstadoAtraccion(Atraccion atraccion,
                                       EstadoActual estado){

        atraccion.setEstadoActual(estado);
    }

    public void registrarRevisionTecnica(Atraccion atraccion){

        atraccion.setEstadoActual(EstadoActual.ACTIVA);
    }

    public Zona getZonaAsignada() {
        return zonaAsignada;
    }

    public EspecialidadOperador getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadOperador especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Verifica si este operador puede gestionar una atracción específica
     */
    public boolean puedeGestionar(Atraccion atraccion) {
        return especialidad.puedeGestionar(atraccion.getTipoAtraccion());
    }

    @Override
    public String toString() {
        return nombre + " (" + especialidad.getNombre() + ")";
    }
}
