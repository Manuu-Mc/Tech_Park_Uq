package co.edu.uniquindio.poo.tech_park_uq.controller.modell.abstracts;

import java.io.Serializable;

public abstract class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    protected float valor;
    protected boolean tienePrioridadCola;

    public Ticket(float valor, boolean tienePrioridadCola) {
        this.valor = valor;
        this.tienePrioridadCola = tienePrioridadCola;
    }

    public float getValor() {
        return valor;
    }

    public boolean isTienePrioridadCola() {
        return tienePrioridadCola;
    }
}
