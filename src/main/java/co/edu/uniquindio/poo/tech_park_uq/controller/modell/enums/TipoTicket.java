package co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums;

public enum TipoTicket {
    NINGUNO("Sin Ticket", 0, 0, false),
    GENERAL("Ticket General", 50000, 0, false),
    FAMILIAR("Ticket Familiar", 150000, 25, false),
    FAST_PASS("Fast-Pass", 80000, 0, true);

    private final String nombre;
    private final float precio;
    private final int descuentoPuntos; // Porcentaje de descuento
    private final boolean tienePrioridad;

    TipoTicket(String nombre, float precio, int descuentoPuntos, boolean tienePrioridad) {
        this.nombre = nombre;
        this.precio = precio;
        this.descuentoPuntos = descuentoPuntos;
        this.tienePrioridad = tienePrioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getDescuentoPuntos() {
        return descuentoPuntos;
    }

    public boolean tienePrioridad() {
        return tienePrioridad;
    }

    public float calcularPrecioPuntos(int cantidadPuntos, float precioBase) {
        if (descuentoPuntos > 0) {
            return precioBase * (1 - descuentoPuntos / 100.0f);
        }
        return precioBase;
    }
}
