package co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties;

import co.edu.uniquindio.poo.techparkuq.model.abstracts.Ticket;

public class TicketFastPass extends Ticket {

    private static final long serialVersionUID = 1L;

    public TicketFastPass(float valor) {
        super(valor, true);
    }
}
