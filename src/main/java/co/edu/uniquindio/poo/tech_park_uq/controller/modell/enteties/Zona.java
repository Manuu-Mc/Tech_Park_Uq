package co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;

    protected int aforoMaximoZona;
    protected int visitantesActuales;
    protected String nombre;
    @SuppressWarnings("serial")
    protected List<Visitante> visitantesEnZona;

    public Zona(String nombre, int aforoMaximoZona) {
        this.aforoMaximoZona = aforoMaximoZona;
        this.nombre = nombre;
        this.visitantesActuales = 0;
        this.visitantesEnZona = new ArrayList<>();
    }

    public boolean puedeIngresarVisitante() {
        return visitantesActuales < aforoMaximoZona;
    }

    public boolean registrarIngresoVisitante(Visitante visitante) {
        if (puedeIngresarVisitante()) {
            visitantesActuales++;
            visitantesEnZona.add(visitante);
            return true;
        }
        return false;
    }

    public void registrarIngresoVisitante() {
        if (puedeIngresarVisitante()) {
            visitantesActuales++;
        }
    }

    public void registrarSalidaVisitante(Visitante visitante) {
        if (visitantesEnZona.contains(visitante)) {
            visitantesActuales--;
            visitantesEnZona.remove(visitante);
        }
    }

    public void registrarSalidaVisitante() {
        if (visitantesActuales > 0) {
            visitantesActuales--;
        }
    }

    public int getAforoMaximoZona() {
        return aforoMaximoZona;
    }

    public int getCapacidadMaxima() {
        return aforoMaximoZona;
    }

    public int getVisitantesActuales() {
        return visitantesActuales;
    }

    public int getEspaciosDisponibles() {
        return aforoMaximoZona - visitantesActuales;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Visitante> getVisitantesEnZona() {
        return visitantesEnZona;
    }
}
