package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements GameInterface {

   // public static void main(String[] args){

        public static void playSlotsGame(){

        final String RED_BRIGHT = "\033[0;91m";    // RED
        final String TEXT_RESET = "\u001B[0m";

        Random random = new Random();
        int num1;
        int num2;
        int num3;
        int accountBalance = 500;
        Scanner scan = new Scanner(System.in);
        String playerInput;

            System.out.println(TEXT_RESET + "Welcome to the" + RED_BRIGHT + " Red Cherry " + TEXT_RESET + "slot machine!" + "\n" +
                    "Match any three numbers to win 50 tokens" + "\n" +
                    "Three 7s wins the Jackpot of $100,000!!" + "\n" +
                    "Three" + RED_BRIGHT + " CHERRIES " + TEXT_RESET + "wins 2 tokens \n" + "\n" +
                    "Each pull of the lever costs 5 tokens \n" +
                    "Your account balance is " + accountBalance + "\n" +
                    "Press ENTER to pull lever.  Type 'q' to return to the main menu at any time.");

            try{System.in.read();}
            catch(Exception e){}


        do {

            System.out.println("\n");

            num1 = random.nextInt(10);
            num2 = random.nextInt(10);
            num3 = random.nextInt(10);

            if(Math.random() <= 0.15) {
                System.out.println( RED_BRIGHT + "$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
                        "CHERRY | CHERRY | CHERRY\n" +
                        "$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
                        TEXT_RESET + "\n" +
                        "Plus 2 tokens! \n");
                accountBalance += 2;
            } else {

                System.out.println("$$$$$$$$$$$$$$$\n" +
                        "  " + num1 + " || " + num2 + " || " + num3 + "  \n" +
                        "$$$$$$$$$$$$$$$");

                if (num1 == 7 && num2 == 7 && num3 == 7) {
                    //win jackpot
                    System.out.println("\n" +
                            "Jackpot!! Congratulations!! ;D \n" +
                            "Plus 100,000 tokens!");
                    accountBalance += 100000;

                } else if (num1 == num2 && num1 == num3) {
                    //win tokens
                    System.out.println("\n" +
                            "Winner!!  Plus 50 tokens! \n");
                    accountBalance += 50;
                } else {
                    System.out.println("\n" +
                            "Loser.  Try again! \n");
                    accountBalance -= 5;
                }
            }
            System.out.println("Your account balance is " + accountBalance + "\n" + "Press enter to pull lever.");
            playerInput = scan.nextLine().toLowerCase();
        } while (!Objects.equals(playerInput, "q"));
        if (Objects.equals(playerInput, "q")){
            ;
        }
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        playSlotsGame();

    }
}
