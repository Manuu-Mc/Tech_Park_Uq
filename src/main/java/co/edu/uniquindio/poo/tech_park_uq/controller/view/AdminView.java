package co.edu.uniquindio.poo.tech_park_uq.controller.view;



import co.edu.uniquindio.poo.tech_park_uq.controller.controller.AdminController;

import co.edu.uniquindio.poo.tech_park_uq.controller.controller.ParqueController;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Administrador;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Atraccion;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Operador;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties.Zona;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EspecialidadOperador;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoAtraccion;

import javafx.beans.property.SimpleStringProperty;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;



import java.util.List;



public class AdminView {

    

    private Stage stage;

    private Administrador administrador;

    private AdminController controller;

    

    public AdminView(Stage stage, Administrador administrador, ParqueController parqueController) {

        this.stage = stage;

        this.administrador = administrador;

        this.controller = new AdminController(administrador, parqueController);

    }

    

    public Scene crearEscena() {

        BorderPane root = new BorderPane();

        root.setStyle("-fx-background-color: #F5F7FA;");

        

        Label titulo = new Label("Panel de Administrador");

        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: 700; -fx-text-fill: white; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Button btnCerrarSesion = new Button("Cerrar Sesión");

        btnCerrarSesion.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 600; -fx-padding: 10 25; -fx-background-radius: 20; "

                + "-fx-border-color: rgba(255,255,255,0.5); -fx-border-width: 1.5; -fx-border-radius: 20; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnCerrarSesion.setOnMouseEntered(e -> btnCerrarSesion.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 600; -fx-padding: 10 25; -fx-background-radius: 20; "

                + "-fx-border-color: rgba(255,255,255,0.7); -fx-border-width: 1.5; -fx-border-radius: 20; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnCerrarSesion.setOnMouseExited(e -> btnCerrarSesion.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 600; -fx-padding: 10 25; -fx-background-radius: 20; "

                + "-fx-border-color: rgba(255,255,255,0.5); -fx-border-width: 1.5; -fx-border-radius: 20; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnCerrarSesion.setOnAction(e -> {

            LoginView loginView = new LoginView(stage, controller.getParqueController());

            stage.setScene(loginView.crearEscena());

        });



        HBox headerBox = new HBox();

        headerBox.setPadding(new Insets(25, 35, 25, 35));

        headerBox.setStyle("-fx-background-color: #C62828;");

        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();

        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        headerBox.getChildren().addAll(titulo, spacer, btnCerrarSesion);

        headerBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        root.setTop(headerBox);

        

        TabPane tabPane = new TabPane();

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabPane.setStyle("-fx-background-color: #F5F7FA;");

        tabPane.getTabs().addAll(

            crearTabInformacion(),

            crearTabGestionPersonal(),

            crearTabGestionOperadores(),

            crearTabGestionAtracciones(),

            crearTabControlClimatico(),

            crearTabReportes()

        );

        

        root.setCenter(tabPane);

        

        Scene scene = new Scene(root, 1920, 1080);

        scene.getStylesheets().add(getClass().getResource("/styles/modern-tables.css").toExternalForm());

        scene.getStylesheets().add(getClass().getResource("/styles/modern-tabs.css").toExternalForm());

        return scene;

    }

    

    private Tab crearTabInformacion() {

        Tab tab = new Tab("Mi Información");

        tab.setClosable(false);



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #F5F7FA;");



        VBox cardPrincipal = new VBox(25);

        cardPrincipal.setStyle("-fx-background-color: white; -fx-padding: 40; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label tituloInfo = new Label("Información Personal");

        tituloInfo.setStyle("-fx-font-size: 24px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        GridPane gridInfo = new GridPane();

        gridInfo.setHgap(40);

        gridInfo.setVgap(20);

        gridInfo.setStyle("-fx-padding: 0;");

        

        agregarCampoInfo(gridInfo, "Nombre Completo:", administrador.getNombre(), 0);

        agregarCampoInfo(gridInfo, "Cédula:", administrador.getCedula(), 1);

        agregarCampoInfo(gridInfo, "Edad:", administrador.getEdad() + " años", 2);

        agregarCampoInfo(gridInfo, "ID Empleado:", administrador.getIdEmpleado(), 3);

        

        Separator separator1 = new Separator();

        separator1.setStyle("-fx-border-color: #E0E0E0;");

        

        VBox seccionFoto = crearSeccionFoto();

        

        HBox infoBox = new HBox(40);

        infoBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        infoBox.setStyle("-fx-padding: 20 0 0 0;");

        infoBox.getChildren().addAll(seccionFoto, gridInfo);

        

        cardPrincipal.getChildren().addAll(tituloInfo, separator1, infoBox);



        VBox cardLaboral = new VBox(25);

        cardLaboral.setStyle("-fx-background-color: white; -fx-padding: 40; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label tituloLaboral = new Label("Información Laboral");

        tituloLaboral.setStyle("-fx-font-size: 24px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        GridPane gridLaboral = new GridPane();

        gridLaboral.setHgap(40);

        gridLaboral.setVgap(20);

        gridLaboral.setStyle("-fx-padding: 20 0 0 0;");

        

        agregarCampoInfo(gridLaboral, "Cargo:", "Administrador del Parque", 0);

        agregarCampoInfo(gridLaboral, "Sueldo:", "$" + String.format("%,.0f", administrador.getSueldo()), 1);

        agregarCampoInfo(gridLaboral, "Departamento:", "Administración General", 2);

        

        Separator separator2 = new Separator();

        separator2.setStyle("-fx-border-color: #E0E0E0;");

        

        cardLaboral.getChildren().addAll(tituloLaboral, separator2, gridLaboral);



        root.getChildren().addAll(cardPrincipal, cardLaboral);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #F5F7FA;");

        tab.setContent(sp);

        return tab;

    }

    

    private VBox crearSeccionFoto() {

        VBox seccionFoto = new VBox(15);

        seccionFoto.setAlignment(javafx.geometry.Pos.CENTER);

        seccionFoto.setPrefWidth(200);



        javafx.scene.layout.StackPane fotoContainer = new javafx.scene.layout.StackPane();

        fotoContainer.setPrefSize(180, 180);

        fotoContainer.setMinSize(180, 180);

        fotoContainer.setMaxSize(180, 180);

        fotoContainer.setStyle("-fx-background-color: #FFEBEE; "

                + "-fx-background-radius: 12; -fx-border-color: #C62828; -fx-border-width: 4; -fx-border-radius: 12; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.3), 10, 0, 0, 2);");



        if (administrador.tieneFotoPerfil()) {

            try {

                javafx.scene.image.Image fotoPerfil = new javafx.scene.image.Image(

                    "file:" + administrador.getRutaFotoPerfil());

                javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(fotoPerfil);

                imageView.setFitWidth(172);

                imageView.setFitHeight(172);

                imageView.setPreserveRatio(false);



                javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(172, 172);

                clip.setArcWidth(12);

                clip.setArcHeight(12);

                imageView.setClip(clip);



                fotoContainer.getChildren().add(imageView);

            } catch (Exception ex) {

                Label lblSinFoto = new Label("\uD83D\uDC64");

                lblSinFoto.setStyle("-fx-font-size: 80px; -fx-text-fill: #C62828;");

                fotoContainer.getChildren().add(lblSinFoto);

            }

        } else {

            Label lblSinFoto = new Label("\uD83D\uDC64");

            lblSinFoto.setStyle("-fx-font-size: 80px; -fx-text-fill: #C62828;");

            fotoContainer.getChildren().add(lblSinFoto);

        }



        Button btnCambiarFoto = new Button("\uD83D\uDCF7 Cambiar Foto");

        btnCambiarFoto.setPrefWidth(180);

        btnCambiarFoto.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; "

                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-padding: 10 20; -fx-background-radius: 8; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-cursor: hand;");

        btnCambiarFoto.setOnMouseEntered(e -> btnCambiarFoto.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white; "

                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-padding: 10 20; -fx-background-radius: 8; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnCambiarFoto.setOnMouseExited(e -> btnCambiarFoto.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; "

                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-padding: 10 20; -fx-background-radius: 8; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnCambiarFoto.setOnAction(e -> {

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();

            fileChooser.setTitle("Seleccionar Foto de Perfil");

            fileChooser.getExtensionFilters().addAll(

                new javafx.stage.FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")

            );

            java.io.File archivo = fileChooser.showOpenDialog(stage);

            if (archivo != null) {

                administrador.setRutaFotoPerfil(archivo.getAbsolutePath());

                new Alert(Alert.AlertType.INFORMATION, "Foto de perfil actualizada correctamente").show();

                stage.setScene(crearEscena());

            }

        });



        seccionFoto.getChildren().addAll(fotoContainer, btnCambiarFoto);

        return seccionFoto;

    }



    private void agregarCampoInfo(GridPane grid, String etiqueta, String valor, int fila) {

        Label lblEtiqueta = new Label(etiqueta);

        lblEtiqueta.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575; -fx-font-weight: 600; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        lblEtiqueta.setPrefWidth(180);

        

        Label lblValor = new Label(valor);

        lblValor.setStyle("-fx-font-size: 16px; -fx-text-fill: #212121; -fx-font-weight: 500; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        grid.add(lblEtiqueta, 0, fila);

        grid.add(lblValor, 1, fila);

    }

    

    private Tab crearTabGestionPersonal() {

        Tab tab = new Tab("Gestión de Personal");

        tab.setClosable(false);

        

        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #F5F7FA;");

        

        VBox cardFormulario = new VBox(25);

        cardFormulario.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label tituloRegistro = new Label("Registrar Nuevo Operador");

        tituloRegistro.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        GridPane formulario = new GridPane();

        formulario.setHgap(20);

        formulario.setVgap(18);

        formulario.setStyle("-fx-padding: 15 0 0 0;");

        

        TextField txtNombre = new TextField();

        txtNombre.setPromptText("Nombre completo");

        txtNombre.setPrefWidth(300);

        txtNombre.setPrefHeight(48);

        txtNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtNombre.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        TextField txtCedula = new TextField();

        txtCedula.setPromptText("Número de cédula");

        txtCedula.setPrefWidth(300);

        txtCedula.setPrefHeight(48);

        txtCedula.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtCedula.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtCedula.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtCedula.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        TextField txtEdad = new TextField();

        txtEdad.setPromptText("Edad");

        txtEdad.setPrefWidth(300);

        txtEdad.setPrefHeight(48);

        txtEdad.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtEdad.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtEdad.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtEdad.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        TextField txtIdEmpleado = new TextField();

        txtIdEmpleado.setPromptText("ID de empleado");

        txtIdEmpleado.setPrefWidth(300);

        txtIdEmpleado.setPrefHeight(48);

        txtIdEmpleado.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtIdEmpleado.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtIdEmpleado.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtIdEmpleado.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        ComboBox<String> cmbZona = new ComboBox<>();

        cmbZona.getItems().addAll("Zona Acuática", "Zona Mecánica", "Zona Infantil");

        cmbZona.setPromptText("Seleccionar zona");

        cmbZona.setPrefWidth(300);

        cmbZona.setPrefHeight(48);

        cmbZona.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        

        ComboBox<EspecialidadOperador> cmbEspecialidad = new ComboBox<>();

        cmbEspecialidad.getItems().addAll(EspecialidadOperador.values());

        cmbEspecialidad.setValue(EspecialidadOperador.GENERAL);

        cmbEspecialidad.setPrefWidth(300);

        cmbEspecialidad.setPrefHeight(48);

        cmbEspecialidad.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        

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

        Label lblEsp = new Label("Especialidad:");

        lblEsp.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        formulario.add(lblNombre, 0, 0);

        formulario.add(txtNombre, 1, 0);

        formulario.add(lblCedula, 0, 1);

        formulario.add(txtCedula, 1, 1);

        formulario.add(lblEdad, 0, 2);

        formulario.add(txtEdad, 1, 2);

        formulario.add(lblId, 0, 3);

        formulario.add(txtIdEmpleado, 1, 3);

        formulario.add(lblZona, 0, 4);

        formulario.add(cmbZona, 1, 4);

        formulario.add(lblEsp, 0, 5);

        formulario.add(cmbEspecialidad, 1, 5);

        

        HBox botones = new HBox(15);

        botones.setStyle("-fx-padding: 15 0 0 0;");

        

        Button btnContratar = new Button("Contratar Operador");

        btnContratar.setPrefHeight(48);

        btnContratar.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white; -fx-font-size: 15px; "

                + "-fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnContratar.setOnMouseEntered(e -> btnContratar.setStyle("-fx-background-color: #1B5E20; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnContratar.setOnMouseExited(e -> btnContratar.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        

        Button btnDesvincular = new Button("Despedir Empleado");

        btnDesvincular.setPrefHeight(48);

        btnDesvincular.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; -fx-font-size: 15px; "

                + "-fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnDesvincular.setOnMouseEntered(e -> btnDesvincular.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnDesvincular.setOnMouseExited(e -> btnDesvincular.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        

        botones.getChildren().addAll(btnContratar, btnDesvincular);

        

        Separator separator1 = new Separator();

        separator1.setStyle("-fx-border-color: #E0E0E0;");

        

        cardFormulario.getChildren().addAll(tituloRegistro, separator1, formulario, botones);

        

        VBox cardTabla = new VBox(20);

        cardTabla.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label tituloTabla = new Label("Personal Registrado");

        tituloTabla.setStyle("-fx-font-size: 20px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        TableView<Operador> tablaOperadores = new TableView<>();

        tablaOperadores.setItems(controller.getOperadoresObservable());

        tablaOperadores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        tablaOperadores.setPrefHeight(350);

        tablaOperadores.setStyle("-fx-background-color: #F8FAFC; -fx-border-color: #E0E0E0; -fx-border-width: 1; "

                + "-fx-border-radius: 12; -fx-background-radius: 12;");

        

        TableColumn<Operador, String> colNombre = new TableColumn<>("Nombre");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colNombre.setPrefWidth(200);

        

        TableColumn<Operador, String> colCedula = new TableColumn<>("Cédula");

        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));

        colCedula.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colCedula.setPrefWidth(150);

        

        TableColumn<Operador, String> colId = new TableColumn<>("ID Empleado");

        colId.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));

        colId.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colId.setPrefWidth(150);

        

        TableColumn<Operador, String> colEspecialidad = new TableColumn<>("Especialidad");

        colEspecialidad.setCellValueFactory(d -> 

            new SimpleStringProperty(d.getValue().getEspecialidad().getNombre()));

        colEspecialidad.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colEspecialidad.setPrefWidth(180);

        

        TableColumn<Operador, Void> colCambiar = new TableColumn<>("Cambiar Especialidad");

        colCambiar.setCellFactory(p -> new TableCell<>() {

            private final ComboBox<EspecialidadOperador> cmbCambio = new ComboBox<>();

            private final Button btnCambiar = new Button("Cambiar");

            private final HBox box = new HBox(8, cmbCambio, btnCambiar);

            {

                cmbCambio.getItems().addAll(EspecialidadOperador.values());

                cmbCambio.setPrefWidth(140);

                cmbCambio.setPrefHeight(40);

                cmbCambio.setStyle("-fx-font-size: 14px; -fx-font-weight: 500; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-background-color: #F8FAFC; -fx-border-color: #CBD5E1; -fx-border-width: 2; "

                        + "-fx-border-radius: 10; -fx-background-radius: 10;");

                btnCambiar.setPrefHeight(40);

                btnCambiar.setStyle("-fx-background-color: #6366F1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: 700; "

                        + "-fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand; "

                        + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

                btnCambiar.setOnAction(e -> {

                    Operador op = getTableView().getItems().get(getIndex());

                    EspecialidadOperador nueva = cmbCambio.getValue();

                    if (nueva != null) {

                        op.setEspecialidad(nueva);

                        getTableView().refresh();

                        new Alert(Alert.AlertType.INFORMATION, 

                            "Especialidad de " + op.getNombre() + " cambiada a " + nueva.getNombre()).show();

                    }

                });

            }

            @Override

            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {

                    setGraphic(null);

                } else {

                    Operador op = getTableView().getItems().get(getIndex());

                    cmbCambio.setValue(op.getEspecialidad());

                    setGraphic(box);

                }

            }

        });

        colCambiar.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER;");

        colCambiar.setPrefWidth(220);

        

        tablaOperadores.getColumns().addAll(List.of(colNombre, colCedula, colId, colEspecialidad, colCambiar));

        

        btnContratar.setOnAction(e -> {

            try {

                Zona zona = new Zona(cmbZona.getValue(), 100);

                controller.contratarOperador(

                    txtNombre.getText(),

                    txtCedula.getText(),

                    Integer.parseInt(txtEdad.getText()),

                    txtIdEmpleado.getText(),

                    zona,

                    cmbEspecialidad.getValue()

                );

                limpiarCampos(txtNombre, txtCedula, txtEdad, txtIdEmpleado);

                cmbZona.setValue(null);

            } catch (Exception ex) {

                new Alert(Alert.AlertType.ERROR, "Error en los datos").show();

            }

        });

        

        btnDesvincular.setOnAction(e -> {

            Operador seleccionado = tablaOperadores.getSelectionModel().getSelectedItem();

            if (seleccionado != null) {

                controller.desvincularEmpleado(seleccionado);

                tablaOperadores.refresh();

            }

        });

        

        Separator separator2 = new Separator();

        separator2.setStyle("-fx-border-color: #E0E0E0;");

        

        cardTabla.getChildren().addAll(tituloTabla, separator2, tablaOperadores);

        

        root.getChildren().addAll(cardFormulario, cardTabla);

        ScrollPane spPersonal = new ScrollPane(root);

        spPersonal.setFitToWidth(true);

        spPersonal.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(spPersonal);

        return tab;

    }

    

    private Tab crearTabGestionOperadores() {

        Tab tab = new Tab("Gestión de Operadores");

        tab.setClosable(false);



        VBox content = new VBox(30);

        content.setPadding(new Insets(40));

        content.setStyle("-fx-background-color: #F5F7FA;");



        VBox cardTabla = new VBox(20);

        cardTabla.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");



        Label titulo = new Label("Lista de Operadores del Parque");

        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        TableView<Operador> tablaOperadores = new TableView<>();

        tablaOperadores.setItems(controller.getOperadoresObservable());

        tablaOperadores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        tablaOperadores.setPrefHeight(400);

        tablaOperadores.setStyle("-fx-background-color: #F8FAFC; -fx-border-color: #E0E0E0; -fx-border-width: 1; "

                + "-fx-border-radius: 12; -fx-background-radius: 12;");



        TableColumn<Operador, String> colNombre = new TableColumn<>("Nombre");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colNombre.setPrefWidth(200);



        TableColumn<Operador, String> colCedula = new TableColumn<>("Cédula");

        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));

        colCedula.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colCedula.setPrefWidth(150);



        TableColumn<Operador, String> colId = new TableColumn<>("ID Empleado");

        colId.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));

        colId.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colId.setPrefWidth(150);



        TableColumn<Operador, String> colEspecialidad = new TableColumn<>("Especialidad");

        colEspecialidad.setCellValueFactory(d -> 

            new SimpleStringProperty(d.getValue().getEspecialidad().getNombre()));

        colEspecialidad.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colEspecialidad.setPrefWidth(180);



        TableColumn<Operador, String> colZona = new TableColumn<>("Zona");

        colZona.setCellValueFactory(d -> 

            new SimpleStringProperty(d.getValue().getZonaAsignada().getNombre()));

        colZona.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colZona.setPrefWidth(200);



        tablaOperadores.getColumns().addAll(List.of(colNombre, colCedula, colId, colEspecialidad, colZona));



        Separator separator = new Separator();

        separator.setStyle("-fx-border-color: #E0E0E0;");



        Label lblInfo = new Label("Los operadores solo pueden gestionar atracciones de su especialidad.");

        lblInfo.setStyle("-fx-font-size: 13px; -fx-text-fill: #757575; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        cardTabla.getChildren().addAll(titulo, separator, tablaOperadores, lblInfo);

        content.getChildren().add(cardTabla);

        

        ScrollPane spOperadores = new ScrollPane(content);

        spOperadores.setFitToWidth(true);

        spOperadores.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(spOperadores);

        return tab;

    }



    private Tab crearTabGestionAtracciones() {

        Tab tab = new Tab("Gestión de Atracciones");

        tab.setClosable(false);

        

        VBox content = new VBox(30);

        content.setPadding(new Insets(40));

        content.setStyle("-fx-background-color: #F5F7FA;");

        

        VBox cardFormulario = new VBox(25);

        cardFormulario.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label tituloFormulario = new Label("Crear Nueva Atracción");

        tituloFormulario.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        GridPane formulario = new GridPane();

        formulario.setHgap(20);

        formulario.setVgap(18);

        formulario.setStyle("-fx-padding: 15 0 0 0;");

        

        TextField txtId = new TextField();

        txtId.setPromptText("ID de la atracción");

        txtId.setPrefWidth(300);

        txtId.setPrefHeight(48);

        txtId.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtId.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtId.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtId.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        TextField txtNombre = new TextField();

        txtNombre.setPromptText("Nombre de la atracción");

        txtNombre.setPrefWidth(300);

        txtNombre.setPrefHeight(48);

        txtNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtNombre.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        TextField txtCapacidad = new TextField();

        txtCapacidad.setPromptText("Capacidad máxima");

        txtCapacidad.setPrefWidth(300);

        txtCapacidad.setPrefHeight(48);

        txtCapacidad.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtCapacidad.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtCapacidad.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtCapacidad.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        TextField txtAlturaMin = new TextField();

        txtAlturaMin.setPromptText("Altura mínima (m)");

        txtAlturaMin.setPrefWidth(300);

        txtAlturaMin.setPrefHeight(48);

        txtAlturaMin.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtAlturaMin.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtAlturaMin.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtAlturaMin.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        TextField txtEdadMin = new TextField();

        txtEdadMin.setPromptText("Edad mínima");

        txtEdadMin.setPrefWidth(300);

        txtEdadMin.setPrefHeight(48);

        txtEdadMin.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtEdadMin.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtEdadMin.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtEdadMin.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        TextField txtCosto = new TextField();

        txtCosto.setPromptText("Costo adicional");

        txtCosto.setPrefWidth(300);

        txtCosto.setPrefHeight(48);

        txtCosto.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        txtCosto.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal) {

                txtCosto.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #FFFFFF; "

                        + "-fx-border-color: #6366F1; -fx-border-width: 2.5; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(99,102,241,0.2), 12, 0, 0, 4);");

            } else {

                txtCosto.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                        + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                        + "-fx-background-radius: 12; -fx-padding: 0 20; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                        + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

            }

        });

        

        ComboBox<TipoAtraccion> cmbTipo = new ComboBox<>();

        cmbTipo.getItems().addAll(TipoAtraccion.values());

        cmbTipo.setPromptText("Seleccionar tipo");

        cmbTipo.setPrefWidth(300);

        cmbTipo.setPrefHeight(48);

        cmbTipo.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        

        ComboBox<Operador> cmbOperador = new ComboBox<>();

        cmbOperador.setItems(controller.getOperadoresObservable());

        cmbOperador.setPromptText("Seleccionar operador (opcional)");

        cmbOperador.setPrefWidth(300);

        cmbOperador.setPrefHeight(48);

        cmbOperador.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-background-color: #F8FAFC; "

                + "-fx-border-color: #CBD5E1; -fx-border-width: 2; -fx-border-radius: 12; "

                + "-fx-background-radius: 12; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-prompt-text-fill: #94A3B8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);");

        

        Label lblId = new Label("ID:");

        lblId.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label lblNombre = new Label("Nombre:");

        lblNombre.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label lblCapacidad = new Label("Capacidad:");

        lblCapacidad.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label lblAltura = new Label("Altura Mín (m):");

        lblAltura.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label lblEdad = new Label("Edad Mín:");

        lblEdad.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label lblCosto = new Label("Costo Adicional:");

        lblCosto.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label lblTipo = new Label("Tipo:");

        lblTipo.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label lblOperador = new Label("Operador Asignado:");

        lblOperador.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: #424242; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        formulario.add(lblId, 0, 0);

        formulario.add(txtId, 1, 0);

        formulario.add(lblNombre, 0, 1);

        formulario.add(txtNombre, 1, 1);

        formulario.add(lblCapacidad, 0, 2);

        formulario.add(txtCapacidad, 1, 2);

        formulario.add(lblAltura, 0, 3);

        formulario.add(txtAlturaMin, 1, 3);

        formulario.add(lblEdad, 0, 4);

        formulario.add(txtEdadMin, 1, 4);

        formulario.add(lblCosto, 0, 5);

        formulario.add(txtCosto, 1, 5);

        formulario.add(lblTipo, 0, 6);

        formulario.add(cmbTipo, 1, 6);

        formulario.add(lblOperador, 0, 7);

        formulario.add(cmbOperador, 1, 7);

        

        HBox botones = new HBox(15);

        botones.setStyle("-fx-padding: 15 0 0 0;");

        

        Button btnCrear = new Button("Crear Atracción");

        btnCrear.setPrefHeight(48);

        btnCrear.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; -fx-font-size: 15px; "

                + "-fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnCrear.setOnMouseEntered(e -> btnCrear.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnCrear.setOnMouseExited(e -> btnCrear.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        

        Button btnEliminar = new Button("Eliminar Atracción");

        btnEliminar.setPrefHeight(48);

        btnEliminar.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; -fx-font-size: 15px; "

                + "-fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnEliminar.setOnMouseEntered(e -> btnEliminar.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnEliminar.setOnMouseExited(e -> btnEliminar.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        

        Button btnMantenimiento = new Button("Poner en Mantenimiento");

        btnMantenimiento.setPrefHeight(48);

        btnMantenimiento.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 15px; "

                + "-fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnMantenimiento.setOnMouseEntered(e -> btnMantenimiento.setStyle("-fx-background-color: #F57C00; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnMantenimiento.setOnMouseExited(e -> btnMantenimiento.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 14 35; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        

        botones.getChildren().addAll(btnCrear, btnEliminar, btnMantenimiento);

        

        Separator separator1 = new Separator();

        separator1.setStyle("-fx-border-color: #E0E0E0;");

        

        cardFormulario.getChildren().addAll(tituloFormulario, separator1, formulario, botones);

        

        VBox cardTabla = new VBox(20);

        cardTabla.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label tituloTabla = new Label("Atracciones Registradas");

        tituloTabla.setStyle("-fx-font-size: 20px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        TableView<Atraccion> tablaAtracciones = new TableView<>();

        tablaAtracciones.setItems(controller.getAtraccionesObservable());

        tablaAtracciones.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        tablaAtracciones.setPrefHeight(600);

        tablaAtracciones.setMinHeight(400);

        tablaAtracciones.setMaxHeight(Double.MAX_VALUE);

        tablaAtracciones.setMaxWidth(Double.MAX_VALUE);

        tablaAtracciones.setFixedCellSize(48);

        VBox.setVgrow(tablaAtracciones, javafx.scene.layout.Priority.ALWAYS);

        tablaAtracciones.setStyle("-fx-background-color: #F8FAFC; -fx-border-color: #E0E0E0; -fx-border-width: 1; "

                + "-fx-border-radius: 12; -fx-background-radius: 12; -fx-font-size: 15px;");

        

        TableColumn<Atraccion, String> colNombre = new TableColumn<>("Nombre");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colNombre.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colNombre.setMinWidth(300);

        

        TableColumn<Atraccion, String> colTipo = new TableColumn<>("Tipo");

        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAtraccion"));

        colTipo.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colTipo.setMinWidth(220);

        

        TableColumn<Atraccion, String> colEstado = new TableColumn<>("Estado");

        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoActual"));

        colEstado.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-alignment: CENTER-LEFT;");

        colEstado.setMinWidth(220);

        

        tablaAtracciones.getColumns().addAll(List.of(colNombre, colTipo, colEstado));

        

        btnCrear.setOnAction(e -> {

            try {

                controller.crearAtraccion(

                    txtId.getText(),

                    txtNombre.getText(),

                    Integer.parseInt(txtCapacidad.getText()),

                    Float.parseFloat(txtAlturaMin.getText()),

                    Integer.parseInt(txtEdadMin.getText()),

                    Float.parseFloat(txtCosto.getText()),

                    cmbTipo.getValue(),

                    cmbOperador.getValue()

                );

                limpiarCampos(txtId, txtNombre, txtCapacidad, txtAlturaMin, txtEdadMin, txtCosto);

                cmbOperador.setValue(null);

            } catch (Exception ex) {

                new Alert(Alert.AlertType.ERROR, "Error en los datos").show();

            }

        });

        

        btnEliminar.setOnAction(e -> {

            Atraccion seleccionada = tablaAtracciones.getSelectionModel().getSelectedItem();

            controller.eliminarAtraccion(seleccionada);

        });

        

        btnMantenimiento.setOnAction(e -> {

            Atraccion seleccionada = tablaAtracciones.getSelectionModel().getSelectedItem();

            if (seleccionada != null) {

                controller.ponerEnMantenimiento(seleccionada);

                tablaAtracciones.refresh();

            } else {

                new Alert(Alert.AlertType.ERROR, "Seleccione una atracción").show();

            }

        });

        

        Separator separator2 = new Separator();

        separator2.setStyle("-fx-border-color: #E0E0E0;");

        

        cardTabla.getChildren().addAll(tituloTabla, separator2, tablaAtracciones);

        VBox.setVgrow(cardTabla, javafx.scene.layout.Priority.ALWAYS);

        

        content.getChildren().addAll(cardFormulario, cardTabla);

        

        ScrollPane sp = new ScrollPane(content);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(sp);

        return tab;

    }

    

    private Tab crearTabControlClimatico() {

        Tab tab = new Tab("Control Climático");

        tab.setClosable(false);

        

        VBox content = new VBox(30);

        content.setPadding(new Insets(40));

        content.setStyle("-fx-background-color: #F5F7FA;");

        

        // Header con título

        VBox headerBox = new VBox(10);

        headerBox.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label titulo = new Label("Control Climático del Parque");

        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label descripcion = new Label("Gestione las alertas climáticas para proteger atracciones sensibles");

        descripcion.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575; -fx-font-weight: 500; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        headerBox.getChildren().addAll(titulo, descripcion);

        

        // Panel de control

        VBox controlPanel = new VBox(20);

        controlPanel.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label tituloControl = new Label("Panel de Control");

        tituloControl.setStyle("-fx-font-size: 18px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        HBox botonesBox = new HBox(15);

        botonesBox.setAlignment(javafx.geometry.Pos.CENTER);

        

        // Botón Activar Alerta

        Button btnActivar = new Button("Activar Alerta");

        btnActivar.setPrefWidth(220);

        btnActivar.setPrefHeight(48);

        btnActivar.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; -fx-font-size: 15px; "

                + "-fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnActivar.setOnMouseEntered(e -> btnActivar.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnActivar.setOnMouseExited(e -> btnActivar.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        

        // Botón Desactivar Alerta

        Button btnDesactivar = new Button("Desactivar Alerta");

        btnDesactivar.setPrefWidth(220);

        btnDesactivar.setPrefHeight(48);

        btnDesactivar.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; -fx-font-size: 15px; "

                + "-fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnDesactivar.setOnMouseEntered(e -> btnDesactivar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnDesactivar.setOnMouseExited(e -> btnDesactivar.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        

        btnActivar.setOnAction(e -> controller.activarAlertaClimatica());

        btnDesactivar.setOnAction(e -> controller.desactivarAlertaClimatica());

        

        botonesBox.getChildren().addAll(btnActivar, btnDesactivar);

        

        // Información adicional

        Label infoAdicional = new Label("Las alertas climáticas se activan automáticamente en condiciones adversas");

        infoAdicional.setStyle("-fx-font-size: 13px; -fx-text-fill: #757575; -fx-font-weight: 500; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-alignment: center;");

        

        Separator separatorClima = new Separator();

        separatorClima.setStyle("-fx-border-color: #E0E0E0;");

        

        controlPanel.getChildren().addAll(tituloControl, separatorClima, botonesBox, infoAdicional);

        

        content.getChildren().addAll(headerBox, controlPanel);

        

        ScrollPane spClima = new ScrollPane(content);

        spClima.setFitToWidth(true);

        spClima.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(spClima);

        return tab;

    }

    

    private Tab crearTabReportes() {

        Tab tab = new Tab("Reportes");

        tab.setClosable(false);

        

        VBox content = new VBox(30);

        content.setPadding(new Insets(40));

        content.setStyle("-fx-background-color: #F5F7FA;");

        

        // Header con título y descripción

        VBox headerBox = new VBox(10);

        headerBox.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        Label titulo = new Label("Centro de Reportes");

        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label descripcion = new Label("Genere y visualice reportes detallados del parque en tiempo real");

        descripcion.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575; -fx-font-weight: 500; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Separator separator = new Separator();

        separator.setStyle("-fx-border-color: #E2E8F0;");

        

        HBox actionBox = new HBox(20);

        actionBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        

        Button btnGenerar = new Button("Generar Reporte Fin de Jornada");

        btnGenerar.setPrefHeight(48);

        btnGenerar.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; -fx-font-size: 15px; "

                + "-fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnGenerar.setOnMouseEntered(e -> btnGenerar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnGenerar.setOnMouseExited(e -> btnGenerar.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; "

                + "-fx-font-size: 15px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        

        Label lblInfo = new Label("Datos actualizados en tiempo real");

        lblInfo.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B; -fx-font-weight: 600; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        actionBox.getChildren().addAll(btnGenerar, lblInfo);

        

        headerBox.getChildren().addAll(titulo, descripcion, separator, actionBox);

        

        // Ãrea de reporte con diseño moderno

        VBox reporteBox = new VBox(20);

        reporteBox.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(198,40,40,0.08), 25, 0, 0, 8);");

        

        HBox reportHeader = new HBox(15);

        reportHeader.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        

        Label lblTituloReporte = new Label("Informe Detallado");

        lblTituloReporte.setStyle("-fx-font-size: 20px; -fx-font-weight: 700; -fx-text-fill: #C62828; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label lblEstado = new Label("Estado: Esperando generación...");

        lblEstado.setStyle("-fx-font-size: 13px; -fx-text-fill: #94A3B8; -fx-font-weight: 600; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        reportHeader.getChildren().addAll(lblTituloReporte, lblEstado);

        

        Separator separator2 = new Separator();

        separator2.setStyle("-fx-border-color: #E2E8F0;");

        

        TextArea txtReporte = new TextArea();

        txtReporte.setEditable(false);

        txtReporte.setPrefHeight(400);

        txtReporte.setStyle("-fx-font-family: 'Segoe UI', 'Roboto', 'Helvetica Neue', sans-serif; -fx-font-size: 14px; "

                + "-fx-font-weight: 500; -fx-background-color: #F8FAFC; -fx-border-color: #E0E0E0; -fx-border-width: 1; "

                + "-fx-border-radius: 12; -fx-background-radius: 12; -fx-padding: 20; "

                + "-fx-line-height: 1.6; -fx-text-fill: #334155; -fx-wrap-text: true;");

        txtReporte.setPromptText("Presione el botón 'Generar Reporte' para visualizar el informe completo con estadísticas detalladas del parque...");

        

        btnGenerar.setOnAction(e -> {

            String reporte = controller.generarReporteDiario();

            txtReporte.setText(reporte);

            lblEstado.setText("Estado: Reporte generado exitosamente");

            lblEstado.setStyle("-fx-font-size: 13px; -fx-text-fill: #10B981; -fx-font-weight: 600; "

                    + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        });

        

        // Footer con información adicional

        HBox footerBox = new HBox(15);

        footerBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        footerBox.setStyle("-fx-padding: 10 0 0 0;");

        

        Label lblFooter = new Label("Tip: Los reportes incluyen información de atracciones, operadores y estado climático");

        lblFooter.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B; -fx-font-weight: 500; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        footerBox.getChildren().add(lblFooter);

        

        reporteBox.getChildren().addAll(reportHeader, separator2, txtReporte, footerBox);

        

        content.getChildren().addAll(headerBox, reporteBox);

        

        ScrollPane spReportes = new ScrollPane(content);

        spReportes.setFitToWidth(true);

        spReportes.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(spReportes);

        return tab;

    }

    

    private void limpiarCampos(TextField... campos) {

        for (TextField campo : campos) {

            campo.clear();

        }

    }

}

