package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.MainApplication;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.Card;
import com.github.zipcodewilmington.casino.games.DeckOfCards;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;



public class BlackJackGame implements GameInterface {
    DeckOfCards deck = new DeckOfCards(true);
    int playerValue = 0;

    int dealerValue = 0;

    int playerChips = 500;

    int playerBet = 0;


    ArrayList<Card> playHand = new ArrayList<>();
    ArrayList<Card> dealHand = new ArrayList<>();
    Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Welcome to Bobby's Brutal Blackjack. Name's Bobby, nice to meet cha.");
        BlackJackGame bj = new BlackJackGame();
        bj.playGame();
    }


    void playGame() {
        Scanner scan = new Scanner(System.in);
        deck.shuffle();
        System.out.println("Let's jack it up!\n");
        showPlayerBet();
        System.out.println("For my first card, I draw...");
        showDealerDraw1();
        System.out.println("    ");
        System.out.println("Right now you have " + playerValue + ". Hit enter to draw for yourself.");
        scan.nextLine();
        showPlayerDraw();
        if (playerBet != 0) {
            doubleDownEarly();
        }
        System.out.println("  ");
        System.out.println("And for my next card...");
        showDealerDraw2();
        if (dealerValue == 21) {
            dealerWinsEarly();
        } else {
            System.out.println(" ");
            System.out.println("I'm gonna hold at " + dealerValue + ". See if you can beat that!\n");
            System.out.println(playHand);
        }
        while (playerValue < 21 && playerValue <= dealerValue) {
            if ((playerChips - playerBet) > playerBet && playerBet != 0) {
                System.out.println("Now you got " + playerValue + ". Type 'dd' to double down, or hit enter to draw");
                String respond = scan.nextLine();
                if (respond.equals("dd")) {
                    doubleDown();
                } else {
                    showPlayerDraw();
                }
            } else {
                playerRecap();
                scan.nextLine();
                showPlayerDraw();
            }
        }


        if (playerValue <= 21 && playerValue > dealerValue) {
            playerWins();
        } else if (playerValue > 21) {
            dealerWins();
        }

    }



    public int showDealerDraw1() {
        if (deck.get(0).getValue() < 9 || deck.get(0).getValue() > 10) {
            deck.shuffle();
            showDealerDraw1();
        } else {
            dealerValue += deck.get(0).getValue();
            dealHand.add((deck.get(0)));
            deck.draw();
            System.out.println((dealHand) + "     " + (dealerValue) + "!" );

        }
        return dealerValue;
    }


    public int showDealerDraw2() {
        if (deck.get(0).getValue() < 6) {
            deck.shuffle();
            showDealerDraw2();
        } else {
            dealerValue += deck.get(0).getValue();
            dealHand.add((deck.get(0)));
            deck.draw();
            System.out.println((dealHand) + "     " + (dealerValue) + "!" );
        }
        return dealerValue;
    }

    public int showPlayerDraw() {
        deck.shuffle();
        if (playHand.contains(deck.get(0).getFaceName())) {
            showPlayerDraw();
        } else if(deck.get(0).getValue() ==11) {
        playHand.add((deck.get(0)));
        deck.draw();
        System.out.println(playHand);
        aces();
    }else{
        playerValue += deck.get(0).getValue();
        playHand.add((deck.get(0)));
        deck.draw();
        System.out.println(playHand);
        }
        return playerValue;
    }


    public void playerRecap(){
        System.out.println("Now you've got " + playerValue + ". Hit enter to draw.");
    }

    private int showPlayerBet(){
        Scanner scan = new Scanner(System.in);
        System.out.println("You've got " + playerChips + " chips. How many ya bettin'?");
        try {
            playerBet = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That ain't a number I ever heard of.");
            showPlayerBet();
        }
        if (playerBet > playerChips){
            System.out.println("That bet is too big for your britches. Try a smaller amount.");
            showPlayerBet();
        }else if (playerBet < 0) {
            System.out.println("Hey, don't try it with the funny business!");
            showPlayerBet();
        }
        return playerBet;
    }

    private int aces() {
        int nextDraw = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Do ya want this Ace to be a 1 or an 11?");
        try {nextDraw = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That ain't a number I ever heard of.");
            aces();
        }
        if (nextDraw == 1 || nextDraw == 11) {
            playerValue += nextDraw;
            System.out.println("Ya got " + nextDraw + "!");
        }else{
            System.out.println("Nice try...");
            aces();
        }
        return playerValue;
    }

    public void playerWins(){
        Scanner scan = new Scanner(System.in);
        playerChips += playerBet;
        System.out.println("Woah! You got " + playerValue + " and won " + playerBet + " chips! You now have " + playerChips + " chips! \n"
                +"Hit enter to play again or type 'Q' to get outta here.");
        playerValue = 0;
        dealerValue = 0;
        deck.addAllCard(playHand);
        deck.addAllCard(dealHand);
        playHand.clear();
        dealHand.clear();
        String respond = scan.nextLine();
        if (respond.equalsIgnoreCase("Q")) {
            Casino c = new Casino();
            c.run();
        } else {
            playGame();
        }
    }

    public void dealerWins() {
        Scanner scan = new Scanner(System.in);
        playerChips -= playerBet;
        System.out.println(playerValue + "! Welp, them's the breaks, kid. You lost " + playerBet + " chips and now have " + playerChips + " chips.\n"
                + "Hit enter to play again or type 'Q' to get outta here.");
        playerValue = 0;
        dealerValue = 0;
        deck.addAllCard(playHand);
        deck.addAllCard(dealHand);
        playHand.clear();
        dealHand.clear();
        String respond = scan.nextLine();
        if (respond.equalsIgnoreCase("Q")) {
            Casino c = new Casino();
            c.run();
        } else {
            playGame();
        }
    }

    public void dealerWinsEarly(){
        Scanner scan = new Scanner(System.in);
        playerChips-=playerBet;
        System.out.println("Welp, them's the breaks, kid. You lost " + playerBet + " chips and now have " + playerChips + " chips.\n"
                + "Hit enter to play again or type 'Q' to get outta here.");
        playerValue = 0;
        dealerValue = 0;
        deck.addAllCard(playHand);
        deck.addAllCard(dealHand);
        playHand.clear();
        dealHand.clear();
        String respond = scan.nextLine();
        if (respond.equalsIgnoreCase("Q")) {
            Casino c = new Casino();
            c.run();
        } else {
            playGame();
        }
    }

    public int doubleDown(){
        playerBet = playerBet * 2;
        System.out.println("OK kid, this next one is for all da marbles! Let's see what cha got!");
        scan.nextLine();
        showPlayerDraw();
        if (playerValue <= 21 && playerValue > dealerValue){
            playerWins();
        } else{
            dealerWins();
        }
        return playerBet;
    }
    public int doubleDownEarly(){
        System.out.println("Right now you got " + playerValue + ". Type 'dd' to double down now, or hit enter to see the dealer's card first.");
        String respond = scan.nextLine();
        if (respond.equals("dd")) {
            doubleDownEarlyResults();
        } else {

        }
        return playerBet;
    }

    public int doubleDownEarlyResults(){
        playerBet = playerBet * 2;
        System.out.println("OK kid, this next one is for all da marbles! Let's see what cha got!");
        scan.nextLine();
        System.out.println("You got:");
        showPlayerDraw();
        System.out.println(playerValue + "!\n");
        System.out.println("And I got:");
        showDealerDraw2();
        System.out.println(dealerValue + "!");
        if (playerValue <= 21 && playerValue > dealerValue){
            playerWins();
        } else{
            dealerWinsEarly();
        }
        return playerBet;
    }


    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        playGame();

    }
}