package co.edu.uniquindio.poo.tech_park_uq.controller.view;



import co.edu.uniquindio.poo.tech_park_uq.controller.controller.ParqueController;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.entities.*;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EspecialidadOperador;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.EstadoActual;

import co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums.TipoAtraccion;

import javafx.application.Application;

import javafx.stage.Stage;



public class MainApp extends Application {



    private ParqueController parqueController;



    @Override

    public void start(Stage stage) {

        parqueController = new ParqueController();

        cargarDatosPrueba();



        LoginView loginView = new LoginView(stage, parqueController);

        stage.setTitle("Tech-Park UQ");

        stage.setScene(loginView.crearEscena());

        stage.show();

    }



    private void cargarDatosPrueba() {

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // 1. CREAR ZONAS

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        Zona zonaAcuatica = new Zona("Zona Acuática", 200);

        Zona zonaMecanica = new Zona("Zona Mecánica", 150);

        Zona zonaInfantil = new Zona("Zona Infantil", 250);



        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // 2. CREAR OPERADORES CON ESPECIALIDADES

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        Operador operador1 = new Operador("Juan Martínez", "OP001", 32, "EMP001",

                zonaAcuatica, EspecialidadOperador.ACUATICA);

        Operador operador2 = new Operador("María González", "OP002", 28, "EMP002",

                zonaMecanica, EspecialidadOperador.MECANICA);

        Operador operador3 = new Operador("Pedro Sánchez", "OP003", 35, "EMP003",

                zonaInfantil, EspecialidadOperador.INFANTIL);

        parqueController.registrarOperador(operador1);

        parqueController.registrarOperador(operador2);

        parqueController.registrarOperador(operador3);



        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // 3. CREAR ADMINISTRADOR

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        Administrador admin1 = new Administrador("Carlos Mendoza", "ADMIN001", 45, "EMP999", 5000000);

        parqueController.registrarAdministrador(admin1);



        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // 4. CREAR VISITANTES

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        Visitante visitante1 = new Visitante("Ana Torres", "1001", 25, 1.65f, 150000);

        Visitante visitante2 = new Visitante("Carlos Ruiz", "1002", 30, 1.78f, 120000);

        Visitante visitante3 = new Visitante("Laura Díaz", "1003", 22, 1.60f, 100000);

        Visitante visitante4 = new Visitante("Miguel Pérez", "1004", 28, 1.82f, 200000);

        Visitante visitante5 = new Visitante("Sofía Castro", "1005", 19, 1.55f, 80000);

        Visitante visitante6 = new Visitante("Diego López", "1006", 35, 1.75f, 180000);

        Visitante visitante7 = new Visitante("Camila Rojas", "1007", 27, 1.68f, 130000);

        Visitante visitante8 = new Visitante("Andrés Vargas", "1008", 40, 1.80f, 250000);

        parqueController.registrarVisitante(visitante1);

        parqueController.registrarVisitante(visitante2);

        parqueController.registrarVisitante(visitante3);

        parqueController.registrarVisitante(visitante4);

        parqueController.registrarVisitante(visitante5);

        parqueController.registrarVisitante(visitante6);

        parqueController.registrarVisitante(visitante7);

        parqueController.registrarVisitante(visitante8);



        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // 5. CREAR ATRACCIONES Y ASIGNAR OPERADORES

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // Atracciones acuáticas (Operador 1 - Juan Martínez)

        Atraccion tobogan = new Atraccion("ATR001", "Tobogán Acuático Extremo",

                20, 1.40f, 12, 15000, TipoAtraccion.ACUATICA);

        tobogan.setOperadorAsignado(operador1);



        Atraccion rioRapido = new Atraccion("ATR002", "Río Rápido",

                30, 1.20f, 8, 12000, TipoAtraccion.ACUATICA);

        rioRapido.setOperadorAsignado(operador1);



        Atraccion olaGigante = new Atraccion("ATR003", "Ola Gigante",

                40, 1.30f, 10, 18000, TipoAtraccion.ACUATICA);

        olaGigante.setOperadorAsignado(operador1);



        // Atracciones mecánicas (Operador 2 - María González)

        Atraccion ruedaFortuna = new Atraccion("ATR004", "Rueda de la Fortuna",

                40, 1.30f, 10, 10000, TipoAtraccion.MECANICA_ALTURA);

        ruedaFortuna.setOperadorAsignado(operador2);



        Atraccion montanaRusa = new Atraccion("ATR005", "Montaña Rusa",

                24, 1.50f, 14, 20000, TipoAtraccion.MECANICA_ALTURA);

        montanaRusa.setOperadorAsignado(operador2);



        Atraccion barcoVikingo = new Atraccion("ATR006", "Barco Vikingo",

                30, 1.40f, 12, 16000, TipoAtraccion.MECANICA_ALTURA);

        barcoVikingo.setOperadorAsignado(operador2);



        // Atracciones infantiles (Operador 3 - Pedro Sánchez)

        Atraccion carrusel = new Atraccion("ATR007", "Carrusel Mágico",

                25, 0.90f, 3, 8000, TipoAtraccion.INFANTIL);

        carrusel.setOperadorAsignado(operador3);



        Atraccion trenInfantil = new Atraccion("ATR008", "Tren Infantil",

                30, 0.80f, 2, 6000, TipoAtraccion.INFANTIL);

        trenInfantil.setOperadorAsignado(operador3);



        Atraccion saltarines = new Atraccion("ATR009", "Cama de Saltarines",

                15, 1.00f, 4, 5000, TipoAtraccion.INFANTIL);

        saltarines.setOperadorAsignado(operador3);



        parqueController.agregarAtraccion(tobogan);

        parqueController.agregarAtraccion(rioRapido);

        parqueController.agregarAtraccion(olaGigante);

        parqueController.agregarAtraccion(ruedaFortuna);

        parqueController.agregarAtraccion(montanaRusa);

        parqueController.agregarAtraccion(barcoVikingo);

        parqueController.agregarAtraccion(carrusel);

        parqueController.agregarAtraccion(trenInfantil);

        parqueController.agregarAtraccion(saltarines);



        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // 6. SIMULAR ESTADOS DE LAS ATRACCIONES (para probar mantenimiento)

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // Una atracción por operador en EN_MANTENIMIENTO para pruebas inmediatas

        olaGigante.setEstadoActual(EstadoActual.EN_MANTENIMIENTO);

        montanaRusa.setEstadoActual(EstadoActual.EN_MANTENIMIENTO);

        trenInfantil.setEstadoActual(EstadoActual.EN_MANTENIMIENTO);



        // Una atracción CERRADA para probar el estado cerrado

        barcoVikingo.setEstadoActual(EstadoActual.CERRADO);



        // Atracción cerca del límite de mantenimiento automático (498/500)

        // Después de 2 ingresos más entrará en mantenimiento automático

        for (int i = 0; i < 498; i++) {

            ruedaFortuna.registrarIngreso();

        }



        // Otra atracción con visitas medias para mostrar estadísticas

        for (int i = 0; i < 250; i++) {

            tobogan.registrarIngreso();

        }

        for (int i = 0; i < 120; i++) {

            carrusel.registrarIngreso();

        }



        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        // 7. DAR PUNTOS INICIALES A LOS VISITANTES PARA PRUEBAS

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•

        visitante1.agregarPuntos(150);

        visitante2.agregarPuntos(200);

        visitante3.agregarPuntos(100);

        visitante4.agregarPuntos(80);

        visitante5.agregarPuntos(50);

        visitante6.agregarPuntos(300);

        visitante7.agregarPuntos(75);

        visitante8.agregarPuntos(220);

    }



    @SuppressWarnings("unused")
    public static void main(String[] args) {
        launch(args);
    }

}

