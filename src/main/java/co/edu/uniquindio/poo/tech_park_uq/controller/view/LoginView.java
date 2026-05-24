package co.edu.uniquindio.poo.tech_park_uq.controller.view;

import co.edu.uniquindio.poo.tech_park_uq.controller.controller.LoginController;
import co.edu.uniquindio.poo.tech_park_uq.controller.controller.ParqueController;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Administrador;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Operador;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Visitante;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Zona;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    
    private LoginController controller;
    private ParqueController parqueController;
    private Stage stage;
    
    public LoginView(Stage stage, ParqueController parqueController) {
        this.stage = stage;
        this.parqueController = parqueController;
        this.controller = new LoginController(parqueController);
    }
    
    public Scene crearEscena() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F7FA;");
        
        // Panel central con el formulario
        VBox centerBox = new VBox(40);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(60));
        
        // Tarjeta de login
        VBox loginCard = new VBox(30);
        loginCard.setMaxWidth(480);
        loginCard.setStyle("-fx-background-color: white; -fx-padding: 50; -fx-background-radius: 24; "
                + "-fx-effect: dropshadow(gaussian, rgba(69,39,160,0.08), 40, 0, 0, 12);");
        loginCard.setAlignment(Pos.CENTER);
        
        // Logo/Título
        VBox headerBox = new VBox(12);
        headerBox.setAlignment(Pos.CENTER);
        
        Label titulo = new Label("Tech-Park UQ");
        titulo.setStyle("-fx-font-size: 32px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        Label subtitulo = new Label("Bienvenido al sistema");
        subtitulo.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        headerBox.getChildren().addAll(titulo, subtitulo);
        
        // Separador sutil
        Separator separator = new Separator();
        separator.setMaxWidth(300);
        separator.setStyle("-fx-border-color: #E0E0E0;");
        
        // Formulario
        VBox formulario = new VBox(22);
        formulario.setAlignment(Pos.CENTER);
        
        // Campo Cédula
        VBox cedulaBox = new VBox(8);
        Label lblCedula = new Label("Cédula / ID");
        lblCedula.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        TextField txtCedula = new TextField();
        txtCedula.setPromptText("Ingrese su cédula o ID");
        txtCedula.setPrefWidth(380);
        txtCedula.setPrefHeight(44);
        txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtCedula.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        cedulaBox.getChildren().addAll(lblCedula, txtCedula);
        
        // Campo Rol
        VBox rolBox = new VBox(8);
        Label lblRol = new Label("Tipo de Usuario");
        lblRol.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        ComboBox<String> cmbRol = new ComboBox<>();
        cmbRol.getItems().addAll("Visitante", "Operador", "Administrador");
        cmbRol.setValue("Visitante");
        cmbRol.setPrefWidth(380);
        cmbRol.setPrefHeight(44);
        cmbRol.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        rolBox.getChildren().addAll(lblRol, cmbRol);
        
        formulario.getChildren().addAll(cedulaBox, rolBox);
        
        // Botones
        VBox botonesBox = new VBox(14);
        botonesBox.setAlignment(Pos.CENTER);
        
        Button btnIngresar = new Button("Ingresar");
        btnIngresar.setPrefWidth(380);
        btnIngresar.setPrefHeight(48);
        btnIngresar.setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-background-radius: 12; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        btnIngresar.setOnMouseEntered(e -> btnIngresar.setStyle("-fx-background-color: #5E35B1; -fx-text-fill: white; "
                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-background-radius: 12; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));
        btnIngresar.setOnMouseExited(e -> btnIngresar.setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-background-radius: 12; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));
        
        Button btnRegistrar = new Button("Registrarse");
        btnRegistrar.setPrefWidth(380);
        btnRegistrar.setPrefHeight(48);
        btnRegistrar.setStyle("-fx-background-color: transparent; -fx-text-fill: #4527A0; "
                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-border-color: #4527A0; "
                + "-fx-border-width: 1.5; -fx-border-radius: 12; -fx-background-radius: 12; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        btnRegistrar.setOnMouseEntered(e -> btnRegistrar.setStyle("-fx-background-color: rgba(69, 39, 160, 0.05); -fx-text-fill: #4527A0; "
                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-border-color: #4527A0; "
                + "-fx-border-width: 1.5; -fx-border-radius: 12; -fx-background-radius: 12; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));
        btnRegistrar.setOnMouseExited(e -> btnRegistrar.setStyle("-fx-background-color: transparent; -fx-text-fill: #4527A0; "
                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-border-color: #4527A0; "
                + "-fx-border-width: 1.5; -fx-border-radius: 12; -fx-background-radius: 12; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));
        
        botonesBox.getChildren().addAll(btnIngresar, btnRegistrar);
        
        btnIngresar.setOnAction(e -> {
            String cedula = txtCedula.getText();
            String rol = cmbRol.getValue();
            
            if (cedula.isEmpty()) {
                controller.mostrarAlerta("Error", "Ingrese su cédula", Alert.AlertType.ERROR);
                return;
            }
            
            Object usuario = controller.autenticarUsuario(cedula, rol);
            
            if (usuario instanceof Visitante) {
                abrirDashboardVisitante((Visitante) usuario);
            } else if (usuario instanceof Operador) {
                abrirDashboardOperador((Operador) usuario);
            } else if (usuario instanceof Administrador) {
                abrirDashboardAdmin((Administrador) usuario);
            } else {
                controller.mostrarAlerta("Error", "Usuario no encontrado", Alert.AlertType.ERROR);
            }
        });
        
        btnRegistrar.setOnAction(e -> {
            String rol = cmbRol.getValue();
            if (rol.equals("Visitante")) {
                mostrarFormularioRegistroVisitante();
            } else if (rol.equals("Operador")) {
                mostrarFormularioRegistroOperador();
            } else if (rol.equals("Administrador")) {
                mostrarFormularioRegistroAdministrador();
            }
        });
        
        // Footer
        Label footer = new Label("Tech-Park UQ");
        footer.setStyle("-fx-font-size: 12px; -fx-text-fill: #9E9E9E; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        loginCard.getChildren().addAll(headerBox, separator, formulario, botonesBox, footer);
        centerBox.getChildren().add(loginCard);
        
        root.setCenter(centerBox);
        
        Scene scene = new Scene(root, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/styles/modern-tabs.css").toExternalForm());
        return scene;
    }
    
    private void mostrarFormularioRegistroVisitante() {
        Dialog<Visitante> dialog = new Dialog<>();
        dialog.setTitle("Registro de Visitante");
        dialog.setHeaderText(null);
        
        // Custom header
        VBox header = new VBox(8);
        header.setPadding(new Insets(20, 20, 10, 20));
        header.setStyle("-fx-background-color: #F5F7FA;");
        
        Label titulo = new Label("Registro de Visitante");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        Label subtitulo = new Label("Complete los datos del nuevo visitante");
        subtitulo.setStyle("-fx-font-size: 13px; -fx-text-fill: #757575; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        header.getChildren().addAll(titulo, subtitulo);
        
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25));
        
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        txtNombre.setPrefWidth(300);
        txtNombre.setPrefHeight(44);
        txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtNombre.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtCedula = new TextField();
        txtCedula.setPromptText("Número de cédula");
        txtCedula.setPrefWidth(300);
        txtCedula.setPrefHeight(44);
        txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtCedula.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Edad en años");
        txtEdad.setPrefWidth(300);
        txtEdad.setPrefHeight(44);
        txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtEdad.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtEstatura = new TextField();
        txtEstatura.setPromptText("Ej: 1.75");
        txtEstatura.setPrefWidth(300);
        txtEstatura.setPrefHeight(44);
        txtEstatura.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtEstatura.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtEstatura.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtEstatura.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        Label lblFoto = new Label("Foto de Perfil (Opcional):");
        lblFoto.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        TextField txtRutaFoto = new TextField();
        txtRutaFoto.setPromptText("Ruta de la imagen");
        txtRutaFoto.setEditable(false);
        txtRutaFoto.setPrefWidth(220);
        txtRutaFoto.setPrefHeight(44);
        txtRutaFoto.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        Button btnSeleccionarFoto = new Button("Seleccionar");
        btnSeleccionarFoto.setPrefHeight(40);
        btnSeleccionarFoto.setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-background-radius: 8; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        btnSeleccionarFoto.setOnMouseEntered(e -> btnSeleccionarFoto.setStyle("-fx-background-color: #5E35B1; -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-background-radius: 8; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));
        btnSeleccionarFoto.setOnMouseExited(e -> btnSeleccionarFoto.setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-background-radius: 8; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));
        btnSeleccionarFoto.setOnAction(e -> {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Seleccionar Foto de Perfil");
            fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            java.io.File archivo = fileChooser.showOpenDialog(dialog.getOwner());
            if (archivo != null) {
                txtRutaFoto.setText(archivo.getAbsolutePath());
            }
        });
        
        HBox fotoBox = new HBox(10);
        fotoBox.getChildren().addAll(txtRutaFoto, btnSeleccionarFoto);
        
        Label lblNombre = new Label("Nombre:");
        lblNombre.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblCedula = new Label("Cédula:");
        lblCedula.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblEdad = new Label("Edad:");
        lblEdad.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblEstatura = new Label("Estatura (m):");
        lblEstatura.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        grid.add(lblNombre, 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(lblCedula, 0, 1);
        grid.add(txtCedula, 1, 1);
        grid.add(lblEdad, 0, 2);
        grid.add(txtEdad, 1, 2);
        grid.add(lblEstatura, 0, 3);
        grid.add(txtEstatura, 1, 3);
        grid.add(lblFoto, 0, 4);
        grid.add(fotoBox, 1, 4);
        
        VBox content = new VBox(0);
        content.getChildren().addAll(header, grid);
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        // Style the dialog buttons
        dialog.getDialogPane().lookupButton(ButtonType.OK).setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-background-radius: 8; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setStyle("-fx-background-color: transparent; -fx-text-fill: #757575; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-border-color: #E0E0E0; -fx-border-width: 1; "
                + "-fx-border-radius: 8; -fx-background-radius: 8; -fx-cursor: hand; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                try {
                    Visitante nuevoVisitante = controller.registrarNuevoVisitante(
                        txtNombre.getText(),
                        txtCedula.getText(),
                        Integer.parseInt(txtEdad.getText()),
                        Float.parseFloat(txtEstatura.getText())
                    );
                    
                    // Guardar ruta de foto si se seleccionó
                    if (nuevoVisitante != null && !txtRutaFoto.getText().isEmpty()) {
                        nuevoVisitante.setRutaFotoPerfil(txtRutaFoto.getText());
                    }
                    
                    return nuevoVisitante;
                } catch (NumberFormatException ex) {
                    controller.mostrarAlerta("Error", "Datos inválidos. Verifique edad y estatura.", Alert.AlertType.ERROR);
                }
            }
            return null;
        });
        
        dialog.showAndWait();
    }
    
    private void mostrarFormularioRegistroOperador() {
        Dialog<Operador> dialog = new Dialog<>();
        dialog.setTitle("Registro de Operador");
        dialog.setHeaderText(null);
        
        // Custom header
        VBox header = new VBox(8);
        header.setPadding(new Insets(20, 20, 10, 20));
        header.setStyle("-fx-background-color: #F5F7FA;");
        
        Label titulo = new Label("Registro de Operador");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        Label subtitulo = new Label("Complete los datos del nuevo operador");
        subtitulo.setStyle("-fx-font-size: 13px; -fx-text-fill: #757575; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        header.getChildren().addAll(titulo, subtitulo);
        
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25));
        
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        txtNombre.setPrefWidth(300);
        txtNombre.setPrefHeight(44);
        txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtNombre.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtCedula = new TextField();
        txtCedula.setPromptText("Número de cédula");
        txtCedula.setPrefWidth(300);
        txtCedula.setPrefHeight(44);
        txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtCedula.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Edad en años");
        txtEdad.setPrefWidth(300);
        txtEdad.setPrefHeight(44);
        txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtEdad.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtIdEmpleado = new TextField();
        txtIdEmpleado.setPromptText("ID de empleado");
        txtIdEmpleado.setPrefWidth(300);
        txtIdEmpleado.setPrefHeight(44);
        txtIdEmpleado.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtIdEmpleado.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtIdEmpleado.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtIdEmpleado.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        ComboBox<String> cmbZona = new ComboBox<>();
        cmbZona.getItems().addAll("Zona Acuatica", "Zona Mecanica", "Zona Infantil", "Zona Principal");
        cmbZona.setValue("Zona Principal");
        cmbZona.setPrefWidth(300);
        cmbZona.setPrefHeight(44);
        cmbZona.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        Label lblNombre = new Label("Nombre:");
        lblNombre.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblCedula = new Label("Cédula:");
        lblCedula.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblEdad = new Label("Edad:");
        lblEdad.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblId = new Label("ID Empleado:");
        lblId.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblZona = new Label("Zona Asignada:");
        lblZona.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        Label lblFoto = new Label("Foto de Perfil (Opcional):");
        lblFoto.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        TextField txtRutaFoto = new TextField();
        txtRutaFoto.setPromptText("Ruta de la imagen");
        txtRutaFoto.setEditable(false);
        txtRutaFoto.setPrefWidth(220);
        txtRutaFoto.setPrefHeight(44);
        txtRutaFoto.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Button btnSeleccionarFoto = new Button("Seleccionar");
        btnSeleccionarFoto.setPrefHeight(44);
        btnSeleccionarFoto.setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-background-radius: 8; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        btnSeleccionarFoto.setOnAction(e -> {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Seleccionar Foto de Perfil");
            fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            java.io.File archivo = fileChooser.showOpenDialog(dialog.getOwner());
            if (archivo != null) {
                txtRutaFoto.setText(archivo.getAbsolutePath());
            }
        });
        HBox fotoBox = new HBox(10);
        fotoBox.getChildren().addAll(txtRutaFoto, btnSeleccionarFoto);
        
        grid.add(lblNombre, 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(lblCedula, 0, 1);
        grid.add(txtCedula, 1, 1);
        grid.add(lblEdad, 0, 2);
        grid.add(txtEdad, 1, 2);
        grid.add(lblId, 0, 3);
        grid.add(txtIdEmpleado, 1, 3);
        grid.add(lblZona, 0, 4);
        grid.add(cmbZona, 1, 4);
        grid.add(lblFoto, 0, 5);
        grid.add(fotoBox, 1, 5);
        
        VBox content = new VBox(0);
        content.getChildren().addAll(header, grid);
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        // Style the dialog buttons
        dialog.getDialogPane().lookupButton(ButtonType.OK).setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-background-radius: 8; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setStyle("-fx-background-color: transparent; -fx-text-fill: #757575; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-border-color: #E0E0E0; -fx-border-width: 1; "
                + "-fx-border-radius: 8; -fx-background-radius: 8; -fx-cursor: hand; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                try {
                    Zona zona = new Zona(cmbZona.getValue(), 100);
                    Operador nuevoOperador = controller.registrarNuevoOperador(
                        txtNombre.getText(),
                        txtCedula.getText(),
                        Integer.parseInt(txtEdad.getText()),
                        txtIdEmpleado.getText(),
                        zona
                    );
                    if (nuevoOperador != null && !txtRutaFoto.getText().isEmpty()) {
                        nuevoOperador.setRutaFotoPerfil(txtRutaFoto.getText());
                    }
                    return nuevoOperador;
                } catch (NumberFormatException ex) {
                    controller.mostrarAlerta("Error", "Datos invalidos", Alert.AlertType.ERROR);
                }
            }
            return null;
        });
        
        dialog.showAndWait();
    }
    
    private void mostrarFormularioRegistroAdministrador() {
        Dialog<Administrador> dialog = new Dialog<>();
        dialog.setTitle("Registro de Administrador");
        dialog.setHeaderText(null);
        
        // Custom header
        VBox header = new VBox(8);
        header.setPadding(new Insets(20, 20, 10, 20));
        header.setStyle("-fx-background-color: #F5F7FA;");
        
        Label titulo = new Label("Registro de Administrador");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        Label subtitulo = new Label("Complete los datos del nuevo administrador");
        subtitulo.setStyle("-fx-font-size: 13px; -fx-text-fill: #757575; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        header.getChildren().addAll(titulo, subtitulo);
        
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25));
        
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        txtNombre.setPrefWidth(300);
        txtNombre.setPrefHeight(44);
        txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtNombre.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtNombre.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtCedula = new TextField();
        txtCedula.setPromptText("Número de cédula");
        txtCedula.setPrefWidth(300);
        txtCedula.setPrefHeight(44);
        txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtCedula.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtCedula.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Edad en años");
        txtEdad.setPrefWidth(300);
        txtEdad.setPrefHeight(44);
        txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtEdad.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtEdad.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtIdEmpleado = new TextField();
        txtIdEmpleado.setPromptText("ID de empleado");
        txtIdEmpleado.setPrefWidth(300);
        txtIdEmpleado.setPrefHeight(44);
        txtIdEmpleado.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtIdEmpleado.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtIdEmpleado.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtIdEmpleado.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        TextField txtSueldo = new TextField();
        txtSueldo.setPromptText("Sueldo mensual");
        txtSueldo.setPrefWidth(300);
        txtSueldo.setPrefHeight(44);
        txtSueldo.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        txtSueldo.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtSueldo.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #4527A0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            } else {
                txtSueldo.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                        + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                        + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
            }
        });
        
        Label lblNombre = new Label("Nombre:");
        lblNombre.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblCedula = new Label("Cédula:");
        lblCedula.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblEdad = new Label("Edad:");
        lblEdad.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblId = new Label("ID Empleado:");
        lblId.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Label lblSueldo = new Label("Sueldo:");
        lblSueldo.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        Label lblFoto = new Label("Foto de Perfil (Opcional):");
        lblFoto.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        TextField txtRutaFoto = new TextField();
        txtRutaFoto.setPromptText("Ruta de la imagen");
        txtRutaFoto.setEditable(false);
        txtRutaFoto.setPrefWidth(220);
        txtRutaFoto.setPrefHeight(44);
        txtRutaFoto.setStyle("-fx-font-size: 14px; -fx-background-color: #FFFFFF; "
                + "-fx-border-color: #D0D0D0; -fx-border-width: 1.5; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-padding: 0 16; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        Button btnSeleccionarFoto = new Button("Seleccionar");
        btnSeleccionarFoto.setPrefHeight(44);
        btnSeleccionarFoto.setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-background-radius: 8; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        btnSeleccionarFoto.setOnAction(e -> {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Seleccionar Foto de Perfil");
            fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            java.io.File archivo = fileChooser.showOpenDialog(dialog.getOwner());
            if (archivo != null) {
                txtRutaFoto.setText(archivo.getAbsolutePath());
            }
        });
        HBox fotoBox = new HBox(10);
        fotoBox.getChildren().addAll(txtRutaFoto, btnSeleccionarFoto);
        
        grid.add(lblNombre, 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(lblCedula, 0, 1);
        grid.add(txtCedula, 1, 1);
        grid.add(lblEdad, 0, 2);
        grid.add(txtEdad, 1, 2);
        grid.add(lblId, 0, 3);
        grid.add(txtIdEmpleado, 1, 3);
        grid.add(lblSueldo, 0, 4);
        grid.add(txtSueldo, 1, 4);
        grid.add(lblFoto, 0, 5);
        grid.add(fotoBox, 1, 5);
        
        VBox content = new VBox(0);
        content.getChildren().addAll(header, grid);
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        // Style the dialog buttons
        dialog.getDialogPane().lookupButton(ButtonType.OK).setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-background-radius: 8; -fx-cursor: hand; "
                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setStyle("-fx-background-color: transparent; -fx-text-fill: #757575; "
                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-border-color: #E0E0E0; -fx-border-width: 1; "
                + "-fx-border-radius: 8; -fx-background-radius: 8; -fx-cursor: hand; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");
        
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                try {
                    Administrador nuevoAdmin = controller.registrarNuevoAdministrador(
                        txtNombre.getText(),
                        txtCedula.getText(),
                        Integer.parseInt(txtEdad.getText()),
                        txtIdEmpleado.getText(),
                        Float.parseFloat(txtSueldo.getText())
                    );
                    if (nuevoAdmin != null && !txtRutaFoto.getText().isEmpty()) {
                        nuevoAdmin.setRutaFotoPerfil(txtRutaFoto.getText());
                    }
                    return nuevoAdmin;
                } catch (NumberFormatException ex) {
                    controller.mostrarAlerta("Error", "Datos invalidos", Alert.AlertType.ERROR);
                }
            }
            return null;
        });
        
        dialog.showAndWait();
    }
    
    public LoginController getController() {
        return controller;
    }

    private void abrirDashboardVisitante(Visitante visitante) {
        VisitanteView view = new VisitanteView(stage, visitante, parqueController);
        stage.setScene(view.crearEscena());
    }
    
    private void abrirDashboardOperador(Operador operador) {
        OperadorView view = new OperadorView(stage, operador, parqueController);
        stage.setScene(view.crearEscena());
    }
    
    private void abrirDashboardAdmin(Administrador admin) {
        AdminView view = new AdminView(stage, admin, parqueController);
        stage.setScene(view.crearEscena());
    }
}
