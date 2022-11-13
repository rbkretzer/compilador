package com.equipeglr.gals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Stack;
import java.util.StringJoiner;

public class Semantico implements Constants {
    private static final StringJoiner joiner = new StringJoiner("\n");
    private static final Stack<String> pilha = new Stack<>();
    private char operador;

    public void executeAction(int action, Token token) throws SemanticError, IOException {
        System.out.println("Ação #" + action + ", Token: " + token);
        switch (action) {
            case 1:
                acionaToken1();
                break;
            case 2:
                acionaToken2();
                break;
            case 3:
                acionaToken3();
                break;
            case 4:
                acionaToken4();
                break;
            case 5:
                acionaToken5(token);
                break;
            case 6:
                acionaToken6(token);
                break;
            case 7:
                acionaToken7();
                break;
            case 8:
                acionaToken8();
                break;
            case 9:
                acionaToken9(token);
                break;
            case 10:
                acionaToken10();
                break;
            case 11:
                acionaToken11(token);
                break;
            case 12:
                acionaToken12(token);
                break;
            case 13:
                acionaToken13();
                break;
            case 14:
                acionaToken14();
                break;
            case 15:
                acionaToken15();
                break;
            case 16:
                acionaToken16();
                break;
            case 17:
                acionaToken17();
                break;
            case 18:
                acionaToken18();
                break;
            case 19:
                acionaToken19();
                break;
            case 20:
                acionaToken20();
                break;
            case 21:
                acionaToken21();
                break;
            case 22:
                acionaToken22();
                break;
        }
    }

    private void acionaToken1() {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        if (tipo1.equals("float64") || tipo2.equals("float64")) {
            pilha.push("float64");
        } else {
            pilha.push("int64");
        }
        joiner.add("add");
    }

    private void acionaToken2() {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        if (tipo1.equals("float64") || tipo2.equals("float64")) {
            pilha.push("float64");
        } else {
            pilha.push("int64");
        }
        joiner.add("sub");
    }

    private void acionaToken3() {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        if (tipo1.equals("float64") || tipo2.equals("float64")) {
            pilha.push("float64");
        } else {
            pilha.push("int64");
        }
        joiner.add("mul");
    }

    private void acionaToken4() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        if (!tipo1.equals(tipo2)) {
            throw new SemanticError("ERRO SEMÂNTICO, PARAR");
        }
        pilha.push(tipo1);
        joiner.add("div");
    }

    private void acionaToken5(Token token) {
        pilha.push("int64");
        joiner.add("ldc.i8 " + token.getLexeme());
        joiner.add("conv.r8");
    }

    private void acionaToken6(Token token) {
        pilha.push("int64");
        joiner.add("ldc.r8 " + token.getLexeme());
    }

    private void acionaToken7() throws SemanticError {
        String tipo = pilha.pop();
        if (!(tipo.equals("float64") || tipo.equals("int64"))) {
            throw new SemanticError("ERRO SEMÂNTICO, PARAR");
        }
        pilha.push(tipo);
    }

    private void acionaToken8() throws SemanticError {
        String tipo = pilha.pop();
        if (!(tipo.equals("float64") || tipo.equals("int64"))) {
            throw new SemanticError("ERRO SEMÂNTICO, PARAR");
        }
        pilha.push(tipo);
        joiner.add("ldc.i8 -1");
        joiner.add("conv.r8");
        joiner.add("mul");
    }

    private void acionaToken9(Token token) {
        operador = token.getLexeme().charAt(0);
    }

    private void acionaToken10() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        if (!tipo1.equals(tipo2)) {
            throw new SemanticError("ERRO SEMÂNTICO, PARAR");
        }
        pilha.push("bool");
        switch (operador) {
            case '>':
                joiner.add("cgt");
                break;
            case '<':
                joiner.add("clt");
                break;
            default:
                joiner.add("ceq");
        }
    }

    private void acionaToken11(Token token) {
        pilha.push("bool");
        joiner.add("ldc.i4.1");
    }

    private void acionaToken12(Token token) {
        pilha.push("bool");
        joiner.add("ldc.i4.0");
    }

    private void acionaToken13() throws SemanticError {
        String tipo = pilha.pop();
        if (!tipo.equals("bool")) {
            throw new SemanticError("erro semântico, parar");
        } 
        pilha.push("bool");
        joiner.add("ldc.i4.1");
        joiner.add("xor");
    }

    private void acionaToken14() {
        String tipo = pilha.pop();
        if (tipo.equals("int64")) {
            joiner.add("conv.i8");
        }
        joiner.add("call void [mscorlib]System.Console::Write(" + tipo + ")");
    }

    private void acionaToken15() {
        joiner.add(".assembly extern mscorlib {}");
        joiner.add(".assembly _codigo_objeto{}");
        joiner.add(".module   _codigo_objeto.exe");
        joiner.add(".class public _UNICA{");
        joiner.add(".method static public void _principal() {");
        joiner.add("    .entrypoint");
    }

    private void acionaToken16() throws IOException {
        joiner.add("    ret");
        joiner.add("    }");
        joiner.add("}");

        Path path = Paths.get("F:\\Users\\RBK\\Desktop\\teste.txt");

        
        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        writer.write(joiner.toString());
        writer.flush();
    }

    private void acionaToken17() {
    }

    private void acionaToken18() {
    }

    private void acionaToken19() {
    }

    private void acionaToken20() {
    }

    private void acionaToken21() {
    }

    private void acionaToken22() {
    }
}
