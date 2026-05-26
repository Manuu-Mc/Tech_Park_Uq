package co.edu.uniquindio.poo.tech_park_uq.controller.view;

import co.edu.uniquindio.poo.tech_park_uq.controller.controller.OperadorController;
import co.edu.uniquindio.poo.tech_park_uq.controller.controller.ParqueController;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.Atraccion;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.Operador;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.SolicitudAcceso;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;



public class OperadorView {

    

    private Stage stage;

    private Operador operador;

    private OperadorController controller;

    

    public OperadorView(Stage stage, Operador operador, ParqueController parqueController) {

        this.stage = stage;

        this.operador = operador;

        this.controller = new OperadorController(operador, parqueController);

    }

    

    public Scene crearEscena() {

        BorderPane root = new BorderPane();

        root.setTop(crearHeader());

        root.setCenter(crearTabs());

        

        Scene scene = new Scene(root, 1920, 1080);

        scene.getStylesheets().add(getClass().getResource("/styles/modern-tables.css").toExternalForm());

        scene.getStylesheets().add(getClass().getResource("/styles/modern-tabs.css").toExternalForm());

        return scene;

    }

    

    private VBox crearHeader() {

        VBox header = new VBox(10);

        header.setPadding(new Insets(20, 30, 20, 30));

        header.setStyle("-fx-background-color: #1976D2;");

        

        Label titulo = new Label("Panel de Operador");

        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");

        

        String nombreZona = "N/A";

        if (operador != null && operador.getZonaAsignada() != null) {

            nombreZona = operador.getZonaAsignada().getNombre();

        }

        Label infoZona = new Label("Zona Asignada: " + nombreZona + " | Especialidad: " + operador.getEspecialidad().getNombre());

        infoZona.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");



        Button btnCerrarSesion = new Button("Cerrar Sesión");

        btnCerrarSesion.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 25; -fx-background-radius: 20; "

                + "-fx-border-color: white; -fx-border-width: 1.5; -fx-border-radius: 20;");

