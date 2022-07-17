package com.github.zipcodewilmington.casino.games.connectfour;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.*;

import static com.github.zipcodewilmington.casino.games.connectfour.Board.*;


public class ConnectFour implements GameInterface {
    Board gameBoard;
    Character playerR = 'R';
    Character playerB = 'B';
    Character player = playerR;


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


        boolean allowedPlacement;

        gameBoard = new Board(board);
        gameBoard.createBoard();

        displayGameBoard();
        while (rounds <=21) {
            for (int i=1; i <=2; i++) {
                placeToken(getUserInput());
                displayGameBoard();
//                System.out.println("");
                switchTurn();
                getTurn();
            }
            rounds++;

        }
    }

    int getUserInput() {
        int position;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPlayer" + player + "'s Turn");
        System.out.print("Enter a column number to place your token: ");

        position = sc.nextInt();
        return position;
    }

    void placeToken(int columnChoice) {
        int r = 6;
        Character characterAtPosition = getPlayerPosition(r, columnChoice * 2 - 1);
        ;

        // while board has no winner
        if (isPositionValid(board) == true) {
            //check if position is empty aka contains 'O' character
            if (characterAtPosition.equals('O')) {
                System.out.println("Index:\t" + r + ", " + columnChoice);
                //print out character at empty position...which would return 'O'
                System.out.println("Character: " + getPlayerPosition(r, columnChoice * 2 - 1));

                //update that position with player's token
                board[r][columnChoice * 2 - 1] = player;
            } else {
                //if position is not empty aka returns anything other than 'O'
                System.out.println("Position not available...");

                //decrement row by 1 aka move up 1 position in column
                //until no longer able aka top row is reached
                for (int newR = r - 1; newR >= 1; newR--) {
                    //check if new column position is empty
                    if (board[newR][columnChoice * 2 - 1].equals('O')) {
                        System.out.println("Index:\t" + newR + ", " + columnChoice);

                        board[newR][columnChoice * 2 - 1] = player;
                        //break out of loop & try again
                        //or just break out
                        break;
                    }
                }
            }

/*
            if (characterAtPosition.equals('O')) {
                board[r][columnChoice*2 -1] = player;
            }
            else {
                System.out.println("Position not available...");

                for (int newR=r-1; newR>=1; newR--) {
                    if (board[newR][columnChoice *2 -1].equals('O')) {
                        board[newR][columnChoice *2 -1] = player;
                        break;
                    }
//                    else {
//                        System.out.println("Column filled!");
//                    }
                }
            }
*/
//            switchTurn();
//            for (int row=r; row<=6; row=row) {
//                for (int col=columnChoice; col <=13; col=col) {
//                    board[row][col] = 'R';
//                }
//            }
        }
//        System.out.println("");

    }

    void switchTurn() {
        if (player == playerR)
            player = playerB;
        else
            player = playerR;
    }

    Character getTurn() {
        if (player == playerR)
            return playerR;
        else
            return playerB;
    }

    boolean isPositionValid(Character[][] board) {
        boolean isEmpty = true;

        for (int r = 6; r >= 1; r--) {
            for (int c = 1; c <= 13; c += 2) {
                if (board[r][c].equals('O')) {
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

    int getCol() {

        return 0;
    }

    Character getPlayerPosition(int row, int col) {
        return board[row][col];
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
