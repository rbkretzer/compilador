package com.equipeglr;

public enum Classe {
    IDENTIFICADOR("Identificador"),
    INT("Constante int"),
    FLOAT("Constante float"),
    CHAR("Constante char"),
    STRING("Constante string"),
    BOOLEAN("Palavra reservada boolean"),
    BREAK("Palavra reservada break"),
    PALAVRA_CHAR ("Palavra reservada char"),
    DO ("Palavra reservada do"),
    ELSE ("Palavra reservada else"),
    END ("Palavra reservada end"),
    FALSE ("Palavra reservada false"),
    PALAVRA_FLOAT ("Palavra reservada float"),
    FUN ("Palavra reservada fun"),
    IF ("Palavra reservada if"),
    PALAVRA_INT ("Palavra reservada int"),
    MAIN ("Palavra reservada main"),
    PRINT ("Palavra reservada print"),
    PRINTFN ("Palavra reservada"),
    READLN ("Palavra reservada readln"),
    PALAVRA_STRING ("Palavra reservada string"),
    TRUE ("Palavra reservada true"),
    VAL ("Palavra reservada val"),
    VAR ("Palavra reservada var"),
    WHILE ("Palavra reservada while"),
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
