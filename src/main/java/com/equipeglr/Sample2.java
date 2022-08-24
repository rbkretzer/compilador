package com.equipeglr;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.text.Font;

import java.io.IOException;

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

    public void colarTexto(MouseEvent mouseEvent) {
    }

    public void recortarTexto(MouseEvent mouseEvent) {
    }

    public void compilarArquivo(MouseEvent mouseEvent) {
    }

    public void mostrarEquipe(MouseEvent mouseEvent) {
    }

    public void abrirNovo(MouseEvent mouseEvent) {
    }

    public void abrirArquivo(MouseEvent mouseEvent) {
    }

    public void salvarArquivo(MouseEvent mouseEvent) {
    }

    public void copiarTexto(MouseEvent mouseEvent) {
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
        Font novaFonte = new Font(areaCodigo.getFont().getSize());
        areaCodigo.setFont(novaFonte);
    }
}