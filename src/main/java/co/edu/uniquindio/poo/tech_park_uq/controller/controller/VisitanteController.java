package co.edu.uniquindio.poo.tech_park_uq.controller.controller;

import co.edu.uniquindio.poo.techparkuq.model.entities.Atraccion;
import co.edu.uniquindio.poo.techparkuq.model.entities.SolicitudAcceso;
import co.edu.uniquindio.poo.techparkuq.model.entities.Visitante;
import co.edu.uniquindio.poo.techparkuq.model.enums.EstadoAcceso;
import co.edu.uniquindio.poo.techparkuq.model.enums.EstadoActual;
import co.edu.uniquindio.poo.techparkuq.model.enums.TipoTicket;
import co.edu.uniquindio.poo.techparkuq.model.records.Notificacion;
import co.edu.uniquindio.poo.techparkuq.model.records.RegistroAcceso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDateTime;

public class VisitanteController {
    
    private Visitante visitante;
    private ParqueController parqueController;
    private ObservableList<Atraccion> atraccionesDisponibles;
    private ObservableList<Atraccion> atraccionesFavoritas;
    private ObservableList<Notificacion> notificaciones;
    private ObservableList<RegistroAcceso> historialAccesos;
    
    public VisitanteController(Visitante visitante, ParqueController parqueController) {
        this.visitante = visitante;
        this.parqueController = parqueController;
        this.atraccionesDisponibles = FXCollections.observableArrayList(parqueController.getAtracciones());
        this.atraccionesFavoritas = FXCollections.observableArrayList(visitante.getFavoritos());
        this.notificaciones = FXCollections.observableArrayList(visitante.getNotificaciones());
        this.historialAccesos = FXCollections.observableArrayList(visitante.getHistorialAccesos());
    }
    
    public void recargarSaldo(float monto) {
        if (monto <= 0) {
            mostrarAlerta("Error", "El monto debe ser mayor a 0", Alert.AlertType.ERROR);
            return;
        }
        
        float nuevoSaldo = visitante.getSaldoVirtual() + monto;
        visitante.setSaldoVirtual(nuevoSaldo);
        mostrarAlerta("Recarga Exitosa", 
            "Se han agregado $" + String.format("%.0f", monto) + " a su billetera virtual\n" +
            "Nuevo saldo: $" + String.format("%.0f", nuevoSaldo), 
            Alert.AlertType.INFORMATION);
    }
    
    public void comprarTicketGeneral() {
        float costoTicket = 50000;
        if (visitante.getSaldoVirtual() < costoTicket) {
            mostrarAlerta("Saldo Insuficiente", 
                "No tiene saldo suficiente para comprar el ticket.\n" +
                "Costo: $" + String.format("%.0f", costoTicket) + "\n" +
                "Saldo actual: $" + String.format("%.0f", visitante.getSaldoVirtual()), 
                Alert.AlertType.ERROR);
            return;
        }
        
        visitante.setSaldoVirtual(visitante.getSaldoVirtual() - costoTicket);
        visitante.comprarTicket(TipoTicket.GENERAL);
        mostrarAlerta("Compra Exitosa", 
            "Ticket General adquirido correctamente\n" +
            "Beneficio: Acceso al parque\n" +
            "Saldo restante: $" + String.format("%.0f", visitante.getSaldoVirtual()) + "\n\n" +
            "Ahora puedes comprar puntos en la Tienda de Puntos!", 
            Alert.AlertType.INFORMATION);
    }
    
    public void comprarTicketFamiliar() {
        float costoTicket = 150000;
        if (visitante.getSaldoVirtual() < costoTicket) {
            mostrarAlerta("Saldo Insuficiente", 
                "No tiene saldo suficiente.\n" +
                "Costo: $" + String.format("%.0f", costoTicket) + "\n" +
                "Saldo actual: $" + String.format("%.0f", visitante.getSaldoVirtual()), 
                Alert.AlertType.ERROR);
            return;
        }
        
        visitante.setSaldoVirtual(visitante.getSaldoVirtual() - costoTicket);
        visitante.comprarTicket(TipoTicket.FAMILIAR);
        mostrarAlerta("Compra Exitosa", 
            "Ticket Familiar adquirido (hasta 4 personas)\n" +
            "Beneficio: 25% de descuento en compra de puntos\n" +
            "Saldo restante: $" + String.format("%.0f", visitante.getSaldoVirtual()) + "\n\n" +
            "Visita la Tienda de Puntos para comprar con descuento!", 
            Alert.AlertType.INFORMATION);
    }
    
