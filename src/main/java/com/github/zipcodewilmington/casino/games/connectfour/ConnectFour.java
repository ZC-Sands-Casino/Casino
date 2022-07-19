package com.github.zipcodewilmington.casino.games.connectfour;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.*;

import static com.github.zipcodewilmington.casino.games.connectfour.Board.*;


public class ConnectFour implements GameInterface {
    Board gameBoard;
    IOConsole con = new IOConsole(AnsiColor.RED);
    Token rToken = new Token();
    Token bToken = new Token();
    String playerRTitle = "R";

//    String playerR = rToken.getSymbol();
//    String playerB = bToken.getSymbol();
//    String player = rToken.getSymbol();
    String playerR;
    String playerB;
    String player;



    public static void main(String[] args) {
        ConnectFour cf = new ConnectFour();
        cf.run();
    }

    public void playConnectFour() {
/*
        List<Character[][]> playerR = new ArrayList<>();
        List<Character[][]> playerB = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
*/
        ConnectFourPlayer cfPlayer = new ConnectFourPlayer();
        int rounds = 1;
//        Character playerR = 'R';
//        Character playerB = 'B';
        rToken.setSymbol("\033[1;31m"+"R");
        playerR = rToken.getSymbol();
        bToken.setSymbol("\033[1;30m"+"B");
        playerB = bToken.getSymbol();
        player = playerR;


        boolean allowedPlacement;

        gameBoard = new Board(board);
        gameBoard.createBoard();

//        rToken.setSymbol("\033[1;31m"+"R");
//        bToken.setSymbol("\033[1;30m"+"B");
//        System.out.println(rToken.getSymbol());
//        System.out.println(bToken.getSymbol());

        displayGameBoard();
        while (rounds <=21) {
            for (int i=1; i <=2; i++) {
                placeToken(getUserInput());
                displayGameBoard();
                switchTurn();
                getTurn();


                //TODO revisit if switch case statement possible
                if (columnConsecutive4() == true) {
                    System.out.println("GAME OVER...");
                    switchTurn();
                    System.out.println("Player"+player+"\033[1;93m"+" wins!".toUpperCase());

                    System.out.println("Enter 'q' to exit game");
                    Scanner sc = new Scanner(System.in);
                    String quitResponse = sc.nextLine();
                    if (quitResponse.equals("q")) {
                        Casino cas = new Casino();
                        cas.run();
                    }

                }
                else if (rowConsecutive4() == true) {
                    System.out.println("GAME OVER...");
                    switchTurn();
                    System.out.println("Player"+player+"\033[1;93m"+"\033[1;93m wins!".toUpperCase());

                    System.out.println("Enter 'q' to exit game");
                    Scanner sc = new Scanner(System.in);
                    String quitResponse = sc.nextLine();
                    if (quitResponse.equals("q")) {
                        Casino cas = new Casino();
                        cas.run();
                    }
                }
                //TODO needs diagonal win detection
            }
            rounds++;
        }
    }

    int getUserInput() {
        int position;
        Scanner sc = new Scanner(System.in);
        System.out.println("\033[0m\nPlayer" + player + "\033[0m's Turn");
        System.out.print("\033[0mEnter a column number to place your token: ");

        position = sc.nextInt();
        return position;
    }

    void placeToken(int columnChoice) {
        //starting row at very bottom
        int r = 6;
        String characterAtPosition = getPlayerPosition(r, columnChoice * 2 - 1);
        ;

        // while board has no winner
        if (isPositionValid(board) == true) {
            //check if position is empty aka contains 'O' character
            if (characterAtPosition.equals("O")) {
//                System.out.println("Index:\t" + r + ", " + columnChoice);
                //print out character at empty position...which would return 'O'
//                System.out.println("Character: " + getPlayerPosition(r, columnChoice * 2 - 1));
                gameBoard.setRow(r);
                gameBoard.setCol(columnChoice *2 -1);


                //update that position with player's token
                board[r][columnChoice * 2 - 1] = player;
            } else {
                //if position is not empty aka returns anything other than 'O'
//                System.out.println("Position not available...");

                //decrement row by 1 aka move up 1 position in column
                //until no longer able aka top row is reached
                for (int newR = r - 1; newR >= 1; newR--) {
                    //check if new column position is empty
                    if (board[newR][columnChoice * 2 - 1].equals("O")) {
//                        System.out.println("Index:\t" + newR + ", " + columnChoice);
                        gameBoard.setRow(newR);
                        gameBoard.setCol(columnChoice *2 -1);

                        board[newR][columnChoice * 2 - 1] = player;
                        //break out of loop & try again
                        //or just break out
                        break;
                    }
                }
            }
        }
    }

