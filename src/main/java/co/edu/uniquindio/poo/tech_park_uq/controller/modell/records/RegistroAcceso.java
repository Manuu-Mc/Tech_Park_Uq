package co.edu.uniquindio.poo.tech_park_uq.controller.modell.records;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoAcceso;

import java.time.LocalDateTime;

public record RegistroAcceso(
    String nombreAtraccion,
    EstadoAcceso estado,
    String motivo,
    LocalDateTime fechaHora,
    int puntosUsados
) {
}