    public void comprarTicketFastPass() {
        float costoTicket = 80000;
        if (visitante.getSaldoVirtual() < costoTicket) {
            mostrarAlerta("Saldo Insuficiente", 
                "No tiene saldo suficiente.\n" +
                "Costo: $" + String.format("%.0f", costoTicket) + "\n" +
                "Saldo actual: $" + String.format("%.0f", visitante.getSaldoVirtual()), 
                Alert.AlertType.ERROR);
            return;
        }
        
        visitante.setSaldoVirtual(visitante.getSaldoVirtual() - costoTicket);
        visitante.comprarTicket(TipoTicket.FAST_PASS);
        mostrarAlerta("Compra Exitosa", 
            "Fast-Pass adquirido correctamente\n" +
            "Beneficio: Prioridad en todas las colas virtuales\n" +
            "Beneficio: Sin costos adicionales en atracciones\n" +
            "Saldo restante: $" + String.format("%.0f", visitante.getSaldoVirtual()) + "\n\n" +
            "Ahora tienes prioridad en todas las atracciones!", 
            Alert.AlertType.INFORMATION);
    }
    
    public void agregarAtraccionFavorita(Atraccion atraccion) {
        if (atraccion == null) {
            mostrarAlerta("Error", "Seleccione una atraccion", Alert.AlertType.ERROR);
            return;
        }
        
        if (visitante.getFavoritos().contains(atraccion)) {
            mostrarAlerta("Informacion", "Esta atraccion ya esta en favoritos", Alert.AlertType.INFORMATION);
            return;
        }
        
        visitante.registrarAtraccionFavorita(atraccion);
        atraccionesFavoritas.add(atraccion);
        mostrarAlerta("Favorito Agregado", 
            "Atraccion agregada a favoritos", 
            Alert.AlertType.INFORMATION);
    }
    
    public void eliminarAtraccionFavorita(Atraccion atraccion) {
        if (atraccion == null) {
            mostrarAlerta("Error", "Seleccione una atraccion", Alert.AlertType.ERROR);
            return;
        }
        
        visitante.eliminarAtraccionFavorita(atraccion);
        atraccionesFavoritas.remove(atraccion);
        mostrarAlerta("Favorito Eliminado", 
            "Atraccion eliminada de favoritos", 
            Alert.AlertType.INFORMATION);
    }
    
