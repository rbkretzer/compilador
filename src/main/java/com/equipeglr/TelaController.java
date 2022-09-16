package com.equipeglr;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Formatter;
import java.util.stream.IntStream;

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
        lexico.setInput(areaCodigo.getText());
        Formatter fmt = new Formatter();
        fmt.format("%5s %15s %25s\n", "Linha", "Classe", "Lexema");        
        try {
            Token t = null;
            while ((t = lexico.nextToken()) != null) {
                fmt.format("%4s %22s %20s\n", getLinha(t.getPosition()), converteParaClasse(t.getId()), t.getLexeme());
                // compiled += getLinha(t.getPosition()) + "\t" + converteParaClasse(t.getId()) + "\t" + t.getLexeme()
            }
            areaMensagem.setText(fmt + "\n\t Programa compilado com sucesso");
        } catch (LexicalError e) { // tratamento de erros
            areaMensagem.setText("Erro na linha " + getLinha(e.getPosition()) + " - " + e.getMessage());

            // e.getMessage() - retorna a mensagem de erro de SCANNER_ERRO (olhar
            // ScannerConstants.java e adaptar conforme o enunciado da parte 2)

            // e.getPosition() - retorna a posição inicial do erro, tem que adaptar para
            // mostrar a linha
        }
    }

    private String converteParaClasse(int id) {
        return Classe.values()[id - 2].toString();
    }

    private String getLinha(int position) {
        int linesEncountered = 0;
        for (int i = 0; i < position; i++) {

            if (areaCodigo.getText().charAt(i) == '\n') {
                // next line char encountered
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
            adicionarLinhas();
        }
    }

    private void adicionarLinhas() {
        int linhasExistentes = getQuantidadeLinhas();
        int tamUltimaLinhaAnterior = getTamanhoUltimaLinha();
        IntStream.range(0, areaCodigo.getParagraphs().size()).forEach(x -> {
            int linhaAtual = x + 1;
            if (linhaAtual > linhasExistentes) {
                linhas.setText(linhas.getText().concat("\n" + String.valueOf(linhaAtual)));
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
                linhas.setText(linhas.getText().replaceFirst("\n" + String.valueOf(linhaAtual), ""));
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
            String path = labelStatus.getText().contains(".txt") ? labelStatus.getText()
                    : labelStatus.getText().concat(".txt");
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
    }

    public void copiarTexto() {
        areaCodigo.copy();
    }

    public void alterarContadorDeLinhaAoDigitar(KeyEvent keyEvent) throws IOException {
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