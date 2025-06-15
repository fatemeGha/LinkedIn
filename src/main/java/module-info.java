module model {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires org.json;
    requires com.fasterxml.jackson.databind;
    requires javafx.base;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.jfoenix;
    requires com.fasterxml.jackson.core;



    opens controllers to javafx.fxml;
    //opens javafx.scene.image;
    exports controllers;
    exports model;


}
