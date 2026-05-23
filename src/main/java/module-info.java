module co.edu.uniquindio.poo.tech_park_uq {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.tech_park_uq to javafx.fxml;
    exports co.edu.uniquindio.poo.tech_park_uq;
}