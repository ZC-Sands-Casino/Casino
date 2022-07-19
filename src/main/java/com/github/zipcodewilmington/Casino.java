package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.blackjack.BlackJackGame;
import com.github.zipcodewilmington.casino.blackjack.BlackJackPlayer;
import com.github.zipcodewilmington.casino.games.chuckaluck.ChuckALuckGame;
import com.github.zipcodewilmington.casino.games.chuckaluck.ChuckALuckPlayer;
import com.github.zipcodewilmington.casino.games.connectfour.ConnectFour;
import com.github.zipcodewilmington.casino.games.connectfour.ConnectFourPlayer;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.casino.games.war.WarGame;
import com.github.zipcodewilmington.casino.games.war.WarPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by leon on 7/21/2020.
 */
public class Casino implements Runnable {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);

    @Override
    public void run() {
        String arcadeDashBoardInput;
        CasinoAccountManager casinoAccountManager = new CasinoAccountManager();
        slowPrint(intro());
        System.out.println("\n " +
                " /$$$$$$                            /$$              \n" +
                " /$$__  $$                          | $$              \n" +
                "| $$  \\__/  /$$$$$$  /$$$$$$$   /$$$$$$$  /$$$$$$$    \n" +
                "|  $$$$$$  |____  $$| $$__  $$ /$$__  $$ /$$_____/    \n" +
                " \\____  $$  /$$$$$$$| $$  \\ $$| $$  | $$|  $$$$$$     \n" +
                " /$$  \\ $$ /$$__  $$| $$  | $$| $$  | $$ \\____  $$    \n" +
                "|  $$$$$$/|  $$$$$$$| $$  | $$|  $$$$$$$ /$$$$$$$/    \n" +
                " \\______/  \\_______/|__/  |__/ \\_______/|_______/     \n" +
                "                                                      \n" +
                "                                                      \n" +
                "                                                      \n" +
                "  /$$$$$$                      /$$                    \n" +
                " /$$__  $$                    |__/                    \n" +
                "| $$  \\__/  /$$$$$$   /$$$$$$$ /$$ /$$$$$$$   /$$$$$$ \n" +
                "| $$       |____  $$ /$$_____/| $$| $$__  $$ /$$__  $$\n" +
                "| $$        /$$$$$$$|  $$$$$$ | $$| $$  \\ $$| $$  \\ $$\n" +
                "| $$    $$ /$$__  $$ \\____  $$| $$| $$  | $$| $$  | $$\n" +
                "|  $$$$$$/|  $$$$$$$ /$$$$$$$/| $$| $$  | $$|  $$$$$$/\n" +
                " \\______/  \\_______/|_______/ |__/|__/  |__/ \\______/ \n" +
                "                                                      \n" +
                "                                                      \n" +
                "                                                      " );
        slowPrint(intro2());
        try{
        do {
            arcadeDashBoardInput = getArcadeDashboardInput();

            if ("2".equals(arcadeDashBoardInput)) { //log-in

                    String accountName = console.getStringInput("Enter your account name:");
                    String accountPassword = console.getStringInput("Enter your account password:");
                    CasinoAccount casinoAccount = casinoAccountManager.getAccount(accountName, accountPassword);
                    boolean isValidLogin = casinoAccount != null;
                    if (isValidLogin) {
                        displayGameMenu();


                    } else {
                        // TODO - implement better exception handling
                        String errorMessage = "No account found with name of [ %s ] and password of [ %s ]";
                        throw new RuntimeException(String.format(errorMessage, accountName, accountPassword));
                    }



        } else if ("1".equals(arcadeDashBoardInput)) { //create-account
            console.println("Welcome to the account-creation screen.");
            String accountName = console.getStringInput("Enter your account name:");
            String accountPassword = console.getStringInput("Enter your account password:");
            CasinoAccount newAccount = casinoAccountManager.createAccount(accountName, accountPassword);
            casinoAccountManager.registerAccount(newAccount);
            displayGameMenu();
        }

    } while(!"logout".equals(arcadeDashBoardInput));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
}

    private void displayGameMenu(){
        String gameSelectionInput = getGameSelectionInput().toUpperCase();
        if (gameSelectionInput.equals("1")) { //slots
            play(new SlotsGame(), new SlotsPlayer());
//                    } else if (gameSelectionInput.equals("2")) { //cee-lo
//                        play(new CeeloGame(), new CeeloPlayer());
                    } else if (gameSelectionInput.equals("3")) { //chuck a luck
                        play(new ChuckALuckGame(), new ChuckALuckPlayer());
        } else if (gameSelectionInput.equals("4")) { //connect four
            play(new ConnectFour(), new ConnectFourPlayer());
//                    } else if (gameSelectionInput.equals("5")) { //blackjack
//                        play(new BlackJackGame(), new BlackJackPlayer());
                    }else if (gameSelectionInput.equals("6")) { //war
                        play(new WarGame(), new WarPlayer());
        } else {
            // TODO - implement better exception handling
            String errorMessage = "[ %s ] is an invalid game selection";
            throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
        }
    }


    private String getArcadeDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Arcade Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ 1. create-account ], [ 2. log-in ]")
                .toString());
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ 1. SLOTS ], [ 2. CEE-LO ], [ 3. CHUCK A LUCK], [ 4. CONNECT 4 ], [ 5. BLACKJACK ], [ 6. WAR ]")
                .toString());
    }

    private void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface) gameObject;
        PlayerInterface player = (PlayerInterface) playerObject;
        game.add(player);
        game.run();
    }

    public String intro(){
        return "\n\nWelcome to the \n ";
    }

    public String intro2(){
        return "Like sands through the hourglass, your coinage will flow into our pockets.  \n"+
                "But if it's your lucky day, you may be one of the few to win the whole jackpot. \n"+
                "Are you feeling lucky?\n\n";
    }

    public static void slowPrint(String output) {
        for (int i = 0; i < output.length(); i++) {
            char c = output.charAt(i);
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(90);
            } catch (Exception e) {
            }
        }
    }
}
