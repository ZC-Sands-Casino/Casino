package com.github.zipcodewilmington.casino.games.connectfour;

public class Board {
    public static final String ANSI_YELLOW = "\033[1;93m"; //replace with AnsiColor enums
    private static int rows = 8;
    private static int cols = 15;


    private int row;
    private int col;
    public static final String[][] board = new String[rows][cols];


    public Board(String[][] matrix) {
    }

    void createBoard() {
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                if (c %2 ==0) {
                    board[r][c] = "|";
                }
                else {
                    board[r][c] = "O";
                }

                if (r==0) {
                    board[r][c] = "_";
                }
                else if (r ==7) board[r][c] = "-";
            }
        }
    }


/*
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
        for (int r=0; r<rows; r++) {
            board[rows] = '-';
            for (int c=0; c<cols; c++) {

            }
        }
    }
*/


    static void displayGameBoard() {
        for (String[] row : board) {
            for (String ch : row) {
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
    public void setRow(int row) {
        this.row = row;
    }


    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }


    public String getPosition(int row, int col) {
        return board[row][col];
    }

/*
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
*/


/*
    void clear() {
        for (int i=0; i<width; i++) {
            for (int c=0; c<length; c++) {
                board[i][c] = 'O';
            }
        }
    }
*/
}
