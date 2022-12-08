package com.equipeglr.gals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringJoiner;

public class Semantico implements Constants {
    private static StringJoiner joiner = new StringJoiner("\n");
    private static final Stack<String> pilha = new Stack<>();
    private static final Stack<String> pilhaRotulos = new Stack<>();
    private int numeroRotulo = 0;
    private Map<String, String> tabelaSimbolos = new HashMap<>();
    private String tipoVar;
    private List<String> listaId = new ArrayList<>();
    private String operador;
    private String pathToCompile;

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
                acionaToken21(token);
                break;
            case 22:
                acionaToken22(token);
                break;
            case 24:
                acionaToken24();
                break;
            case 25:
                acionaToken25();
                break;
            case 26:
                acionaToken26();
                break;
            case 27:
                acionaToken27();
                break;
            case 28:
                acionaToken28();
                break;
            case 30:
                acionaToken30(token);
                break;
            case 31:
                acionaToken31();
                break;
            case 32:
                acionaToken32(token);
                break;
            case 33:
                acionaToken33(token);
                break;
            case 34:
                acionaToken34();
                break;
            case 35:
                acionaToken35();
                break;
        }
    }

    private void acionaToken1() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        validaTiposAritmetico(tipo1, tipo2);
        addAritmetico(tipo1, tipo2);
        joiner.add("add");
    }

    private void acionaToken2() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        validaTiposAritmetico(tipo1, tipo2);
        addAritmetico(tipo1, tipo2);
        joiner.add("sub");
    }

    private void acionaToken3() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        validaTiposAritmetico(tipo1, tipo2);
        addAritmetico(tipo1, tipo2);
        joiner.add("mul");
    }

    private void acionaToken4() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        validaTiposAritmetico(tipo1, tipo2);
        addAritmetico(tipo1, tipo2);
        joiner.add("div");
    }

    private void validaTiposAritmetico(String tipo1, String tipo2) throws SemanticError {
        List<String> tiposValidos = Arrays.asList("float64", "int64");
        if (!tiposValidos.contains(tipo1) || !tiposValidos.contains(tipo2)) {
            throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética");
        }
    }

    private void addAritmetico(String tipo1, String tipo2) {
        if (tipo1.equals("float64") || tipo2.equals("float64")) {
            pilha.push("float64");
        } else {
            pilha.push("int64");
        }
    }

    private void acionaToken5(Token token) {
        pilha.push("int64");
        joiner.add("ldc.i8 " + converteTokenInt64(token));
        joiner.add("conv.r8");
    }

    
    private String converteTokenInt64(Token token) {
        if (!token.getLexeme().matches("[0-9]*[1-9]d[1-9][0-9]*")) {
            return token.getLexeme();
        }
        String[] found = token.getLexeme().split("d");
        String prefix = found[0];
        String base = found[1];
        
        Double value = Double.valueOf(prefix) * Math.pow(10l, Double.valueOf(base));
        return String.valueOf(value.intValue());
    }

    private void acionaToken6(Token token) {
        pilha.push("float64");
        joiner.add("ldc.r8 " + converteTokenFloat64(token));
    }

    private String converteTokenFloat64(Token token) {
        String lexeme = token.getLexeme();
        if (lexeme.charAt(0) == '.') {
            lexeme = "0" + lexeme;
        }
        if (!lexeme.matches("[0-9]*.[0-9]*[1-9]d[1-9][0-9]*")) {
            return lexeme;
        }
        String[] found = lexeme.split("d");
        String prefix = found[0];
        String base = found[1];
        
        Double value = Double.valueOf(prefix) * Math.pow(10l, Double.valueOf(base));
        return String.valueOf(value);
    }

    private void acionaToken7() throws SemanticError {
        String tipo = pilha.pop();
        validaTipoAritmeticoUnico(tipo);
        pilha.push(tipo);
    }

    private void acionaToken8() throws SemanticError {
        String tipo = pilha.pop();
        validaTipoAritmeticoUnico(tipo);
        pilha.push(tipo);
        joiner.add("ldc.i8 -1");
        joiner.add("conv.r8");
        joiner.add("mul");
    }

    private void validaTipoAritmeticoUnico(String tipo) throws SemanticError {
        if (!(tipo.equals("float64") || tipo.equals("int64"))) {
            throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética");
        }
    }

    private void acionaToken9(Token token) {
        operador = token.getLexeme();
    }

    private void acionaToken10() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        if (!tipo1.equals(tipo2)) {
            throw new SemanticError("tipo(s) incompatível(is) em expressão relacional");
        }
        pilha.push("bool");
        if (operador.equals(">")) {
            joiner.add("cgt");
            return;
        }
        if (operador.equals("<")) {
            joiner.add("clt");
            return;
        }
        joiner.add("ceq");
        if (operador.equals("==")) {
            return;
        }
        joiner.add("ldc.i4 0");
        joiner.add("ceq");
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
        validaSeBooleano(tipo); 
        pilha.push("bool");
        joiner.add("ldc.i4.1");
        joiner.add("xor");
    }

    private void validaSeBooleano(String tipo) throws SemanticError {
        if (!tipo.equals("bool")) {
            throw new SemanticError("ipo(s) incompatível(is) em expressão lógica");
        }
    }

    private void acionaToken14() {
        String tipoWrite = pilha.pop();
        if (tipoWrite.equals("int64")) {
            joiner.add("conv.i8");
        }
        if (tipoWrite.equals("char")) {
            joiner.add("string");
        }
        joiner.add("call void [mscorlib]System.Console::Write(" + tipoWrite + ")");
    }

    private void acionaToken15() {
        joiner.add(".assembly extern mscorlib {}");
        joiner.add(".assembly _codigo_objeto{}");
        joiner.add(".module _codigo_objeto.exe");
        joiner.add("");
        joiner.add(".class public _UNICA{");
        joiner.add(".method static public void _principal() {");
        joiner.add(".entrypoint");
    }

    private void acionaToken16() throws IOException {
        joiner.add("ret");
        joiner.add("}");
        joiner.add("}");

        pathToCompile = pathToCompile.replace(".txt", ".il");
        
        FileWriter writer = new FileWriter(pathToCompile, StandardCharsets.UTF_8, false);
        writer.write(joiner.toString());
        writer.close();
        joiner = new StringJoiner("\n");
    }

    private void acionaToken17() {
        joiner.add("ldstr " + "\"\\n\"");
        joiner.add("call void [mscorlib]System.Console::Write(string)");
    }

    private void acionaToken18() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        validaTipoLogico(tipo1, tipo2);
        pilha.push("bool");
        joiner.add("and");
    }

    private void acionaToken19() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        validaTipoLogico(tipo1, tipo2);
        pilha.push("bool");
        joiner.add("or");
    }

    private void validaTipoLogico(String tipo1, String tipo2) throws SemanticError {
        if (!(tipo1.equals(tipo2) && tipo1.equals("bool") && tipo1.equals("bool"))) {
            throw new SemanticError("tipo(s) incompatível(is) em expressão lógica");
        }
    }

    private void acionaToken20() throws SemanticError {
        String tipo1 = pilha.pop();
        String tipo2 = pilha.pop();
        if (!(tipo1.equals(tipo2))) {
            throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética");
        }
        addAritmetico(tipo1, tipo2);
        // if (tipo2.equals("float64")) {
        //     pilha.push("float64");
        // } else {
        //     pilha.push("int64");
        // }
        joiner.add("div");
        joiner.add("dup");
        joiner.add("mul");
        joiner.add("sub");
    }

    private void acionaToken21(Token token) {
        pilha.push("string");
        joiner.add("ldstr " + converteTokenString(token));
    }

    private String converteTokenString(Token token) {
        return token.getLexeme().replace("\\s", " ");
    }

    private void acionaToken22(Token token) {
        pilha.push("string");
        joiner.add("ldstr " + converteTokenString(token));
    }

    private void acionaToken24() {
        numeroRotulo++;
        joiner.add("brfalse nr_" + numeroRotulo);
        pilhaRotulos.push("nr_" + numeroRotulo);
    }

    private void acionaToken25(){
        numeroRotulo++;
        joiner.add("br nr_" + numeroRotulo);
        joiner.add(pilhaRotulos.pop() + ":");
        pilhaRotulos.push("nr_" + numeroRotulo);
    }

    private void acionaToken26(){
        joiner.add(pilhaRotulos.pop() + ":");
    }

    private void acionaToken27() {
        numeroRotulo++;
        joiner.add("nr_" + numeroRotulo + ":");
        pilhaRotulos.push("nr_" + numeroRotulo);
    }

    private void acionaToken28(){
        joiner.add("brtrue " + pilhaRotulos.pop());
    }

    private void acionaToken30(Token token){
        if((token.getLexeme()).equals("int")) {
            tipoVar = "int64";
        }
        if((token.getLexeme()).equals("float")) {
            tipoVar = "float64"; 
        }
    }

    private void acionaToken31(){
        for(String id : listaId){
            tabelaSimbolos.put(id, tipoVar);
            joiner.add(".locals ("+ tipoVar + " " + id +")");
        }
        listaId.clear();
    }

    private void acionaToken32(Token token){
        listaId.add(token.getLexeme());
    }

    private void acionaToken33(Token token){
        String id = token.getLexeme();
        String tipoId = tabelaSimbolos.get(id);
        pilha.push(tipoId);
        joiner.add("ldloc " + id);
        if (tipoId.equals("int64")){
            joiner.add("conv.r8");
        }
    }

    private void acionaToken34(){
        String id = listaId.remove(0);
        String tipoId = tabelaSimbolos.get(id);
        String tipoExp = pilha.pop(); 
        if(tipoId.equals("int64")){
            joiner.add("conv.i8");
        }
        joiner.add("stloc " + id);
    }

    private void acionaToken35(){
        String classe = "";
        for(String id : listaId){
            String tipoId = tabelaSimbolos.get(id);
            if(tipoId.equals("int64")){
                classe = "Int64";
            }
            if(tipoId.equals("float64")){
                classe = "Double";
            }
            joiner.add("call string [mscorlib]System.Console::ReadLine()");
            joiner.add("call " + tipoId + " [mscorlib]System." + classe + "::Parse(string)");
            joiner.add("stloc " + id);
        }
        listaId.clear();
    }

    public void setPath(String pathToCompile) {
        this.pathToCompile = pathToCompile;
    }
}
