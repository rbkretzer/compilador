package com.equipeglr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML(), 910, 600);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.minHeightProperty().set(600);
        stage.minWidthProperty().set(910);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("images/terminal.png")));
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scene.getRoot().applyCss();
        setScroll(scene);
        stage.show();
    }

    private void setScroll(Scene scene) {
        TextArea areaCodigo = (TextArea) scene.lookup("#areaCodigo");
        areaCodigo.setStyle("-fx-font-family: monospace");
        TextArea areaMensagem = (TextArea) scene.lookup("#areaMensagem");
        areaMensagem.setStyle("-fx-font-family: monospace");
        TextArea linhas = (TextArea) scene.lookup("#linhas");
        linhas.setStyle("-fx-font-family: monospace");
        ScrollBar n1 = (ScrollBar) areaCodigo.lookup(".scroll-bar");
        if (n1 != null) {
            ScrollBar n2 = (ScrollBar) linhas.lookup(".scroll-bar");
            if (n2 != null) {
                n1.valueProperty().bindBidirectional(n2.valueProperty());
            }
        }
        setScrollPanePolicy(areaCodigo, ScrollPane.ScrollBarPolicy.ALWAYS);
        setScrollPanePolicy(areaMensagem, ScrollPane.ScrollBarPolicy.ALWAYS);
        setScrollPanePolicy(linhas, ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void setScrollPanePolicy(Node node, ScrollPane.ScrollBarPolicy policy) {
        ScrollPane scrollPaneCodigo = (ScrollPane) node.lookup(".scroll-pane");
        scrollPaneCodigo.setVbarPolicy(policy);
        scrollPaneCodigo.setHbarPolicy(policy);
    }

    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("tela.fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}