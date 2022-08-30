package com.equipeglr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML("tela"), 910, 600);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.minHeightProperty().set(600);
        stage.minWidthProperty().set(910);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scene.getRoot().applyCss();
        TextArea areaCodigo = (TextArea) scene.lookup("#areaCodigo");
        ListView<String> linhas = (ListView<String>) scene.lookup("#linhas");
        Node n1 = areaCodigo.lookup(".scroll-bar");
        if (n1 instanceof ScrollBar) {
            final ScrollBar bar1 = (ScrollBar) n1;
            Node n2 = linhas.lookup(".scroll-bar");
            if (n2 instanceof ScrollBar) {
                final ScrollBar bar2 = (ScrollBar) n2;
                bar1.valueProperty().bindBidirectional(bar2.valueProperty());
            }
        }

        stage.setTitle("Compilador GLR");
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}