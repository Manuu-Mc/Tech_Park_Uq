package co.edu.uniquindio.poo.tech_park_uq.controller.controller;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.abstracts.Empleado;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.*;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EspecialidadOperador;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoActual;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoAtraccion;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoNotificacion;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.records.Notificacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDateTime;

public class AdminController {
    
    private Administrador administrador;
    private ParqueController parqueController;
    private ObservableList<Empleado> empleadosObservable;
    private ObservableList<Operador> operadoresObservable;
    private ObservableList<Atraccion> atraccionesObservable;
    
    public AdminController(Administrador administrador, ParqueController parqueController) {
        this.administrador = administrador;
        this.parqueController = parqueController;
        this.empleadosObservable = FXCollections.observableArrayList();
        this.operadoresObservable = FXCollections.observableArrayList(parqueController.getOperadores());
        this.atraccionesObservable = FXCollections.observableArrayList(parqueController.getAtracciones());
    }
    
    public void contratarOperador(String nombre, String cedula, int edad, String idEmpleado, 
                                  Zona zona, EspecialidadOperador especialidad) {
        try {
            Operador operador = new Operador(nombre, cedula, edad, idEmpleado, zona, especialidad);
            administrador.gestionarEmpleado(operador);
            parqueController.registrarOperador(operador);
            empleadosObservable.add(operador);
            operadoresObservable.add(operador);
            mostrarAlerta("Éxito", "Operador contratado correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo contratar al operador: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Método legacy para compatibilidad
    public void contratarOperador(String nombre, String cedula, int edad, String idEmpleado, Zona zona) {
        contratarOperador(nombre, cedula, edad, idEmpleado, zona, EspecialidadOperador.GENERAL);
    }
    
    public void desvincularEmpleado(Empleado empleado) {
        if (empleado != null) {
            empleadosObservable.remove(empleado);
            if (empleado instanceof Operador) {
                operadoresObservable.remove((Operador) empleado);
                parqueController.getOperadores().remove(empleado);
            }
            mostrarAlerta("Éxito", "Empleado desvinculado correctamente", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error", "Seleccione un empleado", Alert.AlertType.ERROR);
        }
    }
    
    public void crearAtraccion(String id, String nombre, int capacidad, float alturaMin, 
                               int edadMin, float costoAdicional, TipoAtraccion tipo, Operador operador) {
        try {
            Atraccion atraccion = new Atraccion(id, nombre, capacidad, alturaMin, edadMin, costoAdicional, tipo);
            if (operador != null) {
                atraccion.setOperadorAsignado(operador);
            }
            administrador.gestionarAtracciones(atraccion);
            parqueController.agregarAtraccion(atraccion);
            atraccionesObservable.add(atraccion);
            mostrarAlerta("Éxito", "Atracción creada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo crear la atracción: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Método legacy sin operador
    public void crearAtraccion(String id, String nombre, int capacidad, float alturaMin, 
                               int edadMin, float costoAdicional, TipoAtraccion tipo) {
        crearAtraccion(id, nombre, capacidad, alturaMin, edadMin, costoAdicional, tipo, null);
    }
    
    public void asignarAtraccionAZona(Atraccion atraccion, Zona zona) {
        if (atraccion != null && zona != null) {
            mostrarAlerta("Éxito", "Atracción asignada a zona: " + zona.getNombre(), Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error", "Seleccione atracción y zona", Alert.AlertType.ERROR);
        }
    }
    
    public void eliminarAtraccion(Atraccion atraccion) {
        if (atraccion != null) {
            atraccion.setEstadoActual(EstadoActual.CERRADO);
            parqueController.getAtracciones().remove(atraccion);
            atraccionesObservable.remove(atraccion);
            mostrarAlerta("Éxito", "Atracción eliminada correctamente", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error", "Seleccione una atracción", Alert.AlertType.ERROR);
        }
    }
    
    public void ponerEnMantenimiento(Atraccion atraccion) {
        if (atraccion != null) {
            atraccion.setEstadoActual(EstadoActual.EN_MANTENIMIENTO);
            
            // Notificar a visitantes que tienen esta atracción en favoritos
            notificarCambioEstadoAtraccion(atraccion, 
                "La atracción '" + atraccion.getNombre() + "' está en MANTENIMIENTO temporalmente.",
                TipoNotificacion.MANTENIMIENTO);
            
            mostrarAlerta("Éxito", 
                "Atracción '" + atraccion.getNombre() + "' puesta en mantenimiento.\n" +
                "El operador asignado deberá registrar la revisión técnica.\n" +
                "Visitantes con esta atracción en favoritos han sido notificados.", 
                Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error", "Seleccione una atracción", Alert.AlertType.ERROR);
        }
    }
    
    public void reactivarAtraccion(Atraccion atraccion) {
        if (atraccion != null) {
            atraccion.setEstadoActual(EstadoActual.ACTIVA);
            
            // Notificar a visitantes que tienen esta atracción en favoritos
            notificarCambioEstadoAtraccion(atraccion,
                "¡Buenas noticias! La atracción '" + atraccion.getNombre() + "' está ACTIVA nuevamente.",
                TipoNotificacion.INFORMACION);
            
            mostrarAlerta("Éxito", 
                "Atracción '" + atraccion.getNombre() + "' reactivada.\n" +
                "Visitantes con esta atracción en favoritos han sido notificados.", 
                Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error", "Seleccione una atracción", Alert.AlertType.ERROR);
        }
    }
    
    private void notificarCambioEstadoAtraccion(Atraccion atraccion, String mensaje, TipoNotificacion tipo) {
        parqueController.getVisitantes().forEach(visitante -> {
            if (visitante.getFavoritos().contains(atraccion)) {
                visitante.recibirNotificacion(new Notificacion(
                    mensaje,
                    LocalDateTime.now(),
                    tipo
                ));
            }
        });
    }
    
    public void activarAlertaClimatica() {
        administrador.activarAlertaClimatica();
        
        // Cerrar atracciones afectadas
        atraccionesObservable.forEach(a -> {
            if (a.getTipoAtraccion() == TipoAtraccion.ACUATICA || 
                a.getTipoAtraccion() == TipoAtraccion.MECANICA_ALTURA) {
                a.setEstadoActual(EstadoActual.CERRADO);
            }
        });
        
        // Notificar a todos los visitantes
        parqueController.getVisitantes().forEach(visitante -> {
            visitante.recibirNotificacion(new Notificacion(
                "ALERTA CLIMÁTICA: Tormenta detectada. Atracciones acuáticas y mecánicas cerradas por seguridad.",
                LocalDateTime.now(),
                TipoNotificacion.ALERTA
            ));
        });
        
        mostrarAlerta("Alerta Climática", 
            "Atracciones acuáticas y mecánicas cerradas por tormenta.\n" +
            "Todos los visitantes han sido notificados.", 
            Alert.AlertType.WARNING);
    }
    
    public void desactivarAlertaClimatica() {
        atraccionesObservable.forEach(a -> {
            if (a.getEstadoActual() == EstadoActual.CERRADO && 
                a.getTipoAtraccion() != TipoAtraccion.INFANTIL) {
                a.setEstadoActual(EstadoActual.ACTIVA);
            }
        });
        
        // Notificar a todos los visitantes
        parqueController.getVisitantes().forEach(visitante -> {
            visitante.recibirNotificacion(new Notificacion(
                "Alerta climática desactivada. Atracciones acuáticas y mecánicas reabiertas.",
                LocalDateTime.now(),
                TipoNotificacion.INFORMACION
            ));
        });
        
        mostrarAlerta("Alerta Desactivada", 
            "Atracciones reabiertas.\nTodos los visitantes han sido notificados.", 
            Alert.AlertType.INFORMATION);
    }
    
    public String generarReporteDiario() {
        administrador.generarReportes();
        
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n");
        reporte.append("================================================\n");
        reporte.append("     REPORTE FIN DE JORNADA - TECH PARK UQ\n");
        reporte.append("================================================\n\n");
        
        // Calcular estadísticas reales
        float ingresoTotal = 0;
        int visitasTotales = 0;
        Atraccion atraccionMasVisitada = null;
        int maxVisitas = 0;
        int atraccionesActivas = 0;
        int atraccionesMantenimiento = 0;
        int atraccionesCerradas = 0;
        
        for (Atraccion a : parqueController.getAtracciones()) {
            ingresoTotal += a.getIngresosTotales();
            visitasTotales += a.getVisitasHoy();
            
            if (a.getVisitasHoy() > maxVisitas) {
                maxVisitas = a.getVisitasHoy();
                atraccionMasVisitada = a;
            }
            
            switch (a.getEstadoActual()) {
                case ACTIVA:
                    atraccionesActivas++;
                    break;
                case EN_MANTENIMIENTO:
                    atraccionesMantenimiento++;
                    break;
                case CERRADO:
                    atraccionesCerradas++;
                    break;
            }
        }
        
        // Sección 1: Resumen General
        reporte.append("RESUMEN GENERAL\n");
        reporte.append("------------------------\n");
        reporte.append(String.format("Total Visitantes Registrados: %d\n", parqueController.getVisitantes().size()));
        reporte.append(String.format("Total Operadores Activos: %d\n", parqueController.getOperadores().size()));
        reporte.append(String.format("Total Atracciones: %d\n", parqueController.getAtracciones().size()));
        reporte.append(String.format("  - Activas: %d\n", atraccionesActivas));
        reporte.append(String.format("  - En Mantenimiento: %d\n", atraccionesMantenimiento));
        reporte.append(String.format("  - Cerradas: %d\n", atraccionesCerradas));
        reporte.append("\n");
        
        // Sección 2: Balance Financiero
        reporte.append("BALANCE FINANCIERO\n");
        reporte.append("------------------------\n");
        reporte.append(String.format("Ingresos por Costos Adicionales: $%.2f\n", ingresoTotal));
        reporte.append(String.format("Visitas Totales del Día: %d\n", visitasTotales));
        reporte.append(String.format("Ingreso Promedio por Visita: $%.2f\n", 
            visitasTotales > 0 ? ingresoTotal / visitasTotales : 0));
        reporte.append("\n");
        
        // Sección 3: Atracción Más Popular
        reporte.append("ATRACCION MAS VISITADA\n");
        reporte.append("------------------------\n");
        if (atraccionMasVisitada != null) {
            reporte.append(String.format("Nombre: %s\n", atraccionMasVisitada.getNombre()));
            reporte.append(String.format("Tipo: %s\n", atraccionMasVisitada.getTipoAtraccion()));
            reporte.append(String.format("Visitas Hoy: %d\n", maxVisitas));
            reporte.append(String.format("Ingresos Generados: $%.2f\n", atraccionMasVisitada.getIngresosTotales()));
        } else {
            reporte.append("No hay datos de visitas\n");
        }
        reporte.append("\n");
        
        // Sección 4: Detalle por Atracción
        reporte.append("DETALLE POR ATRACCION\n");
        reporte.append("------------------------\n");
        for (Atraccion a : parqueController.getAtracciones()) {
            reporte.append(String.format("\n> %s\n", a.getNombre()));
            reporte.append(String.format("   Estado: %s\n", a.getEstadoActual()));
            reporte.append(String.format("   Visitas Hoy: %d\n", a.getVisitasHoy()));
            reporte.append(String.format("   Visitas Totales: %d/500\n", a.getContadorVisitantes()));
            reporte.append(String.format("   Ingresos: $%.2f\n", a.getIngresosTotales()));
            if (a.getOperadorAsignado() != null) {
                reporte.append(String.format("   Operador: %s\n", a.getOperadorAsignado().getNombre()));
            }
        }
        reporte.append("\n");
        
        // Sección 5: Solicitudes Pendientes
        reporte.append("SOLICITUDES PENDIENTES\n");
        reporte.append("------------------------\n");
        int solicitudesPendientes = (int) parqueController.getSolicitudesPendientes().stream()
            .filter(s -> s.getEstado() == co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoAcceso.EN_PROCESO)
            .count();
        reporte.append(String.format("Total Solicitudes en Proceso: %d\n", solicitudesPendientes));
        reporte.append("\n");
        
        reporte.append("================================================\n");
        reporte.append("Reporte generado: " + LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        reporte.append("\n================================================\n");
        
        return reporte.toString();
    }
    
    public ParqueController getParqueController() {
        return parqueController;
    }

    public ObservableList<Empleado> getEmpleadosObservable() {
        return empleadosObservable;
    }
    
    public ObservableList<Atraccion> getAtraccionesObservable() {
        return atraccionesObservable;
    }

    public ObservableList<Operador> getOperadoresObservable() {
        return operadoresObservable;
    }
    
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
