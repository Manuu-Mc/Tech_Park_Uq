package co.edu.uniquindio.poo.tech_park_uq.controller.modell.abstracts;

public abstract class Empleado extends Persona {

    private static final long serialVersionUID = 1L;

    protected String idEmpleado;
    protected String rutaFotoPerfil;

    public Empleado(String nombre, String cedula, int edad, String idEmpleado) {
        super(nombre, cedula, edad);
        this.idEmpleado = idEmpleado;
        this.rutaFotoPerfil = null;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public String getRutaFotoPerfil() {
        return rutaFotoPerfil;
    }

    public void setRutaFotoPerfil(String rutaFotoPerfil) {
        this.rutaFotoPerfil = rutaFotoPerfil;
    }

    public boolean tieneFotoPerfil() {
        return rutaFotoPerfil != null && !rutaFotoPerfil.isEmpty();
    }
}
