package co.edu.uniquindio.poo.tech_park_uq.controller.controller;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Administrador;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Operador;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Visitante;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Zona;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class LoginController {
    
    private ParqueController parqueController;
    private List<Administrador> administradores;
    private List<Operador> operadores;
    
    public LoginController(ParqueController parqueController) {
        this.parqueController = parqueController;
        this.administradores = new ArrayList<>();
        this.operadores = new ArrayList<>();
    }
    
    public Object autenticarUsuario(String cedula, String rol) {
        if (rol.equals("Visitante")) {
            return buscarVisitante(cedula);
        } else if (rol.equals("Operador")) {
            return buscarOperador(cedula);
        } else if (rol.equals("Administrador")) {
            return buscarAdministrador(cedula);
        }
        return null;
    }
    
    private Visitante buscarVisitante(String cedula) {
        return parqueController.getVisitantes().stream()
            .filter(v -> v.getCedula().equals(cedula))
            .findFirst()
            .orElse(null);
    }
    
    private Operador buscarOperador(String cedula) {
        return parqueController.getOperadores().stream()
            .filter(o -> o.getCedula().equals(cedula))
            .findFirst()
            .orElse(null);
    }
    
    private Administrador buscarAdministrador(String cedula) {
        return parqueController.getAdministradores().stream()
            .filter(a -> a.getCedula().equals(cedula))
            .findFirst()
            .orElse(null);
    }
    
    public Visitante registrarNuevoVisitante(String nombre, String cedula, int edad, float estatura) {
        // Verificar si ya existe
        if (buscarVisitante(cedula) != null) {
            mostrarAlerta("Error", "Ya existe un visitante con esta cédula", Alert.AlertType.ERROR);
            return null;
        }
        
        Visitante visitante = new Visitante(nombre, cedula, edad, estatura, 0);
        parqueController.registrarVisitante(visitante);
        mostrarAlerta("Registro Exitoso", "Visitante registrado correctamente", Alert.AlertType.INFORMATION);
        return visitante;
    }
    
    public Operador registrarNuevoOperador(String nombre, String cedula, int edad, String idEmpleado, Zona zona) {
        // Verificar si ya existe
        if (buscarOperador(cedula) != null) {
            mostrarAlerta("Error", "Ya existe un operador con esta cédula", Alert.AlertType.ERROR);
            return null;
        }
        
        Operador operador = new Operador(nombre, cedula, edad, idEmpleado, zona);
        parqueController.registrarOperador(operador);
        operadores.add(operador);
        mostrarAlerta("Registro Exitoso", "Operador registrado correctamente", Alert.AlertType.INFORMATION);
        return operador;
    }
    
    public Administrador registrarNuevoAdministrador(String nombre, String cedula, int edad, String idEmpleado, float sueldo) {
        // Verificar si ya existe
        if (buscarAdministrador(cedula) != null) {
            mostrarAlerta("Error", "Ya existe un administrador con esta cédula", Alert.AlertType.ERROR);
            return null;
        }
        
        Administrador admin = new Administrador(nombre, cedula, edad, idEmpleado, sueldo);
        parqueController.registrarAdministrador(admin);
        administradores.add(admin);
        mostrarAlerta("Registro Exitoso", "Administrador registrado correctamente", Alert.AlertType.INFORMATION);
        return admin;
    }
    
    public void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public void registrarAdministradorDirecto(Administrador admin) {
        parqueController.registrarAdministrador(admin);
        administradores.add(admin);
    }
    
    public List<Administrador> getAdministradores() {
        return administradores;
    }
    
    public List<Operador> getOperadores() {
        return operadores;
    }
}
