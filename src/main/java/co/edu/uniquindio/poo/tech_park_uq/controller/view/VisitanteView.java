package co.edu.uniquindio.poo.tech_park_uq.controller.view;

import co.edu.uniquindio.poo.tech_park_uq.controller.controller.ParqueController;
import co.edu.uniquindio.poo.tech_park_uq.controller.controller.VisitanteController;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.Atraccion;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.Visitante;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoAcceso;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.records.Notificacion;
import co.edu.uniquindio.poo.tech_park_uq.controller.modell.records.RegistroAcceso;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;



public class VisitanteView {



    private final Stage stage;

    private final Visitante visitante;

    private final VisitanteController controller;



    // Labels reactivos globales

    private Label lblSaldoTienda;

    private Label lblPuntosTienda;

    private Label lblSaldoAtracciones;

    private Label lblPuntosAtracciones;

    private Label lblTicketAtracciones;

    private Label lblSaldoPerfil;

    private Label lblPuntosPerfil;

    

    // Labels reactivos para Tienda de Puntos

    private Label lblTicketActual;

    private Label lblBeneficioTicket;

    private VBox cardPaqueteBasico;

    private VBox cardPaqueteEstandar;

    private VBox cardPaquetePremium;



    public VisitanteView(Stage stage, Visitante visitante, ParqueController parqueController) {

        this.stage = stage;

        this.visitante = visitante;

        this.controller = new VisitanteController(visitante, parqueController);

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



    // HEADER 



    private VBox crearHeader() {

        VBox header = new VBox();

        header.setPadding(new Insets(20, 30, 20, 30));

        header.setStyle("-fx-background-color: #4527A0;");



        HBox topRow = new HBox(20);

        topRow.setAlignment(Pos.CENTER_LEFT);

        

        Label titulo = new Label("Tech-Park UQ");

        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");

        

        Region spacer = new Region();

        HBox.setHgrow(spacer, Priority.ALWAYS);

        

        Label bienvenida = new Label("Hola, " + visitante.getNombre());

        bienvenida.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");



        Button btnCerrarSesion = new Button("Cerrar Sesi\u00f3n");

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

        

        topRow.getChildren().addAll(titulo, spacer, bienvenida, btnCerrarSesion);

        header.getChildren().add(topRow);

        return header;

    }



    private Label chip(String texto, String color) {

        Label l = new Label(texto);

        l.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-weight: bold; -fx-font-size: 13px; "

                + "-fx-padding: 4 14; -fx-background-radius: 20;");

        return l;

    }



    private void refrescarTodo() {

        if (lblSaldoTienda != null) lblSaldoTienda.setText("$" + fmt(visitante.getSaldoVirtual()));

        if (lblPuntosTienda != null) lblPuntosTienda.setText(String.valueOf(visitante.getPuntosTicket()) + " pts");

        if (lblSaldoAtracciones != null) lblSaldoAtracciones.setText("$" + fmt(visitante.getSaldoVirtual()));

        if (lblPuntosAtracciones != null) lblPuntosAtracciones.setText(String.valueOf(visitante.getPuntosTicket()) + " pts");

        if (lblTicketAtracciones != null) lblTicketAtracciones.setText(visitante.getTipoTicket().getNombre());

        if (lblSaldoPerfil != null) lblSaldoPerfil.setText("$" + fmt(visitante.getSaldoVirtual()));

        if (lblPuntosPerfil != null) lblPuntosPerfil.setText(String.valueOf(visitante.getPuntosTicket()));

        

        // Actualizar tienda de puntos

        if (lblTicketActual != null) {

            lblTicketActual.setText(visitante.getTipoTicket().getNombre());

        }

        if (lblBeneficioTicket != null) {

            actualizarBeneficioTicket();

        }

        if (cardPaqueteBasico != null) {

            actualizarPaquetes();

        }

    }



    // TABS 



    private TabPane crearTabs() {

        TabPane tp = new TabPane();

        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tp.getTabs().addAll(

                tabPerfil(),

                tabTienda(),

                tabTiendaPuntos(),

                tabAtracciones(),

                tabMapaParque(),

                tabFavoritos(),

                tabEstadisticas(),

                tabNotificaciones()

        );

        return tp;

    }



    // TAB PERFIL 