    void switchTurn() {
        if (player == playerR)
            player = playerB;
        else
            player = playerR;
    }

    String getTurn() {
        if (player == playerR)
            return playerR;
        else
            return playerB;
    }

    boolean isPositionValid(String[][] board) {
        boolean isEmpty = true;

        for (int r = 6; r >= 1; r--) {
            for (int c = 1; c <= 13; c += 2) {
                if (board[r][c].equals("O")) {
                    isEmpty = true;
                }
//                else if (board[r][c].equals('R') || board[r][c].equals('B')) {
                else {
                    isEmpty = false;
                }
            }
        }
        return isEmpty;

    }

    String getPlayerPosition(int row, int col) {
        return board[row][col];
    }

    boolean columnConsecutive4() {
        int row = gameBoard.getRow();
        int col = gameBoard.getCol();
        int count = 0;

        while (row < 6 && count < 3 && gameBoard.getPosition(row, col) == gameBoard.getPosition(row + 1, col)) {
            count++;
            row++;
        }

        if (count == 3) {
            return true;
        } else {
            return false;
        }
    }

    boolean rowConsecutive4() {
        int row = gameBoard.getRow();
        int col = gameBoard.getCol();
        int count = 0;

        if (col < 13) {
            while (col < 13 && count < 3 &&
                    gameBoard.getPosition(row, col) == gameBoard.getPosition(row, col +2)) {
                count++;
                col+=2;
            }
        }
        else if (col >2) {
            row = gameBoard.getRow();
            col = gameBoard.getCol();
            while (col > 2 && count < 3 &&
                    gameBoard.getPosition(row, col) == gameBoard.getPosition(row, col -2)) {
                count++;
                col-=2;
            }
        }
//        while (col < 16 && count < 3 &&
//                gameBoard.getPosition(row, col) == gameBoard.getPosition(row, col +2)) {
//            count++;
//            col+=2;
//
//            if (col==13) {
//                gameBoard.setCol(col-2);
//            }
//        }

        if (count == 3) {
            return true;
        } else {
            return false;
        }
    }




/*
        displayGameBoard();
        System.out.println("");

        if (winner) {
            if (player == 'R') {
                System.out.println("Black won!");
            } else {
                System.out.println("Red won!");
            }
            System.out.println("No winners, tied game");
        }
*/


/*
        ConnectFour(ConnectFourPlayer player) {
            this.player = cfPlayer;
        }

        ConnectFour() {
        }

    static void placeUserPosition(Character[][] board, String user) {
        char symbol = 'X';
        boolean allowedPlacement = true;
        int roundCounter = 1;

        while (true) { //replace w try, catch block
            System.out.println("Round " +roundCounter +"\n"+
                    "Enter a position to place your token: ");
            cfPlayer.setPositionPlacement(sc.nextInt()); // setting player position based on user input
            int pos = cfPlayer.getPositionPlacement(); //return player's position

            allowedPlacement = checkPlacement(pos, board);
            userToken.setSymbol(symbol);
            Character tok = userToken.getSymbol();

            switch (pos) {
                //y then x
                case 1:
                    board[6][1] = symbol;//column1
                    userPositions.add(pos);
//                    indexTracker.put(board, emptyPosition);
                    break;
                case 2:
                    board[6][3] = tok; //column2
                    break;
                case 3:
                    board[6][5] = symbol; //column3
                    break;
                case 4:
                    board[6][7] = symbol; //column4
                    break;
                case 5:
                    board[6][9] = symbol; //column5
                    break;
                case 6:
                    board[6][11] = symbol; //column6
                    break;
                case 7:
                    board[6][13] = symbol; //column7
                    break;
                default:
                    System.out.print("ERROR: invalid number");
                    break;
            }
            roundCounter++;
            System.out.println("\n\n");

            System.out.print(userPositions);

            displayGameBoard();

        }

//        while (winner==false) {
//
//            if (position>7 || position<0) {
//                System.out.print("ERROR: invalid number");
//            }
//            else {
//                board[6][position] = symbol;
//            }
//        }


    }
*/


    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        playConnectFour();
    }
}
