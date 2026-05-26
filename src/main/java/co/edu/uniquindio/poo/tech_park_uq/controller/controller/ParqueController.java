package co.edu.uniquindio.poo.tech_park_uq.controller.controller;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ParqueController {

    private List<Visitante> visitantes;
    private List<Atraccion> atracciones;
    private List<Operador> operadores;
    private List<Administrador> administradores;
    private List<Zona> zonas;
    private ObservableList<SolicitudAcceso> solicitudesPendientes;
    private int aforoMaximoParque;
    private int visitantesEnParque;

    public ParqueController() {
        visitantes = new ArrayList<>();
        atracciones = new ArrayList<>();
        operadores = new ArrayList<>();
        administradores = new ArrayList<>();
        zonas = new ArrayList<>();
        solicitudesPendientes = FXCollections.observableArrayList();
        aforoMaximoParque = 1000; // Capacidad máxima del parque
        visitantesEnParque = 0;
    }

    public boolean puedeIngresarAlParque() {
        return visitantesEnParque < aforoMaximoParque;
    }

    public boolean registrarIngresoParque(Visitante visitante) {
        if (puedeIngresarAlParque()) {
            visitantesEnParque++;
            return true;
        }
        return false;
    }

    public void registrarIngresoParque() {
        if (puedeIngresarAlParque()) {
            visitantesEnParque++;
        }
    }

    public void registrarSalidaParque(Visitante visitante) {
        if (visitantesEnParque > 0) {
            visitantesEnParque--;
        }
    }

    public void registrarSalidaParque() {
        if (visitantesEnParque > 0) {
            visitantesEnParque--;
        }
    }

    public int getEspaciosDisponiblesParque() {
        return aforoMaximoParque - visitantesEnParque;
    }

    public void agregarZona(Zona zona) {
        zonas.add(zona);
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void agregarSolicitud(SolicitudAcceso solicitud) {
        solicitudesPendientes.add(solicitud);
    }

    public void removerSolicitud(SolicitudAcceso solicitud) {
        solicitudesPendientes.remove(solicitud);
    }

    public ObservableList<SolicitudAcceso> getSolicitudesPendientes() {
        return solicitudesPendientes;
    }

    public void registrarOperador(Operador operador) {
        operadores.add(operador);
    }

    public List<Operador> getOperadores() {
        return operadores;
    }

    public void registrarVisitante(Visitante visitante){
        visitantes.add(visitante);
    }

    public void agregarAtraccion(Atraccion atraccion){
        atracciones.add(atraccion);
    }

    public List<Visitante> getVisitantes() {
        return visitantes;
    }

    public List<Atraccion> getAtracciones() {
        return atracciones;
    }

    public void registrarAdministrador(Administrador administrador) {
        administradores.add(administrador);
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public int getAforoMaximoParque() {
        return aforoMaximoParque;
    }

    public int getVisitantesEnParque() {
        return visitantesEnParque;
    }

    public Visitante buscarVisitantePorCedula(String cedula) {
        return visitantes.stream()
            .filter(v -> v.getCedula().equals(cedula))
            .findFirst()
            .orElse(null);
    }
}
