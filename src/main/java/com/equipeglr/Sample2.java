package com.equipeglr;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Window;

public class Sample2 {

    @FXML private Label btnNovo;
    @FXML private Label btnAbrir;
    @FXML private Label btnSalvar;
    @FXML private Label btnCopiar;
    @FXML private Label btnColar;
    @FXML private Label btnRecortar;
    @FXML private Label btnCompilar;
    @FXML private Label btnEquipe;
    @FXML private TextArea areaCodigo;
    @FXML private ListView<Integer> linhas;
    private static Alert mostraEquipe = new Alert(AlertType.NONE);

    public void initialize() {
        mostraEquipe.setTitle("Equipe");
        mostraEquipe.setContentText("\t\tGabriel Adriano Rodrigues\n\t\tLeonardo Souza Nunes\n\t\tRafael Barbosa Kreteer");
        linhas.getItems().add(areaCodigo.getParagraphs().size());
        areaCodigo.addEventFilter(ScrollEvent.SCROLL, event -> {
                if(event.isControlDown()) {
                    System.out.println();
                }
            }
        );
    }

    public void colarTexto(MouseEvent mouseEvent) {
        areaCodigo.paste();
    }

    public void recortarTexto(MouseEvent mouseEvent) {
        areaCodigo.cut();
    }

    public void compilarArquivo(MouseEvent mouseEvent) {
    }

    public void mostrarEquipe(MouseEvent mouseEvent) {
        Window window = mostraEquipe.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> mostraEquipe.hide());
        mostraEquipe.show();
    }

    public void abrirNovo(MouseEvent mouseEvent) {
    }

    public void abrirArquivo(MouseEvent mouseEvent) {
    }

    public void salvarArquivo(MouseEvent mouseEvent) {

    }

    public void copiarTexto(MouseEvent mouseEvent) {
        areaCodigo.copy();
    }

    public void alteraContadorDeLinha(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            linhas.getItems().add(areaCodigo.getParagraphs().size());
        }
        if (keyEvent.getCode().equals(KeyCode.BACK_SPACE) || keyEvent.getCode().equals(KeyCode.DELETE)) {
            if (areaCodigo.getParagraphs().size() != linhas.getItems().size()) {
                linhas.getItems().remove(areaCodigo.getParagraphs().size());
            }
        }
    }
}