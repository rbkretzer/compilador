package com.equipeglr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        //InputStream img = new BufferedInputStream(new FileInputStream("terminal.png"));
        //stage.getIcons().add(new Image(img));
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