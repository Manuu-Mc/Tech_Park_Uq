package co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.abstracts.Persona;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoTicket;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.interfaces.INotificable;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.records.Notificacion;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.records.RegistroAcceso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visitante extends Persona implements INotificable {

    private static final long serialVersionUID = 1L;

    private float estatura;
    private float saldoVirtual;
    private int puntosTicket;
    private TipoTicket tipoTicket;
    private String rutaFotoPerfil;

    @SuppressWarnings("serial")
    private List<Atraccion> favoritos;
    @SuppressWarnings("serial")
    private Map<String, Integer> contadorIngresosPorAtraccion;
    @SuppressWarnings("serial")
    private List<RegistroAcceso> historialAccesos;
    @SuppressWarnings("serial")
    private List<Notificacion> notificaciones;

    public Visitante(String nombre,
                     String cedula,
                     int edad,
                     float estatura,
                     float saldoVirtual) {

        super(nombre, cedula, edad);

        this.estatura = estatura;
        this.saldoVirtual = saldoVirtual;
        this.puntosTicket = 0;
        this.tipoTicket = TipoTicket.NINGUNO;
        this.rutaFotoPerfil = null;

        favoritos = new ArrayList<>();
        contadorIngresosPorAtraccion = new HashMap<>();
        historialAccesos = new ArrayList<>();
        notificaciones = new ArrayList<>();
    }

    public void comprarTicket(TipoTicket tipo) {
        this.tipoTicket = tipo;
    }

    public TipoTicket getTipoTicket() {
        return tipoTicket;
    }

    public boolean tieneTicket() {
        return tipoTicket != TipoTicket.NINGUNO;
    }

    public boolean tieneFastPass() {
        return tipoTicket == TipoTicket.FAST_PASS;
    }

    public int getPrioridadCola() {
        return tieneFastPass() ? 1 : 2;
    }

    public int getDescuentoPuntos() {
        return tipoTicket.getDescuentoPuntos();
    }

    public float calcularPrecioPuntos(int cantidadPuntos) {
        float precioBase = cantidadPuntos * 100; // $100 por punto
        return tipoTicket.calcularPrecioPuntos(cantidadPuntos, precioBase);
    }

    public void activarFastPass() {
        this.tipoTicket = TipoTicket.FAST_PASS;
    }

    public boolean tieneSaldoSuficiente(float monto) {
        return saldoVirtual >= monto;
    }

    public boolean descontarSaldo(float monto) {
        if (tieneSaldoSuficiente(monto)) {
            saldoVirtual -= monto;
            return true;
        }
        return false;
    }

    public void comprarTicketVirtual(){

    }

    public void consultarMapaParque(){

    }

    public void consultarTiempoEspera(){

    }

    public void registrarAtraccionFavorita(Atraccion atraccion){
        if (!favoritos.contains(atraccion)) {
            favoritos.add(atraccion);
        }
    }

    public void eliminarAtraccionFavorita(Atraccion atraccion){
        favoritos.remove(atraccion);
    }

    public boolean usarPuntosParaAtraccion(int costoEnPuntos) {
        if (puntosTicket >= costoEnPuntos) {
            puntosTicket -= costoEnPuntos;
            return true;
        }
        return false;
    }

    public void agregarPuntos(int puntos) {
        this.puntosTicket += puntos;
    }

    public void registrarIngresoAtraccion(Atraccion atraccion) {
        String nombreAtraccion = atraccion.getNombre();
        contadorIngresosPorAtraccion.put(nombreAtraccion, 
            contadorIngresosPorAtraccion.getOrDefault(nombreAtraccion, 0) + 1);
    }

    public void agregarRegistroAcceso(RegistroAcceso registro) {
        historialAccesos.add(registro);
    }

    public int getIngresosAtraccion(String nombreAtraccion) {
        return contadorIngresosPorAtraccion.getOrDefault(nombreAtraccion, 0);
    }

    public int getTotalIngresos() {
        return contadorIngresosPorAtraccion.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    @Override
    public void recibirNotificacion(Notificacion notificacion) {
        notificaciones.add(notificacion);
        System.out.println("[" + nombre + "] " + notificacion.mensaje());
    }

    public float getEstatura() {
        return estatura;
    }

    public float getSaldoVirtual() {
        return saldoVirtual;
    }

    public void setSaldoVirtual(float saldoVirtual) {
        this.saldoVirtual = saldoVirtual;
    }

    public int getPuntosTicket() {
        return puntosTicket;
    }

    public void setPuntosTicket(int puntosTicket) {
        this.puntosTicket = puntosTicket;
    }

    public List<Atraccion> getFavoritos() {
        return favoritos;
    }

    public Map<String, Integer> getContadorIngresosPorAtraccion() {
        return contadorIngresosPorAtraccion;
    }

    public List<RegistroAcceso> getHistorialAccesos() {
        return historialAccesos;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }
    
    public String getRutaFotoPerfil() {
        return rutaFotoPerfil;
    }
    
    public void setRutaFotoPerfil(String rutaFotoPerfil) {
        this.rutaFotoPerfil = rutaFotoPerfil;
    }
    
    public boolean tieneFotoPerfil() {
        return rutaFotoPerfil != null && !rutaFotoPerfil.isEmpty();
    }
}
