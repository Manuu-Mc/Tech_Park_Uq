module co.edu.uniquindio.poo.techparkuq {

    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.base;
    requires javafx.fxml;

    // VIEW
    exports co.edu.uniquindio.poo.tech_park_uq.controller.view;
    opens co.edu.uniquindio.poo.tech_park_uq.controller.view to javafx.fxml;

    // CONTROLLER
    exports co.edu.uniquindio.poo.tech_park_uq.controller.controller;
    opens co.edu.uniquindio.poo.tech_park_uq.controller.controller to javafx.fxml;

    // MODEL
    exports co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties;
    opens co.edu.uniquindio.poo.tech_park_uq.controller.modell.enteties to javafx.base;
    exports co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums;
    opens co.edu.uniquindio.poo.tech_park_uq.controller.modell.enums to javafx.base;
    exports co.edu.uniquindio.poo.tech_park_uq.controller.modell.interfaces;
    exports co.edu.uniquindio.poo.tech_park_uq.controller.modell.records;
    opens co.edu.uniquindio.poo.tech_park_uq.controller.modell.records to javafx.base;
    exports co.edu.uniquindio.poo.tech_park_uq.controller.modell.abstracts;
    opens co.edu.uniquindio.poo.tech_park_uq.controller.modell.abstracts to javafx.base;

    // UTIL
    exports co.edu.uniquindio.poo.tech_park_uq.controller.util;
}