        btnCerrarSesion.setOnMouseEntered(e -> btnCerrarSesion.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 25; -fx-background-radius: 20; "

                + "-fx-border-color: white; -fx-border-width: 1.5; -fx-border-radius: 20; -fx-cursor: hand;"));

        btnCerrarSesion.setOnMouseExited(e -> btnCerrarSesion.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 25; -fx-background-radius: 20; "

                + "-fx-border-color: white; -fx-border-width: 1.5; -fx-border-radius: 20;"));

        btnCerrarSesion.setOnAction(e -> {

            LoginView loginView = new LoginView(stage, controller.getParqueController());

            stage.setScene(loginView.crearEscena());

        });



        HBox tituloRow = new HBox();

        Region spacer = new Region();

        HBox.setHgrow(spacer, Priority.ALWAYS);

        tituloRow.getChildren().addAll(titulo, spacer, btnCerrarSesion);

        tituloRow.setAlignment(javafx.geometry.Pos.CENTER_LEFT);



        header.getChildren().addAll(tituloRow, infoZona);

        return header;

    }

    

    private TabPane crearTabs() {

        TabPane tabPane = new TabPane();

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabPane.getTabs().addAll(

            crearTabInformacion(),

            crearTabControlAcceso(),

            crearTabMantenimiento()

        );

        return tabPane;

    }

    

    private Tab crearTabInformacion() {

        Tab tab = new Tab("Mi Información");

        tab.setClosable(false);



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #FAFAFA;");



        VBox cardPrincipal = new VBox(25);

        cardPrincipal.setStyle("-fx-background-color: white; -fx-padding: 40; -fx-background-radius: 16; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 15, 0, 0, 3);");

        

        Label tituloInfo = new Label("Información Personal");

        tituloInfo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1976D2;");

        

        GridPane gridInfo = new GridPane();

        gridInfo.setHgap(40);

        gridInfo.setVgap(20);

        gridInfo.setStyle("-fx-padding: 20 0 0 0;");

        

        agregarCampoInfo(gridInfo, "Nombre Completo:", operador.getNombre(), 0);

        agregarCampoInfo(gridInfo, "Cédula:", operador.getCedula(), 1);

        agregarCampoInfo(gridInfo, "Edad:", operador.getEdad() + " años", 2);

        agregarCampoInfo(gridInfo, "ID Empleado:", operador.getIdEmpleado(), 3);

        

        VBox seccionFoto = crearSeccionFoto();

        

        HBox infoBox = new HBox(40);

        infoBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        infoBox.setStyle("-fx-padding: 20 0 0 0;");

        infoBox.getChildren().addAll(seccionFoto, gridInfo);

        

        cardPrincipal.getChildren().addAll(tituloInfo, new Separator(), infoBox);



        VBox cardLaboral = new VBox(25);

        cardLaboral.setStyle("-fx-background-color: white; -fx-padding: 40; -fx-background-radius: 16; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 15, 0, 0, 3);");

        

        Label tituloLaboral = new Label("Información Laboral");

        tituloLaboral.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1976D2;");

        

        GridPane gridLaboral = new GridPane();

        gridLaboral.setHgap(40);

        gridLaboral.setVgap(20);

        gridLaboral.setStyle("-fx-padding: 20 0 0 0;");

        

        String nombreZona = operador.getZonaAsignada() != null ? operador.getZonaAsignada().getNombre() : "No asignada";

        agregarCampoInfo(gridLaboral, "Zona Asignada:", nombreZona, 0);

        agregarCampoInfo(gridLaboral, "Especialidad:", operador.getEspecialidad().getNombre(), 1);

        agregarCampoInfo(gridLaboral, "Cargo:", "Operador de Atracciones", 2);

        

        cardLaboral.getChildren().addAll(tituloLaboral, new Separator(), gridLaboral);



        root.getChildren().addAll(cardPrincipal, cardLaboral);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #FAFAFA;");

        tab.setContent(sp);

        return tab;

    }

    

    private VBox crearSeccionFoto() {

        VBox seccionFoto = new VBox(15);

        seccionFoto.setAlignment(javafx.geometry.Pos.CENTER);

        seccionFoto.setPrefWidth(200);



        StackPane fotoContainer = new StackPane();

        fotoContainer.setPrefSize(180, 180);

        fotoContainer.setMinSize(180, 180);

        fotoContainer.setMaxSize(180, 180);

        fotoContainer.setStyle("-fx-background-color: #E3F2FD; "

                + "-fx-background-radius: 12; -fx-border-color: #1976D2; -fx-border-width: 4; -fx-border-radius: 12; "

                + "-fx-effect: dropshadow(gaussian, rgba(25,118,210,0.3), 10, 0, 0, 2);");



        if (operador.tieneFotoPerfil()) {

            try {

                javafx.scene.image.Image fotoPerfil = new javafx.scene.image.Image(

                    "file:" + operador.getRutaFotoPerfil());

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

                lblSinFoto.setStyle("-fx-font-size: 80px; -fx-text-fill: #1976D2;");

                fotoContainer.getChildren().add(lblSinFoto);

            }

        } else {

            Label lblSinFoto = new Label("\uD83D\uDC64");

            lblSinFoto.setStyle("-fx-font-size: 80px; -fx-text-fill: #1976D2;");

            fotoContainer.getChildren().add(lblSinFoto);

        }



        Button btnCambiarFoto = new Button("\uD83D\uDCF7 Cambiar Foto");

        btnCambiarFoto.setPrefWidth(180);

        btnCambiarFoto.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; "

                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-padding: 10 20; -fx-background-radius: 8; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-cursor: hand;");

        btnCambiarFoto.setOnMouseEntered(e -> btnCambiarFoto.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; "

                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-padding: 10 20; -fx-background-radius: 8; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnCambiarFoto.setOnMouseExited(e -> btnCambiarFoto.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; "

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

                operador.setRutaFotoPerfil(archivo.getAbsolutePath());

                new Alert(Alert.AlertType.INFORMATION, "Foto de perfil actualizada correctamente").show();

                stage.setScene(crearEscena());

            }

        });



        seccionFoto.getChildren().addAll(fotoContainer, btnCambiarFoto);

        return seccionFoto;

    }



    private void agregarCampoInfo(GridPane grid, String etiqueta, String valor, int fila) {

        Label lblEtiqueta = new Label(etiqueta);

        lblEtiqueta.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575; -fx-font-weight: 600;");

        lblEtiqueta.setPrefWidth(180);

        

        Label lblValor = new Label(valor);

        lblValor.setStyle("-fx-font-size: 16px; -fx-text-fill: #212121; -fx-font-weight: 500;");

        

        grid.add(lblEtiqueta, 0, fila);

        grid.add(lblValor, 1, fila);

    }

    

    private Tab crearTabControlAcceso() {

        Tab tab = new Tab("Control de Acceso");

        tab.setClosable(false);



        VBox root = new VBox(25);

        root.setPadding(new Insets(30));

        root.setStyle("-fx-background-color: #FAFAFA;");



        VBox cardFiltro = new VBox(15);

        cardFiltro.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 12; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");

        

        Label lblFiltro = new Label("Filtrar Solicitudes por Atracción");

        lblFiltro.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1976D2;");



        ComboBox<Atraccion> cmbFiltroAtraccion = new ComboBox<>();

        cmbFiltroAtraccion.setItems(controller.getAtraccionesZona());

        cmbFiltroAtraccion.setPromptText("Todas las atracciones");

        cmbFiltroAtraccion.setPrefWidth(350);

        cmbFiltroAtraccion.setStyle("-fx-font-size: 14px;");

        

        cardFiltro.getChildren().addAll(lblFiltro, cmbFiltroAtraccion);



        VBox cardSolicitudes = new VBox(15);

        cardSolicitudes.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 12; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");

        

        Label lblPendientes = new Label("Solicitudes Pendientes de Aprobación");

        lblPendientes.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1976D2;");



        TableView<SolicitudAcceso> tablaSolicitudes = new TableView<>(controller.getSolicitudesPendientes());

        tablaSolicitudes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        tablaSolicitudes.setPrefHeight(450);



        cmbFiltroAtraccion.setOnAction(e -> {

            Atraccion seleccionada = cmbFiltroAtraccion.getValue();

            tablaSolicitudes.setItems(controller.getSolicitudesPorAtraccion(seleccionada));

        });



        TableColumn<SolicitudAcceso, String> colVisitante = new TableColumn<>("Visitante");

        colVisitante.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombreVisitante()));



        TableColumn<SolicitudAcceso, String> colCedula = new TableColumn<>("Cédula");

        colCedula.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCedulaVisitante()));

        

        TableColumn<SolicitudAcceso, String> colTicket = new TableColumn<>("Tipo Ticket");

        colTicket.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTipoTicket()));

        colTicket.setCellFactory(col -> new TableCell<>() {

            @Override

            protected void updateItem(String item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    setText(item);

                    if (item.equals("Fast-Pass")) {

                        setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6; -fx-background-radius: 4;");

                    } else if (item.equals("Familiar")) {

                        setStyle("-fx-background-color: #6A1B9A; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6; -fx-background-radius: 4;");

                    } else if (item.equals("General")) {

                        setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6; -fx-background-radius: 4;");

                    } else {

                        setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6; -fx-background-radius: 4;");

                    }

                }

            }

        });

        

        TableColumn<SolicitudAcceso, Integer> colPrioridad = new TableColumn<>("Prioridad");

        colPrioridad.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(d.getValue().getPrioridad()));

        colPrioridad.setCellFactory(col -> new TableCell<>() {

            @Override

            protected void updateItem(Integer item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    if (item == 1) {

                        setText("ALTA");

                        setStyle("-fx-text-fill: #FF6F00; -fx-font-weight: bold;");

                    } else {

                        setText("Normal");

                        setStyle("-fx-text-fill: #666;");

                    }

                }

            }

        });



        TableColumn<SolicitudAcceso, String> colAtraccion = new TableColumn<>("Atracción");

        colAtraccion.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombreAtraccion()));



        TableColumn<SolicitudAcceso, String> colPuntos = new TableColumn<>("Puntos");

        colPuntos.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getPuntosDescontados())));



        TableColumn<SolicitudAcceso, Void> colAcciones = new TableColumn<>("Acciones");

        colAcciones.setCellFactory(p -> new TableCell<>() {

            private final Button btnAprobar = new Button("Aprobar");

            private final Button btnRechazar = new Button("Rechazar");

            private final HBox box = new HBox(8, btnAprobar, btnRechazar);

            {

                btnAprobar.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white; -fx-font-size: 12px; "

                        + "-fx-padding: 6 16; -fx-background-radius: 6; -fx-font-weight: 600;");

                btnRechazar.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; -fx-font-size: 12px; "

                        + "-fx-padding: 6 16; -fx-background-radius: 6; -fx-font-weight: 600;");

                btnAprobar.setOnAction(e -> controller.aprobarSolicitud(getTableView().getItems().get(getIndex())));

                btnRechazar.setOnAction(e -> controller.rechazarSolicitud(getTableView().getItems().get(getIndex())));

            }

            @Override

            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                setGraphic(empty ? null : box);

            }

        });



        tablaSolicitudes.getColumns().addAll(List.of(colVisitante, colCedula, colTicket, colPrioridad, colAtraccion, colPuntos, colAcciones));



        Label lblInfo = new Label("Al aprobar: se registra el ingreso y se actualiza el historial del visitante.\n" +

            "Al rechazar: se devuelven los puntos al visitante.\n" +

            "PRIORIDAD ALTA = Fast-Pass (aprobar primero)");

        lblInfo.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

        lblInfo.setWrapText(true);

        

        cardSolicitudes.getChildren().addAll(lblPendientes, new Separator(), tablaSolicitudes, lblInfo);



        root.getChildren().addAll(cardFiltro, cardSolicitudes);

        tab.setContent(root);

        return tab;

    }

    

    private Tab crearTabMantenimiento() {

        Tab tab = new Tab("Mantenimiento");

        tab.setClosable(false);

        

        VBox root = new VBox(25);

        root.setPadding(new Insets(30));

        root.setStyle("-fx-background-color: #FAFAFA;");

        

        VBox cardMantenimiento = new VBox(15);

        cardMantenimiento.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 12; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");

        

        Label titulo = new Label("Gestión de Mantenimiento");

        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1976D2;");

        

        Label subtitulo = new Label("Atracciones en Mantenimiento:");

        subtitulo.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");

        

        TableView<Atraccion> tablaMantenimiento = new TableView<>();

        tablaMantenimiento.setItems(controller.getAtraccionesEnMantenimiento());

        tablaMantenimiento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        tablaMantenimiento.setPrefHeight(400);

        

        TableColumn<Atraccion, String> colNombre = new TableColumn<>("Atracción");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        

        TableColumn<Atraccion, String> colTipo = new TableColumn<>("Tipo");

        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAtraccion"));

        

        TableColumn<Atraccion, String> colEstado = new TableColumn<>("Estado");

        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoActual"));

        

        tablaMantenimiento.getColumns().addAll(List.of(colNombre, colTipo, colEstado));

        

        Button btnRegistrarRevision = new Button("Registrar Revisión Técnica");

        btnRegistrarRevision.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 14px; "

                + "-fx-padding: 12 30; -fx-background-radius: 8; -fx-font-weight: 600;");

        btnRegistrarRevision.setOnMouseEntered(e -> btnRegistrarRevision.setStyle("-fx-background-color: #F57C00; -fx-text-fill: white; -fx-font-size: 14px; "

                + "-fx-padding: 12 30; -fx-background-radius: 8; -fx-font-weight: 600; -fx-cursor: hand;"));

        btnRegistrarRevision.setOnMouseExited(e -> btnRegistrarRevision.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 14px; "

                + "-fx-padding: 12 30; -fx-background-radius: 8; -fx-font-weight: 600;"));

        

        btnRegistrarRevision.setOnAction(e -> {

            Atraccion seleccionada = tablaMantenimiento.getSelectionModel().getSelectedItem();

            controller.registrarRevisionTecnica(seleccionada);

            tablaMantenimiento.refresh();

        });

        

        Label lblInfo = new Label("Al registrar la revisión técnica:\n" +

            "- El estado cambiará a ACTIVA\n" +

            "- El contador de visitas se reiniciará\n" +

            "- La atracción estará disponible nuevamente");

        lblInfo.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

        lblInfo.setWrapText(true);

        

        cardMantenimiento.getChildren().addAll(titulo, subtitulo, new Separator(), tablaMantenimiento, btnRegistrarRevision, lblInfo);

        

        root.getChildren().add(cardMantenimiento);

        tab.setContent(root);

        return tab;

    }

}

