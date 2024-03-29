package com.equipeglr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.IntStream;

import com.equipeglr.gals.LexicalError;
import com.equipeglr.gals.Lexico;
import com.equipeglr.gals.SemanticError;
import com.equipeglr.gals.Semantico;
import com.equipeglr.gals.Sintatico;
import com.equipeglr.gals.SyntaticError;
import com.equipeglr.gals.Token;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

public class TelaController {

    @FXML
    private Label labelStatus;

    @FXML
    private TextArea areaMensagem;

    @FXML
    private TextArea areaCodigo;

    @FXML
    private TextArea linhas;

    @FXML
    private Label labelLinhas;

    private static final FileChooser ufc = new FileChooser();

    private String pathToCompile;

    public void initialize() {
        linhas.setText(String.valueOf(areaCodigo.getParagraphs().size()));
        labelLinhas.setVisible(false);
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
        areaMensagem.setText("");
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico();
        lexico.setInput(areaCodigo.getText());
        new Thread(() -> {
            try {
                semantico.setPath(pathToCompile);
                sintatico.parse(lexico, semantico);
                areaMensagem.appendText("Programa compilado com sucesso");
            } catch (LexicalError e) {
                areaMensagem.setText("Erro na linha " +
                        getLinha(e.getPosition()) + " - "
                        + (e.getMessage().contains("símbolo inválido")
                                ? areaCodigo.getText().charAt(e.getPosition())
                                : "")
                        + " " + e.getMessage());
            } catch (SyntaticError e) {
                Token tokenAtual = sintatico.getToken();
                areaMensagem.setText("Erro na linha " + getLinha(tokenAtual.getPosition()) + " - encontrado "
                        + parseIfEOF(tokenAtual.getLexeme()) + " esperado " + e.getMessage());
            } catch (SemanticError e) {
                Token tokoenAtual = sintatico.getToken();
                areaMensagem.setText("Erro na linha " + getLinha(tokoenAtual.getPosition()) + " - " + e.getMessage());
            } catch (IOException e) {
                areaMensagem.setText("Erro ao salvar o arquivo compilado: " + e.getMessage());
            }
        }).start();

    }

    private String parseIfEOF(String lexeme) {
        return lexeme == "$" ? "EOF" : lexeme;
    }

    private String getLinha(int position) {
        int linesEncountered = 0;
        for (int i = 0; i < position; i++) {

            if (areaCodigo.getText().charAt(i) == '\n') {
                linesEncountered++;
            }
        }
        return String.valueOf(linesEncountered + 1);
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
        File selectedFile = ufc.showOpenDialog(areaCodigo.getScene().getWindow());
        if (selectedFile != null) {
            String s = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8);
            areaCodigo.setText(s);
            labelStatus.setText(selectedFile.getAbsolutePath());
            pathToCompile = selectedFile.getAbsolutePath();
            adicionarLinhas();
        }
    }

    private void adicionarLinhas() {
        int linhasExistentes = getQuantidadeLinhas();
        int tamUltimaLinhaAnterior = getTamanhoUltimaLinha();
        IntStream.range(0, areaCodigo.getParagraphs().size()).forEach(x -> {
            int linhaAtual = x + 1;
            if (linhaAtual > linhasExistentes) {
                linhas.setText(linhas.getText().concat("\n" + linhaAtual));
            }
        });
        redefinirLarguraLista(tamUltimaLinhaAnterior);
    }

    private void removerLinhas() {
        int linhasExistentes = getQuantidadeLinhas();
        int tamUltimaLinhaAnterior = getTamanhoUltimaLinha();
        IntStream.range(1, linhasExistentes).forEach(x -> {
            int linhaAtual = x + 1;
            if (linhaAtual > areaCodigo.getParagraphs().size()) {
                linhas.setText(linhas.getText().replaceFirst("\n" + linhaAtual, ""));
            }
        });
        redefinirLarguraLista(tamUltimaLinhaAnterior);
    }

    private int getTamanhoUltimaLinha() {
        String[] todasLinhas = linhas.getText().split("\n");
        return todasLinhas[todasLinhas.length - 1].length();
    }

    private void redefinirLarguraLista(int tamUltimaLinhaAnterior) {
        int tamUltimaLinha = getTamanhoUltimaLinha();
        if (tamUltimaLinhaAnterior != tamUltimaLinha) {
            double newScale = (tamUltimaLinha == 2 ? 0.6 : (tamUltimaLinha == 1 ? 1 : 0.5));
            linhas.setMinWidth(20 * tamUltimaLinha * newScale);
            linhas.setMaxWidth(20 * tamUltimaLinha * newScale);
        }
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
        if (arquivo == null) {
            return;
        }
        arquivo.createNewFile();
        labelStatus.setText(arquivo.getAbsolutePath());
        pathToCompile = arquivo.getAbsolutePath();
    }

    public void copiarTexto() {
        areaCodigo.copy();
    }

    public void alterarContadorDeLinhaAoDigitar(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)
                || (keyEvent.getCode().equals(KeyCode.V) && keyEvent.isControlDown())) {
            adicionarLinhas();
        }
        if (keyEvent.getCode().equals(KeyCode.BACK_SPACE) || keyEvent.getCode().equals(KeyCode.DELETE)
                || (keyEvent.getCode().equals(KeyCode.X) && keyEvent.isControlDown())) {
            if (areaCodigo.getParagraphs().size() != getQuantidadeLinhas()) {
                removerLinhas();
            }
        }
    }

    private int getQuantidadeLinhas() {
        return linhas.getText().split("\n").length;
    }

    public void realizaAcaoTela(KeyEvent keyEvent) throws IOException {
        if (keyEvent.isControlDown()) {
            if (keyEvent.getCode().equals(KeyCode.S)) {
                salvarArquivo();
            }

            if (keyEvent.getCode().equals(KeyCode.N)) {
                abrirNovo();
            }

            if (keyEvent.getCode().equals(KeyCode.O)) {
                abrirArquivo();
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