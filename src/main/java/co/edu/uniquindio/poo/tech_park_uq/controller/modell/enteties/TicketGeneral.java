package co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties;

import co.edu.uniquindio.poo.techparkuq.model.abstracts.Ticket;

public class TicketGeneral extends Ticket {

    private static final long serialVersionUID = 1L;

    private String ingresoNormal;

    public TicketGeneral(float valor) {
        super(valor, false);
    }
}
