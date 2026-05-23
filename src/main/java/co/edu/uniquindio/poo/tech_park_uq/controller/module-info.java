module co.edu.uniquindio.poo.techparkuq {

    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.base;
    requires javafx.fxml;

    // VIEW
    exports co.edu.uniquindio.poo.techparkuq.view;
    opens co.edu.uniquindio.poo.techparkuq.view to javafx.fxml;

    // CONTROLLER
    exports co.edu.uniquindio.poo.techparkuq.controller;
    opens co.edu.uniquindio.poo.techparkuq.controller to javafx.fxml;

    // MODEL
    exports co.edu.uniquindio.poo.techparkuq.model.entities;
    opens co.edu.uniquindio.poo.techparkuq.model.entities to javafx.base;
    exports co.edu.uniquindio.poo.techparkuq.model.enums;
    opens co.edu.uniquindio.poo.techparkuq.model.enums to javafx.base;
    exports co.edu.uniquindio.poo.techparkuq.model.interfaces;
    exports co.edu.uniquindio.poo.techparkuq.model.records;
    opens co.edu.uniquindio.poo.techparkuq.model.records to javafx.base;
    exports co.edu.uniquindio.poo.techparkuq.model.abstracts;
    opens co.edu.uniquindio.poo.techparkuq.model.abstracts to javafx.base;

    // UTIL
    exports co.edu.uniquindio.poo.techparkuq.util;
}