package co.edu.uniquindio.poo.tech_park_uq.controller.controller;

import co.edu.uniquindio.poo.techparkuq.model.entities.Atraccion;
import co.edu.uniquindio.poo.techparkuq.model.entities.Operador;
import co.edu.uniquindio.poo.techparkuq.model.entities.SolicitudAcceso;
import co.edu.uniquindio.poo.techparkuq.model.entities.Visitante;
import co.edu.uniquindio.poo.techparkuq.model.enums.EstadoAcceso;
import co.edu.uniquindio.poo.techparkuq.model.enums.EstadoActual;
import co.edu.uniquindio.poo.techparkuq.model.records.RegistroAcceso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDateTime;

public class OperadorController {
    
    private Operador operador;
    private ParqueController parqueController;
    private ObservableList<Atraccion> atraccionesZona;
    
    public OperadorController(Operador operador, ParqueController parqueController) {
        this.operador = operador;
        this.parqueController = parqueController;
        this.atraccionesZona = FXCollections.observableArrayList();
        cargarAtraccionesZona();
    }
    
    private void cargarAtraccionesZona() {
        // Filtrar atracciones que el operador puede gestionar según su especialidad
        atraccionesZona.addAll(
            parqueController.getAtracciones().stream()
                .filter(operador::puedeGestionar)
                .toList()
        );
    }
    
    public void validarIngresoVisitante(String cedulaVisitante, Atraccion atraccion) {
        if (atraccion == null) {
            mostrarAlerta("Error", "Seleccione una atracción", Alert.AlertType.ERROR);
            return;
        }
        
        Visitante visitante = buscarVisitante(cedulaVisitante);
        if (visitante == null) {
            mostrarAlerta("Error", "Visitante no encontrado", Alert.AlertType.ERROR);
            return;
        }
        
        if (atraccion.getEstadoActual() == EstadoActual.CERRADO) {
            mostrarAlerta("Acceso Denegado", "La atracción está CERRADA", Alert.AlertType.ERROR);
            return;
        }
        
        if (atraccion.getEstadoActual() == EstadoActual.EN_MANTENIMIENTO) {
            mostrarAlerta("Acceso Denegado", "La atracción está EN MANTENIMIENTO", Alert.AlertType.ERROR);
            return;
        }
        
        boolean accesoValido = operador.validarAcceso(visitante, atraccion);
        
        if (!accesoValido) {
            mostrarAlerta("Acceso Denegado", 
                "El visitante no cumple los requisitos:\n" +
                "- Edad mínima o estatura insuficiente", 
                Alert.AlertType.ERROR);
            return;
        }
        
        atraccion.registrarIngreso();
        
        if (atraccion.getEstadoActual() == EstadoActual.EN_MANTENIMIENTO) {
            mostrarAlerta("Límite Alcanzado", 
                "¡ATENCIÓN! La atracción ha alcanzado 500 visitas.\n" +
                "Estado cambiado a EN_MANTENIMIENTO automáticamente.", 
                Alert.AlertType.WARNING);
        } else {
            mostrarAlerta("Acceso Permitido", "Visitante ingresado correctamente", Alert.AlertType.INFORMATION);
        }
    }
    
    public void registrarRevisionTecnica(Atraccion atraccion) {
        if (atraccion == null) {
            mostrarAlerta("Error", "Seleccione una atracción", Alert.AlertType.ERROR);
            return;
        }
        
        if (atraccion.getEstadoActual() != EstadoActual.EN_MANTENIMIENTO) {
            mostrarAlerta("Error", "La atracción no está en mantenimiento", Alert.AlertType.ERROR);
            return;
        }
        
        operador.registrarRevisionTecnica(atraccion);
        mostrarAlerta("Revisión Completada", 
            "Atracción revisada y reactivada correctamente", 
            Alert.AlertType.INFORMATION);
    }
    
    public void cambiarEstadoAtraccion(Atraccion atraccion, EstadoActual nuevoEstado) {
        if (atraccion != null) {
            operador.cambiarEstadoAtraccion(atraccion, nuevoEstado);
            mostrarAlerta("Estado Actualizado", 
                "Estado cambiado a: " + nuevoEstado, 
                Alert.AlertType.INFORMATION);
        }
    }
    
    private Visitante buscarVisitante(String cedula) {
        return parqueController.getVisitantes().stream()
            .filter(v -> v.getCedula().equals(cedula))
            .findFirst()
            .orElse(null);
    }
    
    public void aprobarSolicitud(SolicitudAcceso solicitud) {
        if (solicitud == null) return;
        Visitante visitante = solicitud.getVisitante();
        Atraccion atraccion = solicitud.getAtraccion();

        atraccion.registrarIngreso();
        atraccion.removerPersonaCola(); // Remover de la cola virtual
        visitante.registrarIngresoAtraccion(atraccion);

        RegistroAcceso aprobado = new RegistroAcceso(
            atraccion.getNombre(), EstadoAcceso.APROBADO,
            "Aprobado por operador", LocalDateTime.now(),
            solicitud.getPuntosDescontados());
        visitante.agregarRegistroAcceso(aprobado);

        parqueController.removerSolicitud(solicitud);

        String msg = "Visitante: " + visitante.getNombre() + "\nAtraccion: " + atraccion.getNombre();
        if (atraccion.getEstadoActual() == EstadoActual.EN_MANTENIMIENTO) {
            msg += "\n\n¡ATENCION! La atraccion alcanzo 500 visitas y paso a MANTENIMIENTO.";
            mostrarAlerta("Aprobado - Limite Alcanzado", msg, Alert.AlertType.WARNING);
        } else {
            mostrarAlerta("Acceso Aprobado", msg, Alert.AlertType.INFORMATION);
        }
    }

    public void rechazarSolicitud(SolicitudAcceso solicitud) {
        if (solicitud == null) return;
        Visitante visitante = solicitud.getVisitante();
        Atraccion atraccion = solicitud.getAtraccion();

        // Devolver puntos y remover de cola
        visitante.agregarPuntos(solicitud.getPuntosDescontados());
        atraccion.removerPersonaCola();

        RegistroAcceso rechazado = new RegistroAcceso(
            atraccion.getNombre(), EstadoAcceso.DENEGADO,
            "Rechazado por operador", LocalDateTime.now(),
            0);
        visitante.agregarRegistroAcceso(rechazado);

        parqueController.removerSolicitud(solicitud);
        mostrarAlerta("Solicitud Rechazada",
            "Visitante: " + visitante.getNombre() +
            "\nPuntos devueltos: " + solicitud.getPuntosDescontados(),
            Alert.AlertType.INFORMATION);
    }

    public ObservableList<SolicitudAcceso> getSolicitudesPendientes() {
        return parqueController.getSolicitudesPendientes();
    }

    /**
     * Filtra las solicitudes pendientes por una atracción específica
     */
    public ObservableList<SolicitudAcceso> getSolicitudesPorAtraccion(Atraccion atraccion) {
        if (atraccion == null) {
            return getSolicitudesPendientes();
        }
        return parqueController.getSolicitudesPendientes().filtered(
            s -> s.getAtraccion().equals(atraccion)
        );
    }

    public ParqueController getParqueController() {
        return parqueController;
    }

    public ObservableList<Atraccion> getAtraccionesZona() {
        return atraccionesZona;
    }
    
    public ObservableList<Atraccion> getAtraccionesEnMantenimiento() {
        return atraccionesZona.filtered(a -> a.getEstadoActual() == EstadoActual.EN_MANTENIMIENTO);
    }
    
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
