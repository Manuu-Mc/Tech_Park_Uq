package co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums;

/**
 * Especialidades de los operadores del parque.
 * Cada operador tiene una especialidad que determina qué atracciones puede gestionar.
 */
public enum EspecialidadOperador {
    ACUATICA("Acuática"),
    MECANICA("Mecánica"),
    INFANTIL("Infantil"),
    GENERAL("General");

    private final String nombre;

    EspecialidadOperador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Verifica si esta especialidad puede gestionar un tipo de atracción específico
     */
    public boolean puedeGestionar(TipoAtraccion tipoAtraccion) {
        return switch (this) {
            case ACUATICA -> tipoAtraccion == TipoAtraccion.ACUATICA;
            case MECANICA -> tipoAtraccion == TipoAtraccion.MECANICA_ALTURA;
            case INFANTIL -> tipoAtraccion == TipoAtraccion.INFANTIL;
            case GENERAL -> true; // Puede gestionar cualquier tipo
        };
    }
}
