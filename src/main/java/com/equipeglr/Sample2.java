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
import javafx.scene.input.ZoomEvent;
import javafx.scene.text.Font;

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
    @FXML private ListView linhas;
    private static Alert mostraEquipe = new Alert(AlertType.NONE);

    public void initialize() {
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
        this.mostraEquipe.show();
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
            System.out.println("Apertei enter");
            System.out.println("Adicionando linha: " + areaCodigo.getParagraphs().size());
            linhas.getItems().add(areaCodigo.getParagraphs().size());
        }
        if (keyEvent.getCode().equals(KeyCode.BACK_SPACE) || keyEvent.getCode().equals(KeyCode.DELETE)) {
            if (areaCodigo.getParagraphs().size() != linhas.getItems().size()) {
                System.out.println("Removendo linha: " + areaCodigo.getParagraphs().size());
                linhas.getItems().remove(areaCodigo.getParagraphs().size());
            }
        }
    }

    public void aumentarFonteCodigo(ZoomEvent zoomEvent) {
        System.out.println(zoomEvent.getTotalZoomFactor());
        Font novaFonte = new Font(zoomEvent.getZoomFactor());
        areaCodigo.setFont(novaFonte);
    }
}