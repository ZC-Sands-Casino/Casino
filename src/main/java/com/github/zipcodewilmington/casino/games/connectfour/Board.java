package com.github.zipcodewilmington.casino.games.connectfour;

public class Board {
    public static final String ANSI_YELLOW = "\u001B[33m"; //replace with AnsiColor enums
    private static int length = 15;
    private static int width = 8;
    private int row;
    private int col;
    public static final Character[][] board = new Character[width][length];


    public Board(Character[][] matrix) {
    }



    //creates board, NOT displays
    static void createGameBoard() {
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < length; col++) {
                if (col % 2 == 0) board[row][col] = '|';
                else {
                    board[row][col] = 'O';
                }
                //top row, "lid"
                if (row == 0) board[row][col] = '_';
                else if (row ==7) board[row][col] = '-';
            }
        }
    }


    static void displayGameBoard() {
        //TODO number rows to better user's understanding
        int colNums = 0;
        for (Character[] row : board) {
//            System.out.print(rowNums++);
            for (Character ch : row) {
                System.out.print(ANSI_YELLOW + ch);
                System.out.print("\t");
            }
            System.out.println("");
        }
        System.out.println("\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7");
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Character getPosition(int row, int col) {
        return board[row][col];
    }

    public boolean placeToken(int col, Character tokenColor) {
        int i=width-1;
        this.col = col-1;

        while (i >0 && i<width && board[i][this.col] != 'O') {
            i--;
        }

        if (i<0) {
            return false;
        }
        else {
            board[i][this.col] = tokenColor;
            row = i;
            return true;
        }
    }


    void clear() {
        for (int i=0; i<width; i++) {
            for (int c=0; c<length; c++) {
                board[i][c] = 'O';
            }
        }
    }
}
