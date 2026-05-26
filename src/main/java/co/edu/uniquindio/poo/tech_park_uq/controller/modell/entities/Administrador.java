package co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.abstracts.Empleado;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoActual;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoAtraccion;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Empleado {

    private static final long serialVersionUID = 1L;

    private float sueldo;

    @SuppressWarnings("serial")
    private List<Empleado> empleados;
    @SuppressWarnings("serial")
    private List<Zona> zonas;
    @SuppressWarnings("serial")
    private List<Atraccion> atracciones;

    public Administrador(String nombre,
                         String cedula,
                         int edad,
                         String idEmpleado,
                         float sueldo) {

        super(nombre, cedula, edad, idEmpleado);

        this.sueldo = sueldo;

        empleados = new ArrayList<>();
        zonas = new ArrayList<>();
        atracciones = new ArrayList<>();
    }

    public void gestionarEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    public void gestionarZonas(Zona zona){
        zonas.add(zona);
    }

    public void gestionarAtracciones(Atraccion atraccion){
        atracciones.add(atraccion);
    }

    public void activarAlertaClimatica(){

        for(Atraccion atraccion : atracciones){

            if(atraccion.getTipoAtraccion() == TipoAtraccion.ACUATICA
                    || atraccion.getTipoAtraccion() == TipoAtraccion.MECANICA_ALTURA){

                atraccion.setEstadoActual(EstadoActual.CERRADO);
            }
        }
    }

    public void generarReportes(){
        System.out.println("Reporte generado");
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<Atraccion> getAtracciones() {
        return atracciones;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }
}
