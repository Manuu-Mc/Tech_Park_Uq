package co.edu.uniquindio.poo.tech_park_uq.controller.modell.records;

import java.time.LocalDate;

public record TicketData(String id, String tipoTicket, float precioPagado, LocalDate fechaCompra) {
}