    public void solicitarAccesoAtraccion(Atraccion atraccion) {
        if (atraccion == null) {
            mostrarAlerta("Error", "Seleccione una atraccion", Alert.AlertType.ERROR);
            return;
        }
        
        // Validar que tenga ticket
        if (!visitante.tieneTicket()) {
            mostrarAlerta("Sin Ticket", 
                "Debes comprar un ticket para poder ingresar a las atracciones.\n\n" +
                "Ve a la pestaña 'Tienda de Tickets' para comprar tu ticket.", 
                Alert.AlertType.WARNING);
            return;
        }
        
        int costoEnPuntos = atraccion.getCostoEnPuntos();
        float costoAdicional = atraccion.getCostoAdicional();
        EstadoAcceso estado;
        String motivo;
        
        // Validar estado de la atracción
        if (atraccion.getEstadoActual() == EstadoActual.CERRADO) {
            estado = EstadoAcceso.DENEGADO;
            motivo = "Atracción CERRADA";
            registrarAcceso(atraccion, estado, motivo, 0);
            mostrarAlerta("Acceso Denegado",
                "La atracción \"" + atraccion.getNombre() + "\" se encuentra CERRADA.\n" +
                "No es posible solicitar acceso en este momento.",
                Alert.AlertType.ERROR);
            return;
        }

        if (atraccion.getEstadoActual() == EstadoActual.EN_MANTENIMIENTO) {
            estado = EstadoAcceso.DENEGADO;
            motivo = "Atracción en MANTENIMIENTO";
            registrarAcceso(atraccion, estado, motivo, 0);
            mostrarAlerta("Acceso Denegado",
                "La atracción \"" + atraccion.getNombre() + "\" está en MANTENIMIENTO.\n" +
                "Vuelve a intentarlo cuando vuelva a estar activa.",
                Alert.AlertType.ERROR);
            return;
        }

        // Validar edad y altura mínimas requeridas para subirse a la atracción
        boolean cumpleEdad = visitante.getEdad() >= atraccion.getEdadMinimaRequerida();
        boolean cumpleAltura = visitante.getEstatura() >= atraccion.getAlturaMinimaRequerida();
        if (!cumpleEdad || !cumpleAltura) {
            estado = EstadoAcceso.DENEGADO;
            StringBuilder detalle = new StringBuilder();
            StringBuilder motivoBuilder = new StringBuilder("No cumple requisitos: ");

            detalle.append("No puedes subirte a \"").append(atraccion.getNombre()).append("\"")
                   .append(" porque no cumples con los siguientes requisitos:\n\n");

            if (!cumpleEdad) {
                int faltan = atraccion.getEdadMinimaRequerida() - visitante.getEdad();
                detalle.append("• Edad mínima requerida: ")
                       .append(atraccion.getEdadMinimaRequerida()).append(" años\n")
                       .append("  Tu edad: ").append(visitante.getEdad()).append(" años")
                       .append(" (te faltan ").append(faltan).append(" años)\n\n");
                motivoBuilder.append("Edad mínima ").append(atraccion.getEdadMinimaRequerida())
                             .append(" años (tienes ").append(visitante.getEdad()).append("). ");
            }

            if (!cumpleAltura) {
                float faltan = atraccion.getAlturaMinimaRequerida() - visitante.getEstatura();
                detalle.append("• Estatura mínima requerida: ")
                       .append(String.format("%.2f", atraccion.getAlturaMinimaRequerida())).append(" m\n")
                       .append("  Tu estatura: ").append(String.format("%.2f", visitante.getEstatura())).append(" m")
                       .append(" (te faltan ").append(String.format("%.2f", faltan)).append(" m)\n\n");
                motivoBuilder.append("Estatura mínima ")
                             .append(String.format("%.2f", atraccion.getAlturaMinimaRequerida()))
                             .append(" m (tienes ")
                             .append(String.format("%.2f", visitante.getEstatura())).append(" m).");
            }

            motivo = motivoBuilder.toString();
            registrarAcceso(atraccion, estado, motivo, 0);
            mostrarAlerta("Acceso Denegado - No cumples los requisitos",
                detalle.toString(),
                Alert.AlertType.ERROR);
            return;
        }
        
        // Validar costo adicional (si no tiene Fast-Pass)
        if (costoAdicional > 0 && !visitante.tieneFastPass()) {
            if (!visitante.tieneSaldoSuficiente(costoAdicional)) {
                estado = EstadoAcceso.DENEGADO;
                motivo = "Saldo insuficiente para costo adicional (Necesitas: $" + 
                         String.format("%.0f", costoAdicional) + ", Tienes: $" + 
                         String.format("%.0f", visitante.getSaldoVirtual()) + ")";
                registrarAcceso(atraccion, estado, motivo, 0);
                mostrarAlerta("Saldo Insuficiente", 
                    "Esta atraccion requiere un pago adicional.\n" +
                    "Costo adicional: $" + String.format("%.0f", costoAdicional) + "\n" +
                    "Tu saldo: $" + String.format("%.0f", visitante.getSaldoVirtual()) + "\n\n" +
                    "Recarga tu saldo o compra un Fast-Pass para acceso sin costo adicional.", 
                    Alert.AlertType.ERROR);
                return;
            }
        }
        
        // Validar puntos
        if (visitante.getPuntosTicket() < costoEnPuntos) {
            estado = EstadoAcceso.DENEGADO;
            motivo = "Puntos insuficientes (Necesitas: " + costoEnPuntos + ", Tienes: " + visitante.getPuntosTicket() + ")";
            registrarAcceso(atraccion, estado, motivo, 0);
            mostrarAlerta("Puntos Insuficientes", 
                "No tienes suficientes puntos.\n" +
                "Necesitas: " + costoEnPuntos + " puntos\n" +
                "Tienes: " + visitante.getPuntosTicket() + " puntos\n\n" +
                "Compra mas tickets para obtener puntos!", 
                Alert.AlertType.ERROR);
            return;
        }
        
        // Descontar puntos
        visitante.usarPuntosParaAtraccion(costoEnPuntos);
        
        // Agregar a la cola virtual según tipo de ticket
        if (visitante.tieneFastPass()) {
            atraccion.agregarPersonaColaFastPass();
        } else {
            atraccion.agregarPersonaCola();
        }
        
        // Descontar costo adicional si aplica
        if (costoAdicional > 0 && !visitante.tieneFastPass()) {
            visitante.descontarSaldo(costoAdicional);
        }

        estado = EstadoAcceso.EN_PROCESO;
        String mensajePrioridad = visitante.tieneFastPass() ? 
            "\n\nFAST-PASS ACTIVO: Tienes prioridad en la cola!" : "";
        String mensajeCosto = (costoAdicional > 0 && !visitante.tieneFastPass()) ?
            "\nCosto adicional descontado: $" + String.format("%.0f", costoAdicional) : "";
        
        motivo = "Solicitud enviada, pendiente de validacion por operador" + 
                 (visitante.tieneFastPass() ? " (PRIORIDAD FAST-PASS)" : "");
        registrarAcceso(atraccion, estado, motivo, costoEnPuntos);

        SolicitudAcceso solicitud = new SolicitudAcceso(visitante, atraccion, costoEnPuntos);
        parqueController.agregarSolicitud(solicitud);

        mostrarAlerta("Solicitud Enviada",
            "Tu solicitud para acceder a:\n" + atraccion.getNombre() +
            "\n\nha sido enviada al operador.\n" +
            "Puntos descontados: " + costoEnPuntos +
            mensajeCosto +
            "\nPuntos restantes: " + visitante.getPuntosTicket() +
            "\n\nEstado: EN PROCESO - Espera la validacion del operador." +
            mensajePrioridad,
            Alert.AlertType.INFORMATION);
    }
    
