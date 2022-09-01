package com.equipeglr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class TelaController {

	@FXML private AnchorPane principal;
    @FXML private Label labelStatus;
    @FXML private TextArea areaMensagem;
    @FXML private Label btnNovo;
    @FXML private Label btnAbrir;
    @FXML private Label btnSalvar;
    @FXML private Label btnCopiar;
    @FXML private Label btnColar;
    @FXML private Label btnRecortar;
    @FXML private Label btnCompilar;
    @FXML private Label btnEquipe;
    @FXML private TextArea areaCodigo;
    @FXML private ListView<String> linhas;

    private static FileChooser ufc = new FileChooser();

    public void initialize() {
        linhas.getItems().add(String.valueOf(areaCodigo.getParagraphs().size()));
        ufc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    }

    public void colarTexto() {
        areaCodigo.paste();
        adicionarLinhas();
    }

    public void recortarTexto() {
        areaCodigo.cut();
        removerLinhas();
    }

    public void compilarArquivo() {
        areaMensagem.setText("->\tcompilação de programas ainda não foi implementada");
    }

    public void mostrarEquipe() {
        areaMensagem.setText("->\tGabriel Adriano Rodrigues\n->\tLeonardo Souza Nunes\n->\tRafael Barbosa Kretzer");
    }

    public void abrirNovo() {
        labelStatus.setText("");
        areaCodigo.setText("");
        areaMensagem.setText("");
        removerLinhas();
    }

    public void abrirArquivo() throws IOException {
        File selectedFile = ufc.showOpenDialog(btnAbrir.getScene().getWindow());
        if (selectedFile != null) {
            String s = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8);
            areaCodigo.setText(s);
            labelStatus.setText(selectedFile.getAbsolutePath());
            adicionarLinhas();
        }
    }

    private void adicionarLinhas() {
        int linhasExistentes = linhas.getItems().size();
        IntStream.range(0, areaCodigo.getParagraphs().size()).forEach(x -> {
            int linhaAtual = x+1;
            if (linhaAtual > linhasExistentes) {
                linhas.getItems().add(String.valueOf(linhaAtual));
            }
        });
        redefinirLarguraLista();
    }

    private void removerLinhas() {
        int linhasExistentes = areaCodigo.getParagraphs().size();
        List<String> linhaParaRemover = linhas.getItems().stream().filter(l -> Integer.valueOf(l) > linhasExistentes).collect(Collectors.toList());
        linhaParaRemover.forEach(l -> linhas.getItems().remove(l));
        redefinirLarguraLista();
    }

    private void redefinirLarguraLista() {
        String ultimaLinha = linhas.getItems().get(linhas.getItems().size() - 1);
        linhas.setMinWidth(20 * ultimaLinha.length());
        linhas.setMaxWidth(20 * ultimaLinha.length());
    }

    public void salvarArquivo() throws IOException {
        if (labelStatus.getText() == null || labelStatus.getText().isEmpty()) {
            criarNovo();
        }
        if (labelStatus.getText() != null && !labelStatus.getText().isEmpty()) {
            FileWriter writer = new FileWriter(labelStatus.getText(), Charset.availableCharsets().get("UTF-8"), false);
            writer.write(areaCodigo.getText());
            writer.close();
        }
    }

    private void criarNovo() throws IOException {
        File arquivo = ufc.showSaveDialog(areaCodigo.getScene().getWindow());
        if (arquivo == null){
            return;
        }
        arquivo.createNewFile();
        labelStatus.setText(arquivo.getAbsolutePath());
    }

    public void copiarTexto() {
        areaCodigo.copy();
    }

    public void alterarContadorDeLinhaAoDigitar(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER) || (keyEvent.getCode().equals(KeyCode.V) && keyEvent.isControlDown())) {
            adicionarLinhas();
        }
        if (keyEvent.getCode().equals(KeyCode.BACK_SPACE) || keyEvent.getCode().equals(KeyCode.DELETE) || (keyEvent.getCode().equals(KeyCode.X) && keyEvent.isControlDown())) {
            if (areaCodigo.getParagraphs().size() != linhas.getItems().size()) {
                removerLinhas();
            }
        }
    }
    
    public void realizaAcaoTela(KeyEvent keyEvent) throws IOException {
        if (keyEvent.isControlDown()) {
        	if (keyEvent.getCode().equals(KeyCode.S)) {
        		salvarArquivo();
        		return;
        	}
        	
        	if (keyEvent.getCode().equals(KeyCode.N)) {
        		abrirNovo();
        		return;
        	}
        	
        	if (keyEvent.getCode().equals(KeyCode.O)) {
        		abrirArquivo();
        		return;
        	}
        } else {
        	if (keyEvent.getCode().equals(KeyCode.F7)) {
        		compilarArquivo();
        	}
        	if (keyEvent.getCode().equals(KeyCode.F1)) {
        		mostrarEquipe();
        	}
        }
    }
}