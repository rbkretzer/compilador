package com.equipeglr;

public enum Classe {
    IDENTIFICADOR("Identificador"),
    INT("Constante int"),
    FLOAT("Constante float"),
    CHAR("Constante char"),
    STRING("Constante string"),
    BOOLEAN("Palavra reservada"),
    BREAK("Palavra reservada"),
    PALAVRA_CHAR ("Palavra reservada"),
    DO ("Palavra reservada"),
    ELSE ("Palavra reservada"),
    END ("Palavra reservada"),
    FALSE ("Palavra reservada"),
    PALAVRA_FLOAT ("Palavra reservada"),
    FUN ("Palavra reservada"),
    IF ("Palavra reservada"),
    PALAVRA_INT ("Palavra reservada"),
    MAIN ("Palavra reservada"),
    PRINT ("Palavra reservada"),
    PRINTFN ("Palavra reservada"),
    READLN ("Palavra reservada"),
    PALAVRA_STRING ("Palavra reservada"),
    TRUE ("Palavra reservada"),
    VAL ("Palavra reservada"),
    VAR ("Palavra reservada"),
    WHILE ("Palavra reservada"),
    DOIS_PONTOS ("Símbolo especial"), // ":"
    VIRGULA ("Símbolo especial"), // ","
    PONTO_VIRGULA ("Símbolo especial"), // ";"
    ATRIBUICAO ("Símbolo especial"), // ")"="
    ABRE_PARENTESES ("Símbolo especial"), // "("
    FECHA_PARENTESES ("Símbolo especial"), // "")"
    ABRE_CHAVES ("Símbolo especial"), // "{"
    FECHA_CHAVES ("Símbolo especial"), // "}"
    IGUAL ("Símbolo especial"), // ")"")=="
    DIFERENTE ("Símbolo especial"), // "")!="
    MENOR_QUE ("Símbolo especial"), // "<"
    MAIOR_QUE ("Símbolo especial"), // ">"
    MAIS ("Símbolo especial"), // "+"
    MENOS ("Símbolo especial"), // "-"
    MULTIPLICACAO ("Símbolo especial"), // "*"
    DIVISAO ("Símbolo especial"), // "/"
    RESTO ("Símbolo especial"), // "%"
    E_LOGICO ("Símbolo especial"), // "&&"
    OU_LOGICO ("Símbolo especial"), // "||"
    NEGACAO ("Símbolo especial"), // "!"
    SOMA_1 ("Símbolo especial"), // "++"
    DIMINUI_1 ("Símbolo especial"); // "--"


    private String str;

    Classe(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }
}
