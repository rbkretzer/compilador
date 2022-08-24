package com.equipeglr;

import com.sun.javafx.scene.text.TextLayout;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private Parent createContent() {
        TextArea area = new TextArea();
        area.setWrapText(true);
        area.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println("Apertei enter");
                System.out.println("Adicionando linha: " + area.getParagraphs().size());
            }
            if (event.getCode().equals(KeyCode.BACK_SPACE) || event.getCode().equals(KeyCode.DELETE)) {
                System.out.println("Removendo linha: " + area.getParagraphs().size());
            }

        });

        Button append = new Button("append paragraph");
        append.setOnAction(e -> {
            area.appendText("Adicionando texto"+"\n ");
            System.out.println("paragraphs: " + area.getParagraphs().size());
        });
        Button logLines = new Button("log lines");
        logLines.setOnAction(e -> {
            Text text = (Text) area.lookup(".text");
            // getTextLayout is a private method in text, have to access reflectively
            // this is my utility method, use your own :)
            TextLayout layout;
            try {
                layout = (TextLayout) FXUtils.invokeGetMethodValue(Text.class, text, "getTextLayout");
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(layout.getLines().length);
        });
        BorderPane content = new BorderPane(area);
        content.setBottom(new HBox(10, append, logLines));
        return content;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}