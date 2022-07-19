package com.github.zipcodewilmington.casino.games.connectfour;


public class Token {
    private String symbol;

    Token() {
        this.symbol = "R";
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

//    Token() {
//        this.symbol = 'O';
//        this.ansiColor = AnsiColor.YELLOW;
//    }
//    Token() {
//    }
//    Token(AnsiColor color, Character ch) {
//    }

//    Token(Character ch) {
//        this.symbol = ch;
//        System.out.print(ANSI_RED+Character.toUpperCase(symbol));
//    }


//    @Override
//    public String toString() {
//        return color+String.valueOf(symbol);
//    }
}
