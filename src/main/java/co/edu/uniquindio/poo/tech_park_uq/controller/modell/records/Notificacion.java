package co.edu.uniquindio.poo.tech_park_uq.controller.modell.records;

import co.edu.uniquindio.poo.techparkuq.model.enums.TipoNotificacion;

import java.time.LocalDateTime;

public record Notificacion(
        String mensaje,
        LocalDateTime fechaHora,
        TipoNotificacion formatoNotificacion
) {
}