    private Tab tabPerfil() {

        Tab tab = new Tab("Mi Perfil");



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #FAFAFA;");



        // Tarjeta principal con foto y datos personales

        HBox cardPrincipal = new HBox(40);

        cardPrincipal.setStyle("-fx-background-color: white; -fx-padding: 40; -fx-background-radius: 16; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 15, 0, 0, 3);");

        cardPrincipal.setAlignment(Pos.CENTER_LEFT);

        

        // Sección izquierda: Foto de perfil

        VBox seccionFoto = new VBox(15);

        seccionFoto.setAlignment(Pos.CENTER);

        seccionFoto.setPrefWidth(200);

        

        StackPane fotoContainer = new StackPane();

        fotoContainer.setPrefSize(180, 180);

        fotoContainer.setMinSize(180, 180);

        fotoContainer.setMaxSize(180, 180);

        fotoContainer.setStyle("-fx-background-color: #E8EAF6; "

                + "-fx-background-radius: 12; -fx-border-color: #4527A0; -fx-border-width: 4; -fx-border-radius: 12; "

                + "-fx-effect: dropshadow(gaussian, rgba(69,39,160,0.3), 10, 0, 0, 2);");

        

        if (visitante.tieneFotoPerfil()) {

            try {

                javafx.scene.image.Image fotoPerfil = new javafx.scene.image.Image(

                    "file:" + visitante.getRutaFotoPerfil());

                javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(fotoPerfil);

                imageView.setFitWidth(172);

                imageView.setFitHeight(172);

                imageView.setPreserveRatio(false);

                

                javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(172, 172);

                clip.setArcWidth(12);

                clip.setArcHeight(12);

                imageView.setClip(clip);

                

                fotoContainer.getChildren().add(imageView);

            } catch (Exception e) {

                Label lblSinFoto = new Label("\uD83D\uDC64");

                lblSinFoto.setStyle("-fx-font-size: 80px; -fx-text-fill: #7E57C2;");

                fotoContainer.getChildren().add(lblSinFoto);

            }

        } else {

            Label lblSinFoto = new Label("\uD83D\uDC64");

            lblSinFoto.setStyle("-fx-font-size: 80px; -fx-text-fill: #7E57C2;");

            fotoContainer.getChildren().add(lblSinFoto);

        }

        

        Button btnCambiarFoto = new Button("\uD83D\uDCF7 Cambiar Foto");

        btnCambiarFoto.setPrefWidth(180);

        btnCambiarFoto.setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "

                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-padding: 10 20; -fx-background-radius: 8; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnCambiarFoto.setOnMouseEntered(e -> btnCambiarFoto.setStyle("-fx-background-color: #5E35B1; -fx-text-fill: white; "

                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-padding: 10 20; -fx-background-radius: 8; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-scale-y: 1.05; -fx-scale-x: 1.05;"));

        btnCambiarFoto.setOnMouseExited(e -> btnCambiarFoto.setStyle("-fx-background-color: #4527A0; -fx-text-fill: white; "

                + "-fx-font-size: 13px; -fx-font-weight: 600; -fx-padding: 10 20; -fx-background-radius: 8; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnCambiarFoto.setOnAction(e -> {

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();

            fileChooser.setTitle("Seleccionar Foto de Perfil");

            fileChooser.getExtensionFilters().addAll(

                new javafx.stage.FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")

            );

            java.io.File archivo = fileChooser.showOpenDialog(stage);

            if (archivo != null) {

                visitante.setRutaFotoPerfil(archivo.getAbsolutePath());

                alerta("Éxito", "Foto de perfil actualizada correctamente", Alert.AlertType.INFORMATION);

                stage.setScene(crearEscena());

            }

        });

        

        seccionFoto.getChildren().addAll(fotoContainer, btnCambiarFoto);

        

        // Sección derecha: Información personal

        VBox seccionInfo = new VBox(20);

        HBox.setHgrow(seccionInfo, Priority.ALWAYS);

        

        Label tituloInfo = new Label("Información Personal");

        tituloInfo.setStyle("-fx-font-size: 24px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        VBox datosContainer = new VBox(15);

        datosContainer.setStyle("-fx-padding: 10 0 0 0;");

        

        datosContainer.getChildren().addAll(

            crearFilaDato("\uD83D\uDC64", "Nombre Completo", visitante.getNombre()),

            crearFilaDato("", "Cédula", visitante.getCedula()),

            crearFilaDato("", "Edad", visitante.getEdad() + " años"),

            crearFilaDato("", "Estatura", visitante.getEstatura() + " metros")

        );

        

        seccionInfo.getChildren().addAll(tituloInfo, datosContainer);

        

        cardPrincipal.getChildren().addAll(seccionFoto, new Separator(javafx.geometry.Orientation.VERTICAL), seccionInfo);



        // Grid de tarjetas de billetera y puntos

        HBox gridCards = new HBox(20);

        gridCards.setAlignment(Pos.CENTER);

        

        // Tarjeta de billetera

        VBox cardBilletera = new VBox(20);

        cardBilletera.setPrefWidth(400);

        cardBilletera.setStyle("-fx-background-color: #43A047; "

                + "-fx-padding: 30; -fx-background-radius: 16; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0, 0, 3);");

        HBox.setHgrow(cardBilletera, Priority.ALWAYS);

        

        Label iconoBilletera = new Label("");

        iconoBilletera.setStyle("-fx-font-size: 40px;");

        

        Label tituloBilletera = new Label("Billetera Virtual");

        tituloBilletera.setStyle("-fx-font-size: 18px; -fx-font-weight: 600; -fx-text-fill: white; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        lblSaldoPerfil = new Label("$" + fmt(visitante.getSaldoVirtual()));

        lblSaldoPerfil.setStyle("-fx-font-size: 42px; -fx-font-weight: 700; -fx-text-fill: white; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label lblSaldoDesc = new Label("Saldo disponible para usar");

        lblSaldoDesc.setStyle("-fx-font-size: 13px; -fx-text-fill: rgba(255,255,255,0.9); "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Button btnRecargar = new Button("Recargar Saldo");

        btnRecargar.setPrefWidth(Double.MAX_VALUE);

        btnRecargar.setStyle("-fx-background-color: white; -fx-text-fill: #2E7D32; "

                + "-fx-font-size: 14px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btnRecargar.setOnMouseEntered(e -> btnRecargar.setStyle("-fx-background-color: #F1F8E9; -fx-text-fill: #2E7D32; "

                + "-fx-font-size: 14px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnRecargar.setOnMouseExited(e -> btnRecargar.setStyle("-fx-background-color: white; -fx-text-fill: #2E7D32; "

                + "-fx-font-size: 14px; -fx-font-weight: 600; -fx-padding: 12 30; -fx-background-radius: 10; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btnRecargar.setOnAction(e -> {

            mostrarDialogoTarjetaCredito();

        });

        

        cardBilletera.getChildren().addAll(iconoBilletera, tituloBilletera, lblSaldoPerfil, lblSaldoDesc, btnRecargar);



        // Tarjeta de puntos

        VBox cardPuntos = new VBox(20);

        cardPuntos.setPrefWidth(400);

        cardPuntos.setStyle("-fx-background-color: #FB8C00; "

                + "-fx-padding: 30; -fx-background-radius: 16; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0, 0, 3);");

        HBox.setHgrow(cardPuntos, Priority.ALWAYS);

        

        Label iconoPuntos = new Label("");

        iconoPuntos.setStyle("-fx-font-size: 40px;");

        

        Label tituloPuntos = new Label("Sistema de Puntos");

        tituloPuntos.setStyle("-fx-font-size: 18px; -fx-font-weight: 600; -fx-text-fill: white; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        lblPuntosPerfil = new Label(String.valueOf(visitante.getPuntosTicket()));

        lblPuntosPerfil.setStyle("-fx-font-size: 42px; -fx-font-weight: 700; -fx-text-fill: white; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label lblPuntosDesc = new Label("Puntos acumulados");

        lblPuntosDesc.setStyle("-fx-font-size: 13px; -fx-text-fill: rgba(255,255,255,0.9); "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label lblPuntosInfo = new Label("Usa tus puntos para acceder a las atracciones del parque");

        lblPuntosInfo.setStyle("-fx-font-size: 12px; -fx-text-fill: rgba(255,255,255,0.85); "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        lblPuntosInfo.setWrapText(true);

        

        cardPuntos.getChildren().addAll(iconoPuntos, tituloPuntos, lblPuntosPerfil, lblPuntosDesc, lblPuntosInfo);

        

        gridCards.getChildren().addAll(cardBilletera, cardPuntos);



        root.getChildren().addAll(cardPrincipal, gridCards);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #FAFAFA;");

        tab.setContent(sp);

        return tab;

    }



    // TAB TIENDA 



    private Tab tabTienda() {

        Tab tab = new Tab("Tienda de Tickets");



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #F5F7FA;");



        // Header section con título y descripción

        VBox headerSection = new VBox(10);

        headerSection.setAlignment(Pos.CENTER_LEFT);

        

        Label tituloPrincipal = new Label("Tienda de Tickets");

        tituloPrincipal.setStyle("-fx-font-size: 32px; -fx-font-weight: 800; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label descPrincipal = new Label("Elige el ticket perfecto para tu experiencia en Tech-Park UQ");

        descPrincipal.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        headerSection.getChildren().addAll(tituloPrincipal, descPrincipal);



        // Tarjeta de estado de cuenta mejorada

        HBox estadoContainer = new HBox(20);

        estadoContainer.setAlignment(Pos.CENTER);

        

        // Card de saldo con gradiente

        VBox cardSaldo = new VBox(20);

        cardSaldo.setPrefWidth(350);

        cardSaldo.setAlignment(Pos.CENTER);

        cardSaldo.setStyle("-fx-background-color: #43A047; "

                + "-fx-padding: 30; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(67,160,71,0.4), 20, 0, 0, 5);");

        

        Label lblSaldoTit = new Label("Saldo Disponible");

        lblSaldoTit.setStyle("-fx-font-size: 14px; -fx-font-weight: 600; -fx-text-fill: rgba(255,255,255,0.9); "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        lblSaldoTienda = new Label("$" + fmt(visitante.getSaldoVirtual()));

        lblSaldoTienda.setStyle("-fx-font-size: 36px; -fx-font-weight: 800; -fx-text-fill: white; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        cardSaldo.getChildren().addAll(lblSaldoTit, lblSaldoTienda);

        

        // Card de puntos con gradiente

        VBox cardPuntos = new VBox(20);

        cardPuntos.setPrefWidth(350);

        cardPuntos.setAlignment(Pos.CENTER);

        cardPuntos.setStyle("-fx-background-color: #FB8C00; "

                + "-fx-padding: 30; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(251,140,0,0.4), 20, 0, 0, 5);");

        

        Label lblPuntosTit = new Label("Puntos Acumulados");

        lblPuntosTit.setStyle("-fx-font-size: 14px; -fx-font-weight: 600; -fx-text-fill: rgba(255,255,255,0.9); "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        lblPuntosTienda = new Label(String.valueOf(visitante.getPuntosTicket()) + " pts");

        lblPuntosTienda.setStyle("-fx-font-size: 36px; -fx-font-weight: 800; -fx-text-fill: white; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        cardPuntos.getChildren().addAll(lblPuntosTit, lblPuntosTienda);

        

        estadoContainer.getChildren().addAll(cardSaldo, cardPuntos);



        // Título de tickets

        Label tituloTickets = new Label("Elige tu Ticket");

        tituloTickets.setStyle("-fx-font-size: 24px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        // Cards de tickets mejoradas

        HBox cards = new HBox(25);

        cards.setAlignment(Pos.CENTER);

        cards.setStyle("-fx-padding: 10 0;");



        cards.getChildren().addAll(

                cardTicketModerno("Ticket General", "$50,000", "#1565C0",

                        "Acceso básico al parque\nSin beneficios adicionales\nCompra puntos en la tienda",

                        e -> { controller.comprarTicketGeneral(); refrescarTodo(); }),

                cardTicketModerno("Ticket Familiar", "$150,000", "#6A1B9A",

                        "Hasta 4 personas\n25% descuento en puntos\nCompra puntos con descuento",

                        e -> { controller.comprarTicketFamiliar(); refrescarTodo(); }),

                cardTicketModerno("Fast-Pass", "$80,000", "#BF360C",

                        "Prioridad en todas las colas\nSin costos adicionales\nCompra puntos en la tienda",

                        e -> { controller.comprarTicketFastPass(); refrescarTodo(); })

        );



        root.getChildren().addAll(headerSection, estadoContainer, tituloTickets, cards);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #F5F7FA;");

        tab.setContent(sp);

        return tab;

    }



    private VBox cardTicketModerno(String nombre, String precio, String color,

                                    String desc, javafx.event.EventHandler<javafx.event.ActionEvent> h) {

        VBox card = new VBox(20);

        card.setPrefWidth(320);

        card.setAlignment(Pos.TOP_CENTER);

        card.setStyle("-fx-background-color: white; -fx-padding: 30; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 25, 0, 0, 8); "

                + "-fx-border-color: " + color + "; -fx-border-width: 0; -fx-border-radius: 20;");



        // Nombre del ticket

        Label lNombre = new Label(nombre);

        lNombre.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: " + color + "; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        // Precio destacado

        VBox precioBox = new VBox(5);

        precioBox.setAlignment(Pos.CENTER);

        precioBox.setStyle("-fx-background-color: " + color + "22; -fx-padding: 10 20; -fx-background-radius: 12;");

        

        Label lPrecio = new Label(precio);

        lPrecio.setStyle("-fx-font-size: 32px; -fx-font-weight: 800; -fx-text-fill: " + color + "; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        precioBox.getChildren().add(lPrecio);



        // Descripción

        Label lDesc = new Label(desc);

        lDesc.setWrapText(true);

        lDesc.setAlignment(Pos.CENTER);

        lDesc.setStyle("-fx-font-size: 13px; -fx-text-fill: #666; -fx-font-family: 'Segoe UI', 'Arial', sans-serif; "

                + "-fx-line-spacing: 2;");



        // Botón moderno

        Button btn = new Button("COMPRAR AHORA");

        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 700; -fx-padding: 14 40; -fx-background-radius: 12; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btn.setMaxWidth(Double.MAX_VALUE);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: derive(" + color + ", -20%); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 700; -fx-padding: 14 40; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-scale-y: 1.05; -fx-scale-x: 1.05;"));

        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 700; -fx-padding: 14 40; -fx-background-radius: 12; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btn.setOnAction(h);



        card.getChildren().addAll(lNombre, precioBox, new Separator(), lDesc, btn);

        return card;

    }



    private VBox cardTicket(String nombre, String precio, String color,

                            String desc, javafx.event.EventHandler<javafx.event.ActionEvent> h) {

        VBox card = new VBox(15);

        card.setPrefWidth(280);

        card.setAlignment(Pos.TOP_CENTER);

        card.setStyle("-fx-background-color: white; -fx-border-color: " + color

                + "; -fx-border-width: 3; -fx-padding: 25; -fx-background-radius: 12; -fx-border-radius: 12; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");



        Label lNombre = new Label(nombre);

        lNombre.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: " + color + ";");



        Label lPrecio = new Label(precio);

        lPrecio.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #212121;");



        Label lDesc = new Label(desc);

        lDesc.setWrapText(true);

        lDesc.setAlignment(Pos.CENTER);

        lDesc.setStyle("-fx-font-size: 13px; -fx-text-fill: #666;");



        Button btn = new Button("Comprar");

        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 40; -fx-background-radius: 8;");

        btn.setMaxWidth(Double.MAX_VALUE);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: derive(" + color + ", -20%); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 40; -fx-background-radius: 8; -fx-cursor: hand;"));

        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 40; -fx-background-radius: 8;"));

        btn.setOnAction(h);



        card.getChildren().addAll(lNombre, lPrecio, new Separator(), lDesc, btn);

        return card;

    }



    // TAB TIENDA PUNTOS 



    private Tab tabTiendaPuntos() {

        Tab tab = new Tab("Tienda de Puntos");



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #FFF8E1;");



        // Header section con título y descripción

        VBox headerSection = new VBox(10);

        headerSection.setAlignment(Pos.CENTER_LEFT);

        

        Label tituloPrincipal = new Label("Tienda de Puntos");

        tituloPrincipal.setStyle("-fx-font-size: 32px; -fx-font-weight: 800; -fx-text-fill: #E65100; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label descPrincipal = new Label("Compra puntos para disfrutar de todas las atracciones del parque");

        descPrincipal.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575; -fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        headerSection.getChildren().addAll(tituloPrincipal, descPrincipal);



        // Tarjeta de información del ticket mejorada

        VBox cardTicket = new VBox(20);

        cardTicket.setStyle("-fx-background-color: white; -fx-padding: 30; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 25, 0, 0, 8);");

        

        Label tituloTicket = new Label("Tu Ticket Actual");

        tituloTicket.setStyle("-fx-font-size: 20px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        HBox ticketInfo = new HBox(25);

        ticketInfo.setAlignment(Pos.CENTER_LEFT);

        ticketInfo.setStyle("-fx-padding: 15 0 0 0;");

        

        VBox ticketBox = new VBox(8);

        ticketBox.setStyle("-fx-background-color: #E3F2FD; "

                + "-fx-padding: 20 30; -fx-background-radius: 16;");

        

        Label lblTicketTit = new Label("Tipo de Ticket");

        lblTicketTit.setStyle("-fx-font-size: 12px; -fx-font-weight: 600; -fx-text-fill: #1976D2; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        lblTicketActual = new Label(visitante.getTipoTicket().getNombre());

        lblTicketActual.setStyle("-fx-font-size: 24px; -fx-font-weight: 800; -fx-text-fill: #0D47A1; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        ticketBox.getChildren().addAll(lblTicketTit, lblTicketActual);

        

        lblBeneficioTicket = new Label();

        actualizarBeneficioTicket();

        

        ticketInfo.getChildren().addAll(ticketBox, lblBeneficioTicket);

        

        cardTicket.getChildren().addAll(tituloTicket, ticketInfo);



        // Título de paquetes

        Label tituloPaquetes = new Label("Paquetes de Puntos Disponibles");

        tituloPaquetes.setStyle("-fx-font-size: 24px; -fx-font-weight: 700; -fx-text-fill: #E65100; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        // Cards de paquetes de puntos mejoradas

        HBox paquetesBox = new HBox(25);

        paquetesBox.setAlignment(Pos.CENTER);

        paquetesBox.setStyle("-fx-padding: 10 0;");



        cardPaqueteBasico = cardPaquetePuntosModerno("Paquete Básico", 50, "#1565C0");

        cardPaqueteEstandar = cardPaquetePuntosModerno("Paquete Estándar", 100, "#6A1B9A");

        cardPaquetePremium = cardPaquetePuntosModerno("Paquete Premium", 200, "#BF360C");



        paquetesBox.getChildren().addAll(cardPaqueteBasico, cardPaqueteEstandar, cardPaquetePremium);



        root.getChildren().addAll(headerSection, cardTicket, tituloPaquetes, paquetesBox);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #FFF8E1;");

        tab.setContent(sp);

        return tab;

    }



    private VBox cardPaquetePuntosModerno(String nombre, int puntos, String color) {

        VBox card = new VBox(20);

        card.setPrefWidth(320);

        card.setAlignment(Pos.TOP_CENTER);

        card.setStyle("-fx-background-color: white; -fx-padding: 30; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 25, 0, 0, 8);");



        // Nombre del paquete

        Label lNombre = new Label(nombre);

        lNombre.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: " + color + "; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        // Puntos destacados

        VBox puntosBox = new VBox(5);

        puntosBox.setAlignment(Pos.CENTER);

        puntosBox.setStyle("-fx-background-color: " + color + "22; -fx-padding: 10 20; -fx-background-radius: 12;");

        

        Label lPuntos = new Label(puntos + " PUNTOS");

        lPuntos.setStyle("-fx-font-size: 32px; -fx-font-weight: 800; -fx-text-fill: " + color + "; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        puntosBox.getChildren().add(lPuntos);



        float precioBase = puntos * 100;

        float precioFinal = visitante.calcularPrecioPuntos(puntos);

        int descuento = visitante.getDescuentoPuntos();



        VBox precioBox = new VBox(8);

        precioBox.setAlignment(Pos.CENTER);

        precioBox.setStyle("-fx-padding: 10 0;");



        if (descuento > 0) {

            Label lPrecioOriginal = new Label("$" + String.format("%.0f", precioBase));

            lPrecioOriginal.setStyle("-fx-font-size: 14px; -fx-text-fill: #999; -fx-strikethrough: true; "

                    + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

            

            Label lPrecioFinal = new Label("$" + String.format("%.0f", precioFinal));

            lPrecioFinal.setStyle("-fx-font-size: 32px; -fx-font-weight: 800; -fx-text-fill: #4CAF50; "

                    + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

            

            Label lDescuento = new Label(descuento + "% DE DESCUENTO");

            lDescuento.setStyle("-fx-font-size: 12px; -fx-font-weight: 700; -fx-text-fill: #4CAF50; "

                    + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

            

            precioBox.getChildren().addAll(lPrecioOriginal, lPrecioFinal, lDescuento);

        } else {

            Label lPrecio = new Label("$" + String.format("%.0f", precioFinal));

            lPrecio.setStyle("-fx-font-size: 32px; -fx-font-weight: 800; -fx-text-fill: #212121; "

                    + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

            precioBox.getChildren().add(lPrecio);

        }



        // Botón moderno

        Button btn = new Button("COMPRAR AHORA");

        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 700; -fx-padding: 14 40; -fx-background-radius: 12; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        btn.setMaxWidth(Double.MAX_VALUE);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: derive(" + color + ", -20%); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 700; -fx-padding: 14 40; -fx-background-radius: 12; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-scale-y: 1.05; -fx-scale-x: 1.05;"));

        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 700; -fx-padding: 14 40; -fx-background-radius: 12; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;"));

        btn.setOnAction(e -> {

            controller.comprarPuntos(puntos);

            refrescarTodo();

        });



        card.getChildren().addAll(lNombre, puntosBox, new Separator(), precioBox, btn);

        return card;

    }



    private VBox cardPaquetePuntos(String nombre, int puntos, String color) {

        VBox card = new VBox(15);

        card.setPrefWidth(280);

        card.setAlignment(Pos.TOP_CENTER);

        card.setStyle("-fx-background-color: white; -fx-border-color: " + color

                + "; -fx-border-width: 3; -fx-padding: 25; -fx-background-radius: 12; -fx-border-radius: 12; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");



        Label lNombre = new Label(nombre);

        lNombre.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: " + color + ";");



        Label lPuntos = new Label(puntos + " PUNTOS");

        lPuntos.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #212121;");



        float precioBase = puntos * 100;

        float precioFinal = visitante.calcularPrecioPuntos(puntos);

        int descuento = visitante.getDescuentoPuntos();



        VBox precioBox = new VBox(5);

        precioBox.setAlignment(Pos.CENTER);



        if (descuento > 0) {

            Label lPrecioOriginal = new Label("$" + String.format("%.0f", precioBase));

            lPrecioOriginal.setStyle("-fx-font-size: 14px; -fx-text-fill: #999; -fx-strikethrough: true;");

            

            Label lPrecioFinal = new Label("$" + String.format("%.0f", precioFinal));

            lPrecioFinal.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");

            

            Label lDescuento = new Label("-" + descuento + "% DESCUENTO");

            lDescuento.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");

            

            precioBox.getChildren().addAll(lPrecioOriginal, lPrecioFinal, lDescuento);

        } else {

            Label lPrecio = new Label("$" + String.format("%.0f", precioFinal));

            lPrecio.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #212121;");

            precioBox.getChildren().add(lPrecio);

        }



        Button btn = new Button("COMPRAR");

        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 40; -fx-background-radius: 8;");

        btn.setMaxWidth(Double.MAX_VALUE);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: derive(" + color + ", -20%); -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 40; -fx-background-radius: 8; -fx-cursor: hand;"));

        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 40; -fx-background-radius: 8;"));

        btn.setOnAction(e -> {

            controller.comprarPuntos(puntos);

            refrescarTodo();

        });



        card.getChildren().addAll(lNombre, lPuntos, new Separator(), precioBox, btn);

        return card;

    }



    // TAB ATRACCIONES 



    private Tab tabAtracciones() {

        Tab tab = new Tab("Atracciones");



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #F5F7FA;");



        // Header section: título + subtítulo 

        VBox headerSection = new VBox(8);

        headerSection.setAlignment(Pos.CENTER_LEFT);



        Label tituloPrincipal = new Label("Atracciones del Parque");

        tituloPrincipal.setStyle("-fx-font-size: 32px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Label subtituloPrincipal = new Label("Consulta tu estado y solicita acceso a las atracciones disponibles");

        subtituloPrincipal.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        headerSection.getChildren().addAll(tituloPrincipal, subtituloPrincipal);



        // Tarjetas de estado (Ticket / Saldo / Puntos) 

        HBox cardsEstado = new HBox(20);

        cardsEstado.setAlignment(Pos.CENTER);



        VBox cardTicket = crearStatCard("", "Ticket Activo",

                visitante.getTipoTicket().getNombre(),

                "Tu plan vigente en el parque",

                "#4527A0", "#5E35B1");

        lblTicketAtracciones = (Label) ((VBox) cardTicket).getChildren().get(2);



        VBox cardSaldo = crearStatCard("", "Saldo Disponible",

                "$" + fmt(visitante.getSaldoVirtual()),

                "Saldo en tu billetera virtual",

                "#43A047", "#2E7D32");

        lblSaldoAtracciones = (Label) ((VBox) cardSaldo).getChildren().get(2);



        VBox cardPuntos = crearStatCard("", "Puntos Acumulados",

                visitante.getPuntosTicket() + " pts",

                "Úsalos para acceder a atracciones",

                "#FB8C00", "#EF6C00");

        lblPuntosAtracciones = (Label) ((VBox) cardPuntos).getChildren().get(2);



        HBox.setHgrow(cardTicket, Priority.ALWAYS);

        HBox.setHgrow(cardSaldo, Priority.ALWAYS);

        HBox.setHgrow(cardPuntos, Priority.ALWAYS);

        cardsEstado.getChildren().addAll(cardTicket, cardSaldo, cardPuntos);



        // Tarjeta de tabla de atracciones 

        VBox cardAtracciones = new VBox(20);

        cardAtracciones.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(69,39,160,0.08), 25, 0, 0, 8);");



        HBox tituloRow = new HBox(15);

        tituloRow.setAlignment(Pos.CENTER_LEFT);



        Label tituloAtracciones = new Label("Atracciones Disponibles");

        tituloAtracciones.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Label countChip = new Label(controller.getAtraccionesDisponibles().size() + " atracciones");

        countChip.setStyle("-fx-background-color: #EDE7F6; -fx-text-fill: #4527A0; "

                + "-fx-font-size: 12px; -fx-font-weight: 700; -fx-padding: 6 14; -fx-background-radius: 20; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Region tituloSpacer = new Region();

        HBox.setHgrow(tituloSpacer, Priority.ALWAYS);



        tituloRow.getChildren().addAll(tituloAtracciones, countChip, tituloSpacer);



        Separator sepTitulo = new Separator();

        sepTitulo.setStyle("-fx-border-color: #E2E8F0;");



        TableView<Atraccion> tabla = new TableView<>(controller.getAtraccionesDisponibles());

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        tabla.setPrefHeight(420);

        tabla.setFixedCellSize(48);

        tabla.setStyle("-fx-background-color: #F8FAFC; -fx-border-color: #E2E8F0; -fx-border-width: 1; "

                + "-fx-border-radius: 12; -fx-background-radius: 12; -fx-font-size: 14px;");



        TableColumn<Atraccion, String> cNombre = col("Atracción", "nombre", 0);

        TableColumn<Atraccion, String> cTipo = col("Tipo", "tipoAtraccion", 0);



        TableColumn<Atraccion, Object> cEstado = new TableColumn<>("Estado");

        cEstado.setCellValueFactory(new PropertyValueFactory<>("estadoActual"));

        cEstado.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Object item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    String estado = item.toString();

                    setText(estado.replace("_", " "));

                    switch (estado) {

                        case "ACTIVA" -> setStyle("-fx-background-color: #C8E6C9; -fx-text-fill: #1B5E20; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; "

                                + "-fx-alignment: CENTER;");

                        case "EN_MANTENIMIENTO" -> setStyle("-fx-background-color: #FFE0B2; -fx-text-fill: #E65100; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; "

                                + "-fx-alignment: CENTER;");

                        case "CERRADO" -> setStyle("-fx-background-color: #FFCDD2; -fx-text-fill: #B71C1C; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; "

                                + "-fx-alignment: CENTER;");

                        default -> setStyle("");

                    }

                }

            }

        });



        TableColumn<Atraccion, Integer> cPuntos = new TableColumn<>("Costo Pts");

        cPuntos.setCellValueFactory(new PropertyValueFactory<>("costoEnPuntos"));

        cPuntos.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Integer item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    setText("" + item);

                    setStyle("-fx-text-fill: #FB8C00; -fx-font-weight: 700; -fx-alignment: CENTER;");

                }

            }

        });



        TableColumn<Atraccion, Integer> cTiempo = new TableColumn<>("Tiempo Espera");

        cTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempoEspera"));

        cTiempo.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Integer item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    // Si tiene Fast Pass, mostrar 0 min (prioridad inmediata)
                    int tiempoMostrado = controller.getVisitante().tieneFastPass() ? 0 : item;
                    setText(tiempoMostrado + " min");

                    if (tiempoMostrado == 0) {

                        setStyle("-fx-text-fill: #2E7D32; -fx-font-weight: 700; -fx-alignment: CENTER;");

                    } else if (tiempoMostrado <= 10) {

                        setStyle("-fx-text-fill: #EF6C00; -fx-font-weight: 700; -fx-alignment: CENTER;");

                    } else {

                        setStyle("-fx-text-fill: #C62828; -fx-font-weight: 700; -fx-alignment: CENTER;");

                    }

                }

            }

        });



        // Columna de acciones

        TableColumn<Atraccion, Void> cAccion = new TableColumn<>("Acciones");

        cAccion.setCellFactory(p -> new TableCell<>() {

            private final Button btnAcceso = new Button("Solicitar Acceso");

            private final Button btnFav = new Button("Favorito");

            private final HBox box = new HBox(8, btnAcceso, btnFav);



            {

                box.setAlignment(Pos.CENTER);

                String accesoBase = "-fx-background-color: #4527A0; -fx-text-fill: white; -fx-font-size: 12px; "

                        + "-fx-font-weight: 600; -fx-padding: 8 16; -fx-background-radius: 8; "

                        + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;";

                String accesoHover = "-fx-background-color: #5E35B1; -fx-text-fill: white; -fx-font-size: 12px; "

                        + "-fx-font-weight: 600; -fx-padding: 8 16; -fx-background-radius: 8; -fx-cursor: hand; "

                        + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;";

                btnAcceso.setStyle(accesoBase);

                btnAcceso.setOnMouseEntered(e -> btnAcceso.setStyle(accesoHover));

                btnAcceso.setOnMouseExited(e -> btnAcceso.setStyle(accesoBase));



                String favBase = "-fx-background-color: #FB8C00; -fx-text-fill: white; -fx-font-size: 12px; "

                        + "-fx-font-weight: 600; -fx-padding: 8 16; -fx-background-radius: 8; "

                        + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;";

                String favHover = "-fx-background-color: #EF6C00; -fx-text-fill: white; -fx-font-size: 12px; "

                        + "-fx-font-weight: 600; -fx-padding: 8 16; -fx-background-radius: 8; -fx-cursor: hand; "

                        + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;";

                btnFav.setStyle(favBase);

                btnFav.setOnMouseEntered(e -> btnFav.setStyle(favHover));

                btnFav.setOnMouseExited(e -> btnFav.setStyle(favBase));



                btnAcceso.setOnAction(e -> {

                    Atraccion a = getTableView().getItems().get(getIndex());

                    controller.solicitarAccesoAtraccion(a);

                    refrescarTodo();

                });



                btnFav.setOnAction(e -> {

                    Atraccion a = getTableView().getItems().get(getIndex());

                    controller.agregarAtraccionFavorita(a);

                });

            }



            @Override

            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                setGraphic(empty ? null : box);

            }

        });



        // Columnas de requisitos (edad mínima y estatura mínima)

        TableColumn<Atraccion, Integer> cEdadMin = new TableColumn<>("Edad Mín.");

        cEdadMin.setCellValueFactory(new PropertyValueFactory<>("edadMinimaRequerida"));

        cEdadMin.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Integer item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    setText(item + " años");

                    boolean ok = visitante.getEdad() >= item;

                    setStyle("-fx-alignment: CENTER; -fx-font-weight: 600; "

                            + "-fx-text-fill: " + (ok ? "#2E7D32" : "#C62828") + ";");

                }

            }

        });



        TableColumn<Atraccion, Float> cAlturaMin = new TableColumn<>("Estatura Mín.");

        cAlturaMin.setCellValueFactory(new PropertyValueFactory<>("alturaMinimaRequerida"));

        cAlturaMin.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Float item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    setText(String.format("%.2f m", item));

                    boolean ok = visitante.getEstatura() >= item;

                    setStyle("-fx-alignment: CENTER; -fx-font-weight: 600; "

                            + "-fx-text-fill: " + (ok ? "#2E7D32" : "#C62828") + ";");

                }

            }

        });



        tabla.getColumns().addAll(List.of(cNombre, cTipo, cEstado, cEdadMin, cAlturaMin, cPuntos, cTiempo, cAccion));



        // Leyenda informativa

        HBox leyenda = new HBox(20);

        leyenda.setAlignment(Pos.CENTER_LEFT);

        leyenda.setStyle("-fx-padding: 5 0 0 0;");

        leyenda.getChildren().addAll(

                crearLeyendaItem("", "Activa", "#2E7D32"),

                crearLeyendaItem("", "En mantenimiento", "#EF6C00"),

                crearLeyendaItem("", "Cerrado", "#C62828")

        );



        Label info = new Label("Haz clic en 'Solicitar Acceso' para intentar ingresar a una atracción usando tus puntos.");

        info.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B; -fx-font-style: italic; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        info.setWrapText(true);



        cardAtracciones.getChildren().addAll(tituloRow, sepTitulo, tabla, leyenda, info);



        root.getChildren().addAll(headerSection, cardsEstado, cardAtracciones);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(sp);

        return tab;

    }



    private VBox crearStatCard(String icono, String titulo, String valor, String descripcion,

                                String colorPrincipal, String colorSecundario) {

        VBox card = new VBox(10);

        card.setStyle("-fx-background-color: linear-gradient(to bottom right, " + colorPrincipal + ", " + colorSecundario + "); "

                + "-fx-padding: 28; -fx-background-radius: 16; "

                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 18, 0, 0, 6);");



        Label lblIcono = new Label(icono);

        lblIcono.setStyle("-fx-font-size: 36px;");



        Label lblTitulo = new Label(titulo);

        lblTitulo.setStyle("-fx-font-size: 13px; -fx-font-weight: 600; -fx-text-fill: rgba(255,255,255,0.9); "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Label lblValor = new Label(valor);

        lblValor.setStyle("-fx-font-size: 28px; -fx-font-weight: 700; -fx-text-fill: white; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Label lblDesc = new Label(descripcion);

        lblDesc.setStyle("-fx-font-size: 11px; -fx-text-fill: rgba(255,255,255,0.85); "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        lblDesc.setWrapText(true);



        // IMPORTANTE: el orden debe ser icono(0), titulo(1), valor(2), desc(3)

        // para que tabAtracciones pueda referenciar el label de valor por índice 2

        card.getChildren().addAll(lblIcono, lblTitulo, lblValor, lblDesc);

        return card;

    }



    private HBox crearLeyendaItem(String icono, String texto, String color) {

        HBox item = new HBox(6);

        item.setAlignment(Pos.CENTER_LEFT);

        Label lblIcono = new Label(icono);

        lblIcono.setStyle("-fx-font-size: 12px;");

        Label lblTexto = new Label(texto);

        lblTexto.setStyle("-fx-font-size: 12px; -fx-font-weight: 600; -fx-text-fill: " + color + "; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        item.getChildren().addAll(lblIcono, lblTexto);

        return item;

    }



    // TAB MAPA PARQUE 



    private Tab tabMapaParque() {

        Tab tab = new Tab("Mapa del Parque");



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #F5F7FA;");



        // Header con título y subtítulo

        VBox headerSection = new VBox(8);

        headerSection.setAlignment(Pos.CENTER_LEFT);



        Label tituloPrincipal = new Label("Mapa del Parque");

        tituloPrincipal.setStyle("-fx-font-size: 32px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Label subtituloPrincipal = new Label("Ubica las atracciones, zonas y puntos de interés del parque");

        subtituloPrincipal.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        headerSection.getChildren().addAll(tituloPrincipal, subtituloPrincipal);



        // Tarjeta con imagen del mapa

        VBox cardMapa = new VBox(20);

        cardMapa.setAlignment(Pos.CENTER);

        cardMapa.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(69,39,160,0.08), 25, 0, 0, 8);");



        HBox tituloRow = new HBox(15);

        tituloRow.setAlignment(Pos.CENTER_LEFT);



        Label tituloMapa = new Label("Vista General");

        tituloMapa.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Label hintChip = new Label("Zoom recomendado: 100%");

        hintChip.setStyle("-fx-background-color: #EDE7F6; -fx-text-fill: #4527A0; "

                + "-fx-font-size: 12px; -fx-font-weight: 700; -fx-padding: 6 14; -fx-background-radius: 20; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



        Region tituloSpacer = new Region();

        HBox.setHgrow(tituloSpacer, Priority.ALWAYS);



        tituloRow.getChildren().addAll(tituloMapa, tituloSpacer, hintChip);



        Separator sepTitulo = new Separator();

        sepTitulo.setStyle("-fx-border-color: #E2E8F0;");



        StackPane contenedorMapa = new StackPane();

        contenedorMapa.setStyle("-fx-background-color: #F8FAFC; -fx-background-radius: 12; "

                + "-fx-border-color: #E2E8F0; -fx-border-width: 1; -fx-border-radius: 12;");

        contenedorMapa.setPadding(new Insets(15));



        try {

            javafx.scene.image.Image mapaImagen = new javafx.scene.image.Image(

                getClass().getResourceAsStream("/images/mapa-parque.png"));

            javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(mapaImagen);

            imageView.setPreserveRatio(true);

            imageView.setFitWidth(1408);

            imageView.setFitHeight(768);

            javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(1408, 768);

            clip.setArcWidth(16);

            clip.setArcHeight(16);

            imageView.setClip(clip);

            contenedorMapa.getChildren().add(imageView);

        } catch (Exception e) {

            VBox boxError = new VBox(12);

            boxError.setAlignment(Pos.CENTER);

            boxError.setPadding(new Insets(60));

            Label icono = new Label("");

            icono.setStyle("-fx-font-size: 64px; -fx-opacity: 0.4;");

            Label titErr = new Label("No se pudo cargar el mapa del parque");

            titErr.setStyle("-fx-font-size: 16px; -fx-font-weight: 700; -fx-text-fill: #475569; "

                    + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

            Label descErr = new Label("Coloca una imagen llamada 'mapa-parque.png' en\nsrc/main/resources/images/  (1408x768 px recomendado)");

            descErr.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B; -fx-text-alignment: center; "

                    + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

            descErr.setWrapText(true);

            descErr.setAlignment(Pos.CENTER);

            boxError.getChildren().addAll(icono, titErr, descErr);

            contenedorMapa.getChildren().add(boxError);

        }



        Label infoMapa = new Label("Consejo: planea tu recorrido revisando primero las atracciones favoritas y sus zonas.");

        infoMapa.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B; -fx-font-style: italic; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        infoMapa.setWrapText(true);



        cardMapa.getChildren().addAll(tituloRow, sepTitulo, contenedorMapa, infoMapa);



        root.getChildren().addAll(headerSection, cardMapa);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(sp);

        return tab;

    }



    // TAB FAVORITOS 



    private Tab tabFavoritos() {

        Tab tab = new Tab("Mis Favoritos");



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #F5F7FA;");



        // Header

        VBox headerSection = new VBox(8);

        headerSection.setAlignment(Pos.CENTER_LEFT);

        Label tituloPrincipal = new Label("Mis Atracciones Favoritas");

        tituloPrincipal.setStyle("-fx-font-size: 32px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label subtituloPrincipal = new Label("Recibe alertas de las atracciones que más te interesan");

        subtituloPrincipal.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        headerSection.getChildren().addAll(tituloPrincipal, subtituloPrincipal);



        VBox cardFavoritos = new VBox(20);

        cardFavoritos.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(69,39,160,0.08), 25, 0, 0, 8);");



        HBox tituloRow = new HBox(15);

        tituloRow.setAlignment(Pos.CENTER_LEFT);

        Label tituloCard = new Label("Tus Favoritos");

        tituloCard.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label countChip = new Label(controller.getAtraccionesFavoritas().size() + " guardadas");

        countChip.setStyle("-fx-background-color: #FFE0B2; -fx-text-fill: #E65100; "

                + "-fx-font-size: 12px; -fx-font-weight: 700; -fx-padding: 6 14; -fx-background-radius: 20; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Region tituloSpacer = new Region();

        HBox.setHgrow(tituloSpacer, Priority.ALWAYS);

        tituloRow.getChildren().addAll(tituloCard, countChip, tituloSpacer);



        Separator sepTitulo = new Separator();

        sepTitulo.setStyle("-fx-border-color: #E2E8F0;");



        TableView<Atraccion> tabla = new TableView<>(controller.getAtraccionesFavoritas());

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        tabla.setPrefHeight(450);

        tabla.setFixedCellSize(48);

        tabla.setStyle("-fx-background-color: #F8FAFC; -fx-border-color: #E2E8F0; -fx-border-width: 1; "

                + "-fx-border-radius: 12; -fx-background-radius: 12; -fx-font-size: 14px;");



        TableColumn<Atraccion, String> cNombre = col("Atracción", "nombre", 0);

        TableColumn<Atraccion, String> cTipo = col("Tipo", "tipoAtraccion", 0);



        TableColumn<Atraccion, Object> cEstado = new TableColumn<>("Estado");

        cEstado.setCellValueFactory(new PropertyValueFactory<>("estadoActual"));

        cEstado.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Object item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    String estado = item.toString();

                    setText(estado.replace("_", " "));

                    switch (estado) {

                        case "ACTIVA" -> setStyle("-fx-background-color: #C8E6C9; -fx-text-fill: #1B5E20; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; -fx-alignment: CENTER;");

                        case "EN_MANTENIMIENTO" -> setStyle("-fx-background-color: #FFE0B2; -fx-text-fill: #E65100; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; -fx-alignment: CENTER;");

                        case "CERRADO" -> setStyle("-fx-background-color: #FFCDD2; -fx-text-fill: #B71C1C; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; -fx-alignment: CENTER;");

                        default -> setStyle("");

                    }

                }

            }

        });



        TableColumn<Atraccion, Integer> cTiempo = new TableColumn<>("Tiempo Espera");

        cTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempoEspera"));

        cTiempo.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Integer item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    // Si tiene Fast Pass, mostrar 0 min (prioridad inmediata)
                    int tiempoMostrado = controller.getVisitante().tieneFastPass() ? 0 : item;
                    setText(tiempoMostrado + " min");

                    if (tiempoMostrado == 0) {

                        setStyle("-fx-text-fill: #2E7D32; -fx-font-weight: 700; -fx-alignment: CENTER;");

                    } else if (tiempoMostrado <= 10) {

                        setStyle("-fx-text-fill: #EF6C00; -fx-font-weight: 700; -fx-alignment: CENTER;");

                    } else {

                        setStyle("-fx-text-fill: #C62828; -fx-font-weight: 700; -fx-alignment: CENTER;");

                    }

                }

            }

        });



        TableColumn<Atraccion, Integer> cPuntos = new TableColumn<>("Costo Pts");

        cPuntos.setCellValueFactory(new PropertyValueFactory<>("costoEnPuntos"));

        cPuntos.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Integer item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    setText("" + item);

                    setStyle("-fx-text-fill: #FB8C00; -fx-font-weight: 700; -fx-alignment: CENTER;");

                }

            }

        });



        TableColumn<Atraccion, Void> cEliminar = new TableColumn<>("Acción");

        cEliminar.setCellFactory(p -> new TableCell<>() {

            private final Button btn = new Button("Eliminar");

            {

                String base = "-fx-background-color: #EF5350; -fx-text-fill: white; -fx-font-size: 12px; "

                        + "-fx-font-weight: 600; -fx-padding: 8 16; -fx-background-radius: 8; "

                        + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;";

                String hover = "-fx-background-color: #C62828; -fx-text-fill: white; -fx-font-size: 12px; "

                        + "-fx-font-weight: 600; -fx-padding: 8 16; -fx-background-radius: 8; -fx-cursor: hand; "

                        + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;";

                btn.setStyle(base);

                btn.setOnMouseEntered(e -> btn.setStyle(hover));

                btn.setOnMouseExited(e -> btn.setStyle(base));

                btn.setOnAction(e -> controller.eliminarAtraccionFavorita(

                        getTableView().getItems().get(getIndex())));

            }

            @Override

            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                setGraphic(empty ? null : btn);

            }

        });



        tabla.getColumns().addAll(List.of(cNombre, cTipo, cEstado, cTiempo, cPuntos, cEliminar));



        // Leyenda de estados

        HBox leyenda = new HBox(20);

        leyenda.setAlignment(Pos.CENTER_LEFT);

        leyenda.getChildren().addAll(

                crearLeyendaItem("", "Activa", "#2E7D32"),

                crearLeyendaItem("", "En mantenimiento", "#EF6C00"),

                crearLeyendaItem("", "Cerrado", "#C62828")

        );



        Label info = new Label("Agrega atracciones desde la pestaña 'Atracciones'. Recibirás notificaciones cuando cambien de estado o tengan tiempos de espera bajos.");

        info.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B; -fx-font-style: italic; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        info.setWrapText(true);



        Button btnActualizar = new Button("Actualizar Tiempos");

        String baseActualizar = "-fx-background-color: #4527A0; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 600; -fx-padding: 12 28; -fx-background-radius: 10; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;";

        String hoverActualizar = "-fx-background-color: #5E35B1; -fx-text-fill: white; "

                + "-fx-font-size: 14px; -fx-font-weight: 600; -fx-padding: 12 28; -fx-background-radius: 10; -fx-cursor: hand; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;";

        btnActualizar.setStyle(baseActualizar);

        btnActualizar.setOnMouseEntered(e -> btnActualizar.setStyle(hoverActualizar));

        btnActualizar.setOnMouseExited(e -> btnActualizar.setStyle(baseActualizar));

        btnActualizar.setOnAction(e -> tabla.refresh());



        HBox accionesRow = new HBox(15);

        accionesRow.setAlignment(Pos.CENTER_LEFT);

        Region accionesSpacer = new Region();

        HBox.setHgrow(accionesSpacer, Priority.ALWAYS);

        accionesRow.getChildren().addAll(leyenda, accionesSpacer, btnActualizar);



        cardFavoritos.getChildren().addAll(tituloRow, sepTitulo, tabla, accionesRow, info);

        root.getChildren().addAll(headerSection, cardFavoritos);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(sp);

        return tab;

    }



