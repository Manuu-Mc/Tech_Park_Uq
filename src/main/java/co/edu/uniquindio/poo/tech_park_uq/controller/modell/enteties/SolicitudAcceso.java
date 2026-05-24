package co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoAcceso;

import java.time.LocalDateTime;

public class SolicitudAcceso {

    private final Visitante visitante;
    private final Atraccion atraccion;
    private final int puntosDescontados;
    private final LocalDateTime fechaHora;
    private EstadoAcceso estado;

    public SolicitudAcceso(Visitante visitante, Atraccion atraccion, int puntosDescontados) {
        this.visitante = visitante;
        this.atraccion = atraccion;
        this.puntosDescontados = puntosDescontados;
        this.fechaHora = LocalDateTime.now();
        this.estado = EstadoAcceso.EN_PROCESO;
    }

    public Visitante getVisitante() { return visitante; }
    public Atraccion getAtraccion() { return atraccion; }
    public int getPuntosDescontados() { return puntosDescontados; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public EstadoAcceso getEstado() { return estado; }
    public void setEstado(EstadoAcceso estado) { this.estado = estado; }

    /** Texto para mostrar en la tabla del operador */
    public String getNombreVisitante() { return visitante.getNombre(); }
    public String getCedulaVisitante() { return visitante.getCedula(); }
    public String getNombreAtraccion() { return atraccion.getNombre(); }
    public String getEstadoTexto() { return estado.name(); }
    public boolean tieneFastPass() { return visitante.tieneFastPass(); }
    public String getTipoTicket() { return visitante.getTipoTicket().getNombre(); }
    public int getPrioridad() { return visitante.getPrioridadCola(); }
}
