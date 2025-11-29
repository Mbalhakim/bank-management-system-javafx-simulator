module org.example.bankmanagementsystemjavasim {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;
    opens org.example.bankmanagementsystemjavasim to javafx.fxml;
    exports org.example.bankmanagementsystemjavasim;
    opens org.example.bankmanagementsystemjavasim.controllers to javafx.fxml;
    exports org.example.bankmanagementsystemjavasim.controllers;
}