    // TAB ESTADISTICAS 



    private Tab tabEstadisticas() {

        Tab tab = new Tab("Mis Estadisticas");



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #F5F7FA;");



        // Header

        VBox headerSection = new VBox(8);

        headerSection.setAlignment(Pos.CENTER_LEFT);

        Label tituloPrincipal = new Label("Mis Estadísticas");

        tituloPrincipal.setStyle("-fx-font-size: 32px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label subtituloPrincipal = new Label("Tu actividad e historial de accesos en el parque");

        subtituloPrincipal.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        headerSection.getChildren().addAll(tituloPrincipal, subtituloPrincipal);



        // Tarjetas KPI (los labels de valor estarán en el índice 2 del VBox; ver crearStatCard)

        HBox cardsKpi = new HBox(20);

        cardsKpi.setAlignment(Pos.CENTER);



        VBox kpiTotal = crearStatCard("", "Total Ingresos", "0",

                "Atracciones a las que has accedido", "#4527A0", "#5E35B1");

        VBox kpiAprobados = crearStatCard("", "Aprobados", "0",

                "Solicitudes exitosas", "#43A047", "#2E7D32");

        VBox kpiProceso = crearStatCard("", "En Proceso", "0",

                "Solicitudes pendientes", "#FB8C00", "#EF6C00");

        VBox kpiDenegados = crearStatCard("", "Denegados", "0",

                "Solicitudes rechazadas", "#E53935", "#C62828");



        Label lblValorTotal = (Label) kpiTotal.getChildren().get(2);

        Label lblValorAprobados = (Label) kpiAprobados.getChildren().get(2);

        Label lblValorProceso = (Label) kpiProceso.getChildren().get(2);

        Label lblValorDenegados = (Label) kpiDenegados.getChildren().get(2);



        HBox.setHgrow(kpiTotal, Priority.ALWAYS);

        HBox.setHgrow(kpiAprobados, Priority.ALWAYS);

        HBox.setHgrow(kpiProceso, Priority.ALWAYS);

        HBox.setHgrow(kpiDenegados, Priority.ALWAYS);

        cardsKpi.getChildren().addAll(kpiTotal, kpiAprobados, kpiProceso, kpiDenegados);



        // Tarjeta de historial

        VBox cardHistorial = new VBox(20);

        cardHistorial.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(69,39,160,0.08), 25, 0, 0, 8);");



        HBox tituloRow = new HBox(15);

        tituloRow.setAlignment(Pos.CENTER_LEFT);

        Label tituloHistorial = new Label("Historial de Accesos");

        tituloHistorial.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label countChip = new Label("0 registros");

        countChip.setStyle("-fx-background-color: #EDE7F6; -fx-text-fill: #4527A0; "

                + "-fx-font-size: 12px; -fx-font-weight: 700; -fx-padding: 6 14; -fx-background-radius: 20; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Region tituloSpacer = new Region();

        HBox.setHgrow(tituloSpacer, Priority.ALWAYS);

        tituloRow.getChildren().addAll(tituloHistorial, countChip, tituloSpacer);



        // Listener: recalcula KPIs y chip cada vez que cambia el historial

        Runnable refrescarKpis = () -> {

            int total = visitante.getTotalIngresos();

            long ok = controller.getHistorialAccesos().stream()

                    .filter(r -> r.estado() == EstadoAcceso.APROBADO).count();

            long proc = controller.getHistorialAccesos().stream()

                    .filter(r -> r.estado() == EstadoAcceso.EN_PROCESO).count();

            long den = controller.getHistorialAccesos().stream()

                    .filter(r -> r.estado() == EstadoAcceso.DENEGADO).count();

            lblValorTotal.setText(String.valueOf(total));

            lblValorAprobados.setText(String.valueOf(ok));

            lblValorProceso.setText(String.valueOf(proc));

            lblValorDenegados.setText(String.valueOf(den));

            countChip.setText(controller.getHistorialAccesos().size() + " registros");

        };

        refrescarKpis.run();

        controller.getHistorialAccesos().addListener(

                (javafx.collections.ListChangeListener<RegistroAcceso>) c -> refrescarKpis.run());



        Separator sepTitulo = new Separator();

        sepTitulo.setStyle("-fx-border-color: #E2E8F0;");



        TableView<RegistroAcceso> tabla = new TableView<>(controller.getHistorialAccesos());

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        tabla.setPrefHeight(420);

        tabla.setFixedCellSize(48);

        tabla.setStyle("-fx-background-color: #F8FAFC; -fx-border-color: #E2E8F0; -fx-border-width: 1; "

                + "-fx-border-radius: 12; -fx-background-radius: 12; -fx-font-size: 14px;");



        TableColumn<RegistroAcceso, String> cAtraccion = new TableColumn<>("Atracción");

        cAtraccion.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().nombreAtraccion()));



