package co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoActual;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoAtraccion;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.interfaces.IValidable;

import java.io.Serializable;

public class Atraccion implements IValidable, Serializable {

    private static final long serialVersionUID = 1L;

    private String identificadorUnico;
    private String nombre;

    private int capacidadMaximaCiclo;

    private float alturaMinimaRequerida;
    private int edadMinimaRequerida;

    private float costoAdicional;

    private int contadorVisitantes;
    private int tiempoEspera;
    private int costoEnPuntos;
    private float ingresosTotales;
    private int visitasHoy;
    private int personasEnCola;

    private EstadoActual estadoActual;
    private TipoAtraccion tipoAtraccion;
    private Operador operadorAsignado;

    public Atraccion(String identificadorUnico,
                     String nombre,
                     int capacidadMaximaCiclo,
                     float alturaMinimaRequerida,
                     int edadMinimaRequerida,
                     float costoAdicional,
                     TipoAtraccion tipoAtraccion) {

        this.identificadorUnico = identificadorUnico;
        this.nombre = nombre;
        this.capacidadMaximaCiclo = capacidadMaximaCiclo;
        this.alturaMinimaRequerida = alturaMinimaRequerida;
        this.edadMinimaRequerida = edadMinimaRequerida;
        this.costoAdicional = costoAdicional;
        this.tipoAtraccion = tipoAtraccion;

        this.estadoActual = EstadoActual.ACTIVA;
        this.costoEnPuntos = calcularCostoEnPuntos(tipoAtraccion);
        this.ingresosTotales = 0;
        this.visitasHoy = 0;
        this.personasEnCola = 0;
        this.tiempoEspera = 0;
    }

    private int calcularCostoEnPuntos(TipoAtraccion tipo) {
        switch (tipo) {
            case MECANICA_ALTURA:
                return 50;
            case ACUATICA:
                return 30;
            case INFANTIL:
                return 20;
            default:
                return 25;
        }
    }

    @Override
    public boolean validarRequisitos() {
        return estadoActual == EstadoActual.ACTIVA;
    }

    public boolean verificarRequisitosVisitantes(Visitante visitante){

        return visitante.getEdad() >= edadMinimaRequerida
                && visitante.getEstatura() >= alturaMinimaRequerida
                && estadoActual == EstadoActual.ACTIVA;
    }

    public void actualizarTiempoEspera(int tiempo){
        this.tiempoEspera = tiempo;
    }
    
    public void agregarPersonaCola() {
        this.personasEnCola++;
        calcularTiempoEsperaReal();
    }
    
    public void agregarPersonaColaFastPass() {
        // Fast-Pass no aumenta el tiempo de espera
        this.personasEnCola++;
    }
    
    public void removerPersonaCola() {
        if (this.personasEnCola > 0) {
            this.personasEnCola--;
        }
        // Reducir 2 minutos a todos cuando alguien ingresa
        reducirTiempoEspera(2);
    }
    
    private void calcularTiempoEsperaReal() {
        // Cada persona que entra aumenta 2 minutos
        this.tiempoEspera += 2;
    }
    
    private void reducirTiempoEspera(int minutos) {
        this.tiempoEspera = Math.max(0, this.tiempoEspera - minutos);
    }
    
    public int getPersonasEnCola() {
        return personasEnCola;
    }

    public void registrarIngreso(){

        contadorVisitantes++;
        visitasHoy++;
        ingresosTotales += costoAdicional;

        ejecutarMantenimientoAutomatico();
    }

    public void ejecutarMantenimientoAutomatico(){

        if(contadorVisitantes >= 500){
            estadoActual = EstadoActual.EN_MANTENIMIENTO;
        }
    }

    public void notificarMantenimiento(){
        System.out.println("Atracción en mantenimiento");
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public EstadoActual getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoActual estadoActual) {
        this.estadoActual = estadoActual;
    }

    public TipoAtraccion getTipoAtraccion() {
        return tipoAtraccion;
    }

    public int getCostoEnPuntos() {
        return costoEnPuntos;
    }

    public int getEdadMinimaRequerida() {
        return edadMinimaRequerida;
    }

    public float getAlturaMinimaRequerida() {
        return alturaMinimaRequerida;
    }

    public Operador getOperadorAsignado() {
        return operadorAsignado;
    }

    public void setOperadorAsignado(Operador operadorAsignado) {
        this.operadorAsignado = operadorAsignado;
    }

    public String getIdentificadorUnico() {
        return identificadorUnico;
    }

    public int getContadorVisitantes() {
        return contadorVisitantes;
    }

    public float getIngresosTotales() {
        return ingresosTotales;
    }

    public int getVisitasHoy() {
        return visitasHoy;
    }

    public void resetearVisitasHoy() {
        this.visitasHoy = 0;
    }

    public void resetearContadorVisitantes() {
        this.contadorVisitantes = 0;
    }

    public float getCostoAdicional() {
        return costoAdicional;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