    private void registrarAcceso(Atraccion atraccion, EstadoAcceso estado, String motivo, int puntosUsados) {
        RegistroAcceso registro = new RegistroAcceso(
            atraccion.getNombre(),
            estado,
            motivo,
            LocalDateTime.now(),
            puntosUsados
        );
        visitante.agregarRegistroAcceso(registro);
        historialAccesos.add(registro);
    }
    
    public void consultarMapaParque() {
        visitante.consultarMapaParque();
        mostrarAlerta("Mapa del Parque", 
            "Mostrando todas las atracciones disponibles", 
            Alert.AlertType.INFORMATION);
    }
    
    public void consultarTiempoEspera(Atraccion atraccion) {
        if (atraccion != null) {
            visitante.consultarTiempoEspera();
            mostrarAlerta("Tiempo de Espera", 
                "Tiempo estimado: 15 minutos", 
                Alert.AlertType.INFORMATION);
        }
    }
    
    public void comprarPuntos(int cantidad) {
        if (!visitante.tieneTicket()) {
            mostrarAlerta("Sin Ticket", 
                "Debes comprar un ticket primero para poder adquirir puntos.\n\n" +
                "Ve a la pestaña 'Tienda de Tickets' para comprar tu ticket.", 
                Alert.AlertType.WARNING);
            return;
        }

        float precioTotal = visitante.calcularPrecioPuntos(cantidad);
        int descuento = visitante.getDescuentoPuntos();
        
        if (visitante.getSaldoVirtual() < precioTotal) {
            mostrarAlerta("Saldo Insuficiente", 
                "No tienes saldo suficiente para comprar " + cantidad + " puntos.\n" +
                "Precio: $" + String.format("%.0f", precioTotal) + 
                (descuento > 0 ? " (" + descuento + "% descuento aplicado)" : "") + "\n" +
                "Saldo actual: $" + String.format("%.0f", visitante.getSaldoVirtual()), 
                Alert.AlertType.ERROR);
            return;
        }
        
        visitante.setSaldoVirtual(visitante.getSaldoVirtual() - precioTotal);
        visitante.agregarPuntos(cantidad);
        
        String mensajeDescuento = descuento > 0 ? 
            "\nDescuento aplicado: " + descuento + "% (Ticket Familiar)" : "";
        
        mostrarAlerta("Compra Exitosa", 
            "Has comprado " + cantidad + " puntos\n" +
            "Precio pagado: $" + String.format("%.0f", precioTotal) +
            mensajeDescuento + "\n" +
            "Total puntos: " + visitante.getPuntosTicket() + "\n" +
            "Saldo restante: $" + String.format("%.0f", visitante.getSaldoVirtual()), 
            Alert.AlertType.INFORMATION);
    }
    
    public ParqueController getParqueController() {
        return parqueController;
    }

    public Visitante getVisitante() {
        return visitante;
    }
    
    public ObservableList<Atraccion> getAtraccionesDisponibles() {
        return atraccionesDisponibles;
    }
    
    public ObservableList<Atraccion> getAtraccionesFavoritas() {
        return atraccionesFavoritas;
    }
    
    public ObservableList<Notificacion> getNotificaciones() {
        return notificaciones;
    }
    
    public ObservableList<RegistroAcceso> getHistorialAccesos() {
        return historialAccesos;
    }
    
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