        TableColumn<RegistroAcceso, String> cEstado = new TableColumn<>("Estado");

        cEstado.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().estado().name()));

        cEstado.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(String item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    setText(item.replace("_", " "));

                    switch (EstadoAcceso.valueOf(item)) {

                        case APROBADO -> setStyle("-fx-background-color: #C8E6C9; -fx-text-fill: #1B5E20; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; -fx-alignment: CENTER;");

                        case EN_PROCESO -> setStyle("-fx-background-color: #FFF9C4; -fx-text-fill: #F57F17; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; -fx-alignment: CENTER;");

                        case DENEGADO -> setStyle("-fx-background-color: #FFCDD2; -fx-text-fill: #B71C1C; "

                                + "-fx-font-weight: 700; -fx-padding: 6 12; -fx-background-radius: 12; -fx-alignment: CENTER;");

                    }

                }

            }

        });



        TableColumn<RegistroAcceso, String> cMotivo = new TableColumn<>("Motivo");

        cMotivo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().motivo()));



        TableColumn<RegistroAcceso, Integer> cPuntos = new TableColumn<>("Puntos Usados");

        cPuntos.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().puntosUsados()));

        cPuntos.setCellFactory(c -> new TableCell<>() {

            @Override

            protected void updateItem(Integer item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");

                } else {

                    setText("" + item);

                    setStyle("-fx-text-fill: #FB8C00; -fx-font-weight: 700; -fx-alignment: CENTER;");

                }

            }

        });



        TableColumn<RegistroAcceso, String> cFecha = new TableColumn<>("Fecha/Hora");

        cFecha.setCellValueFactory(d -> new SimpleStringProperty(

                d.getValue().fechaHora().toLocalTime().toString().substring(0, 8)));



        tabla.getColumns().addAll(List.of(cAtraccion, cEstado, cMotivo, cPuntos, cFecha));



        // Leyenda visual

        HBox leyenda = new HBox(20);

        leyenda.setAlignment(Pos.CENTER_LEFT);

        leyenda.getChildren().addAll(

                crearLeyendaItem("", "Aprobado: ingresaste correctamente", "#2E7D32"),

                crearLeyendaItem("", "En proceso: solicitud pendiente", "#F57F17"),

                crearLeyendaItem("", "Denegado: requisitos o puntos insuficientes", "#C62828")

        );



        cardHistorial.getChildren().addAll(tituloRow, sepTitulo, tabla, leyenda);



        root.getChildren().addAll(headerSection, cardsKpi, cardHistorial);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(sp);

        return tab;

    }



    // TAB NOTIFICACIONES 



    private Tab tabNotificaciones() {

        Tab tab = new Tab("Notificaciones");



        VBox root = new VBox(30);

        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: #F5F7FA;");



        // Header

        VBox headerSection = new VBox(8);

        headerSection.setAlignment(Pos.CENTER_LEFT);

        Label tituloPrincipal = new Label("Centro de Notificaciones");

        tituloPrincipal.setStyle("-fx-font-size: 32px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label subtituloPrincipal = new Label("Alertas climáticas, mantenimientos y eventos en tiempo real");

        subtituloPrincipal.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        headerSection.getChildren().addAll(tituloPrincipal, subtituloPrincipal);



        // Card contenedor

        VBox cardNotif = new VBox(20);

        cardNotif.setStyle("-fx-background-color: white; -fx-padding: 35; -fx-background-radius: 20; "

                + "-fx-effect: dropshadow(gaussian, rgba(69,39,160,0.08), 25, 0, 0, 8);");



        HBox tituloRow = new HBox(15);

        tituloRow.setAlignment(Pos.CENTER_LEFT);

        Label tituloCard = new Label("Bandeja de entrada");

        tituloCard.setStyle("-fx-font-size: 22px; -fx-font-weight: 700; -fx-text-fill: #4527A0; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Label countChip = new Label(controller.getNotificaciones().size() + " notificaciones");

        countChip.setStyle("-fx-background-color: #EDE7F6; -fx-text-fill: #4527A0; "

                + "-fx-font-size: 12px; -fx-font-weight: 700; -fx-padding: 6 14; -fx-background-radius: 20; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        Region tituloSpacer = new Region();

        HBox.setHgrow(tituloSpacer, Priority.ALWAYS);

        tituloRow.getChildren().addAll(tituloCard, countChip, tituloSpacer);



        Separator sepTitulo = new Separator();

        sepTitulo.setStyle("-fx-border-color: #E2E8F0;");



        ListView<Notificacion> lista = new ListView<>(controller.getNotificaciones());

        lista.setPrefHeight(500);

        lista.setStyle("-fx-background-color: #F8FAFC; -fx-background-radius: 12; "

                + "-fx-border-color: #E2E8F0; -fx-border-width: 1; -fx-border-radius: 12; "

                + "-fx-padding: 8;");

        lista.setCellFactory(p -> new ListCell<>() {

            @Override

            protected void updateItem(Notificacion n, boolean empty) {

                super.updateItem(n, empty);

                if (empty || n == null) {

                    setText(null);

                    setGraphic(null);

                    setStyle("-fx-background-color: transparent;");

                } else {

                    String colorTipo;

                    String colorFondoTipo;

                    String icono;

                    switch (n.formatoNotificacion()) {

                        case ALERTA -> {

                            colorTipo = "#C62828";

                            colorFondoTipo = "#FFCDD2";

                            icono = "";

                        }

                        case INFORMACION -> {

                            colorTipo = "#1565C0";

                            colorFondoTipo = "#BBDEFB";

                            icono = "";

                        }

                        case MANTENIMIENTO -> {

                            colorTipo = "#EF6C00";

                            colorFondoTipo = "#FFE0B2";

                            icono = "";

                        }

                        default -> {

                            colorTipo = "#1565C0";

                            colorFondoTipo = "#BBDEFB";

                            icono = "";

                        }

                    }



                    HBox card = new HBox(15);

                    card.setAlignment(Pos.CENTER_LEFT);

                    card.setStyle("-fx-background-color: white; -fx-padding: 16 20; -fx-background-radius: 12; "

                            + "-fx-border-color: " + colorTipo + "; -fx-border-width: 0 0 0 4; "

                            + "-fx-border-radius: 12; "

                            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 6, 0, 0, 2);");



                    Label lblIcono = new Label(icono);

                    lblIcono.setStyle("-fx-font-size: 28px;");



                    VBox contenido = new VBox(6);

                    HBox.setHgrow(contenido, Priority.ALWAYS);



                    HBox header = new HBox(10);

                    header.setAlignment(Pos.CENTER_LEFT);



                    Label tipo = new Label(n.formatoNotificacion().toString());

                    tipo.setStyle("-fx-background-color: " + colorFondoTipo + "; -fx-text-fill: " + colorTipo + "; "

                            + "-fx-font-size: 11px; -fx-font-weight: 700; -fx-padding: 4 12; -fx-background-radius: 20; "

                            + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



                    Region sp2 = new Region();

                    HBox.setHgrow(sp2, Priority.ALWAYS);



                    Label hora = new Label("" + n.fechaHora().toLocalTime().toString().substring(0, 8));

                    hora.setStyle("-fx-font-size: 11px; -fx-text-fill: #94A3B8; -fx-font-weight: 500; "

                            + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



                    header.getChildren().addAll(tipo, sp2, hora);



                    Label mensaje = new Label(n.mensaje());

                    mensaje.setWrapText(true);

                    mensaje.setStyle("-fx-font-size: 14px; -fx-text-fill: #1E293B; -fx-font-weight: 500; "

                            + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");



                    contenido.getChildren().addAll(header, mensaje);

                    card.getChildren().addAll(lblIcono, contenido);



                    setGraphic(card);

                    setText(null);

                    setStyle("-fx-background-color: transparent; -fx-padding: 6 0;");

                }

            }

        });



        Label info = new Label("Recibirás alertas climáticas, avisos de mantenimiento y eventos especiales en tiempo real.");

        info.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B; -fx-font-style: italic; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        info.setWrapText(true);



        cardNotif.getChildren().addAll(tituloRow, sepTitulo, lista, info);

        root.getChildren().addAll(headerSection, cardNotif);



        ScrollPane sp = new ScrollPane(root);

        sp.setFitToWidth(true);

        sp.setStyle("-fx-background-color: #F5F7FA; -fx-background: #F5F7FA;");

        tab.setContent(sp);

        return tab;

    }



    // HELPERS 



    private Label seccion(String texto) {

        Label l = new Label(texto);

        l.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #4527A0;");

        return l;

    }



    private Label tarjetaValor(String etiqueta, String valor, String color) {

        Label l = new Label(etiqueta + "\n" + valor);

        l.setStyle("-fx-background-color: white; -fx-border-color: " + color

                + "; -fx-border-width: 2; -fx-padding: 12 20; -fx-background-radius: 8; "

                + "-fx-border-radius: 8; -fx-font-size: 14px; -fx-font-weight: bold; "

                + "-fx-text-fill: " + color + ";");

        return l;

    }



    private void agregarFila(GridPane g, String etiqueta, String valor, int fila) {

        Label lbl = new Label(etiqueta);

        lbl.setStyle("-fx-font-weight: bold;");

        g.add(lbl, 0, fila);

        g.add(new Label(valor), 1, fila);

    }

    

    private HBox crearFilaDato(String icono, String etiqueta, String valor) {

        HBox fila = new HBox(15);

        fila.setAlignment(Pos.CENTER_LEFT);

        fila.setStyle("-fx-padding: 12 0;");

        

        Label lblIcono = new Label(icono);

        lblIcono.setStyle("-fx-font-size: 24px;");

        lblIcono.setPrefWidth(40);

        

        VBox textoBox = new VBox(3);

        

        Label lblEtiqueta = new Label(etiqueta);

        lblEtiqueta.setStyle("-fx-font-size: 12px; -fx-text-fill: #757575; -fx-font-weight: 500; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        Label lblValor = new Label(valor);

        lblValor.setStyle("-fx-font-size: 16px; -fx-text-fill: #212121; -fx-font-weight: 600; "

                + "-fx-font-family: 'Segoe UI', 'Arial', sans-serif;");

        

        textoBox.getChildren().addAll(lblEtiqueta, lblValor);

        fila.getChildren().addAll(lblIcono, textoBox);

        

        return fila;

    }



    private <T> TableColumn<T, String> col(String titulo, String propiedad, double ancho) {

        TableColumn<T, String> c = new TableColumn<>(titulo);

        c.setCellValueFactory(new PropertyValueFactory<>(propiedad));

        if (ancho > 0) c.setPrefWidth(ancho);

        return c;

    }



    private void alerta(String titulo, String msg, Alert.AlertType tipo) {

        Alert a = new Alert(tipo);

        a.setTitle(titulo);

        a.setHeaderText(null);

        a.setContentText(msg);

        a.showAndWait();

    }

    

    private void actualizarBeneficioTicket() {

        if (lblBeneficioTicket == null) return;

        

        if (visitante.getDescuentoPuntos() > 0) {

            lblBeneficioTicket.setText(visitante.getDescuentoPuntos() + "% DESCUENTO");

            lblBeneficioTicket.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; "

                    + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 6 15; -fx-background-radius: 20;");

        } else if (visitante.tieneFastPass()) {

            lblBeneficioTicket.setText("PRIORIDAD EN COLAS");

            lblBeneficioTicket.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; "

                    + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 6 15; -fx-background-radius: 20;");

        } else if (visitante.tieneTicket()) {

            lblBeneficioTicket.setText("SIN BENEFICIOS");

            lblBeneficioTicket.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; "

                    + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 6 15; -fx-background-radius: 20;");

        } else {

            lblBeneficioTicket.setText("");

            lblBeneficioTicket.setStyle("");

        }

    }

    

    private void actualizarPaquetes() {

        if (cardPaqueteBasico != null) {

            actualizarCardPaquete(cardPaqueteBasico, 50);

        }

        if (cardPaqueteEstandar != null) {

            actualizarCardPaquete(cardPaqueteEstandar, 100);

        }

        if (cardPaquetePremium != null) {

            actualizarCardPaquete(cardPaquetePremium, 200);

        }

    }

    

    private void actualizarCardPaquete(VBox card, int puntos) {

        VBox precioBox = (VBox) card.getChildren().get(3);

        precioBox.getChildren().clear();

        

        float precioBase = puntos * 100;

        float precioFinal = visitante.calcularPrecioPuntos(puntos);

        int descuento = visitante.getDescuentoPuntos();



        if (descuento > 0) {

            Label lPrecioOriginal = new Label("$" + String.format("%.0f", precioBase));

            lPrecioOriginal.setStyle("-fx-font-size: 14px; -fx-text-fill: #999; -fx-strikethrough: true;");

            

            Label lPrecioFinal = new Label("$" + String.format("%.0f", precioFinal));

            lPrecioFinal.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");

            

            Label lDescuento = new Label("-" + descuento + "% DESCUENTO");

            lDescuento.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");

            

            precioBox.getChildren().addAll(lPrecioOriginal, lPrecioFinal, lDescuento);

        } else {

            Label lPrecio = new Label("$" + String.format("%.0f", precioFinal));

            lPrecio.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #212121;");

            precioBox.getChildren().add(lPrecio);

        }

    }



    private String fmt(float valor) {

        return String.format("%,.0f", valor);

    }

    

    private void mostrarDialogoTarjetaCredito() {

        Dialog<Float> dialog = new Dialog<>();

        dialog.setTitle("Recargar Saldo - Tarjeta de Crédito");

        dialog.setHeaderText("Ingresa los datos de tu tarjeta de crédito");

        

        ButtonType btnAceptar = new ButtonType("Procesar Pago", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(btnAceptar, ButtonType.CANCEL);

        

        GridPane grid = new GridPane();

        grid.setHgap(15);

        grid.setVgap(15);

        grid.setPadding(new Insets(25));

        grid.setStyle("-fx-background-color: #FAFAFA;");

        

        // Monto a recargar

        Label lblMonto = new Label("Monto a recargar:");

        lblMonto.setStyle("-fx-font-size: 13px; -fx-font-weight: 600;");

        TextField txtMonto = new TextField();

        txtMonto.setPromptText("Ej: 50000");

        txtMonto.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

        

        // Número de tarjeta

        Label lblNumero = new Label("Número de tarjeta:");

        lblNumero.setStyle("-fx-font-size: 13px; -fx-font-weight: 600;");

        TextField txtNumero = new TextField();

        txtNumero.setPromptText("1234 5678 9012 3456");

        txtNumero.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

        txtNumero.textProperty().addListener((obs, old, newVal) -> {

            if (newVal.length() > 19) {

                txtNumero.setText(old);

            } else {

                String formatted = newVal.replaceAll("[^0-9]", "");

                if (formatted.length() > 0) {

                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < formatted.length(); i++) {

                        if (i > 0 && i % 4 == 0) sb.append(" ");

                        sb.append(formatted.charAt(i));

                    }

                    if (!sb.toString().equals(newVal)) {

                        txtNumero.setText(sb.toString());

                        txtNumero.positionCaret(sb.length());

                    }

                }

            }

        });

        

        // Nombre del titular

        Label lblNombre = new Label("Nombre del titular:");

        lblNombre.setStyle("-fx-font-size: 13px; -fx-font-weight: 600;");

        TextField txtNombre = new TextField();

        txtNombre.setPromptText("Como aparece en la tarjeta");

        txtNombre.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

        

        // Fecha de vencimiento

        Label lblVencimiento = new Label("Fecha de vencimiento:");

        lblVencimiento.setStyle("-fx-font-size: 13px; -fx-font-weight: 600;");

        HBox vencimientoBox = new HBox(10);

        ComboBox<String> cmbMes = new ComboBox<>();

        cmbMes.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

        cmbMes.setPromptText("Mes");

        cmbMes.setStyle("-fx-font-size: 14px;");

        cmbMes.setPrefWidth(100);

        

        ComboBox<String> cmbAnio = new ComboBox<>();

        for (int i = 2024; i <= 2034; i++) {

            cmbAnio.getItems().add(String.valueOf(i));

        }

        cmbAnio.setPromptText("Año");

        cmbAnio.setStyle("-fx-font-size: 14px;");

        cmbAnio.setPrefWidth(100);

        vencimientoBox.getChildren().addAll(cmbMes, new Label("/"), cmbAnio);

        

        // CVV

        Label lblCVV = new Label("CVV:");

        lblCVV.setStyle("-fx-font-size: 13px; -fx-font-weight: 600;");

        TextField txtCVV = new TextField();

        txtCVV.setPromptText("123");

        txtCVV.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

        txtCVV.setPrefWidth(100);

        txtCVV.textProperty().addListener((obs, old, newVal) -> {

            if (!newVal.matches("\\d*") || newVal.length() > 4) {

                txtCVV.setText(old);

            }

        });

        

        // Agregar al grid

        grid.add(lblMonto, 0, 0);

        grid.add(txtMonto, 1, 0);

        grid.add(lblNumero, 0, 1);

        grid.add(txtNumero, 1, 1);

        grid.add(lblNombre, 0, 2);

        grid.add(txtNombre, 1, 2);

        grid.add(lblVencimiento, 0, 3);

        grid.add(vencimientoBox, 1, 3);

        grid.add(lblCVV, 0, 4);

        grid.add(txtCVV, 1, 4);

        

        // Nota de seguridad

        Label lblSeguridad = new Label("Transacción segura y encriptada");

        lblSeguridad.setStyle("-fx-font-size: 11px; -fx-text-fill: #4CAF50; -fx-font-weight: 600;");

        grid.add(lblSeguridad, 0, 5, 2, 1);

        

        dialog.getDialogPane().setContent(grid);

        

        // Validación y resultado

        dialog.setResultConverter(dialogButton -> {

            if (dialogButton == btnAceptar) {

                try {

                    float monto = Float.parseFloat(txtMonto.getText());

                    String numero = txtNumero.getText().replaceAll("\\s", "");

                    String nombre = txtNombre.getText().trim();

                    String mes = cmbMes.getValue();

                    String anio = cmbAnio.getValue();

                    String cvv = txtCVV.getText();

                    

                    if (monto <= 0) {

                        alerta("Error", "El monto debe ser mayor a 0", Alert.AlertType.ERROR);

                        return null;

                    }

                    if (numero.length() != 16) {

                        alerta("Error", "El número de tarjeta debe tener 16 dígitos", Alert.AlertType.ERROR);

                        return null;

                    }

                    if (nombre.isEmpty()) {

                        alerta("Error", "Ingrese el nombre del titular", Alert.AlertType.ERROR);

                        return null;

                    }

                    if (mes == null || anio == null) {

                        alerta("Error", "Seleccione la fecha de vencimiento", Alert.AlertType.ERROR);

                        return null;

                    }

                    if (cvv.length() < 3) {

                        alerta("Error", "El CVV debe tener al menos 3 dígitos", Alert.AlertType.ERROR);

                        return null;

                    }

                    

                    return monto;

                } catch (NumberFormatException e) {

                    alerta("Error", "Ingrese un monto válido", Alert.AlertType.ERROR);

                    return null;

                }

            }

            return null;

        });

        

        dialog.showAndWait().ifPresent(monto -> {

            controller.recargarSaldo(monto);

            refrescarTodo();

        });

    }

}

