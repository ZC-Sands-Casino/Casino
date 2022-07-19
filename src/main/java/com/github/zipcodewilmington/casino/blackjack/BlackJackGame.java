package com.github.zipcodewilmington.casino.blackjack;

import com.github.zipcodewilmington.casino.games.Card;
import com.github.zipcodewilmington.casino.games.DeckOfCards;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;



public class BlackJackGame {
    int playerValue = 0;
    //DeckOfCards.blackJackTrueWarFalse = true;
    DeckOfCards deck = new DeckOfCards(true);
    int dealerValue = 0;

    int playerChips = 500;

    int playerBet = 0;
    ArrayList<Card> playHand = new ArrayList<>();
    ArrayList<Card> dealHand = new ArrayList<>();
    Scanner scan = new Scanner (System.in);





    public static void main(String[] args) {
        System.out.println("Welcome to the Bobby's Brutal Blackjack. Name's Bobby, nice to meet cha.");
        BlackJackGame bj = new BlackJackGame();
//        DeckOfCards deck = new DeckOfCards();
//        deck.shuffle();
//        deck.shuffle();
//        deck.draw();
//        playHand.add(DeckOfCards.drawnCard);
//        System.out.println(playHand.get(0) +  "" +playHand.get(0).getValue());
//        for (int i = 0; i  <deck.size();i++){
//            System.out.println(deck.get(i) + " " + deck.get(i).getValue());
        DeckOfCards deck = new DeckOfCards(true);
        bj.playGame();
    }


    void playGame() {
        Scanner scan = new Scanner(System.in);
        DeckOfCards deck = new DeckOfCards(true);
        deck.shuffle();
        welcomeStatement();
        showPlayerBet();
        System.out.println("And for the first card, the dealer has...");
        showDealerDraw1();
        playerRecap();
        scan.nextLine();
        showPlayerDraw();
        System.out.println("And for the next card, the dealer has...");
        showDealerDraw2();
        if (dealerValue == 21){
            dealerWinsEarly();
        }else {
            dealerRecap2();
        }
        while (playerValue < 21 && playerValue <= dealerValue) {
            if ((playerChips - playerBet) > playerBet && playerBet != 0) {
                System.out.println("Right now you got " + playerValue + ". Type 'dd' to double down, or hit enter to draw");
                String respond = scan.nextLine();
                if (respond.equals("dd")) {
                    doubleDown();
                } else {
                    showPlayerDraw();
                }
            }else{
                playerRecap();
                scan.nextLine();
                showPlayerDraw();
            }
        }


        if (playerValue <= 21 && playerValue > dealerValue){
            playerWins();
        } else if (playerValue > 21){
            dealerWins();
        }

    }



    private void welcomeStatement(){
        System.out.println("Let's jack it up!\n");
    }

    public int showDealerDraw1(){
        DeckOfCards.blackJackTrueWarFalse = true;
        if (deck.get(0).getValue() != 10){
            deck.shuffle();
            showDealerDraw1();
        }else {
            System.out.println(deck.get(0));
            dealerValue += deck.get(0).getValue();
            dealHand.add((deck.get(0)));
            deck.draw();

        }
        return dealerValue;
    }


    public int showDealerDraw2(){
        DeckOfCards.blackJackTrueWarFalse = true;
        if (deck.get(0).getValue() != 6){
            deck.shuffle();
            showDealerDraw2();
        }else {
            dealerValue += deck.get(0).getValue();
            dealHand.add((deck.get(0)));
            deck.draw();
            System.out.println(dealHand);

        }
        return dealerValue;
    }

    public int showPlayerDraw(){
        DeckOfCards.blackJackTrueWarFalse = true;
        deck.shuffle();
        if (deck.get(0).getValue() == 11){
            System.out.println(playHand);
            playHand.add((deck.get(0)));
            deck.draw();
            aces();
        }else {
            playerValue += deck.get(0).getValue();
            playHand.add((deck.get(0)));
            deck.draw();
            System.out.println(playHand);
            System.out.println("\n");
            }
           return playerValue;
        }




    public void dealerRecap1() {
        System.out.println("It looks like the dealer has pulled a " + dealerValue + ".");
    }
    public void dealerRecap2() {
        System.out.println("Looks like the dealer has pulled a " + dealerValue + ". See if you can beat that.");
    }

    public void playerRecap(){
        System.out.println("You've got " + playerValue + ". Hit enter to draw");
    }

    private int showPlayerBet(){
        Scanner scan = new Scanner(System.in);
        System.out.println("How many chips ya bettin'?");
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
        System.out.println("Ooh, an Ace! Choose if you want this Ace to be a 1 or an 11.");
        try {nextDraw = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That ain't a number I ever heard of.");
            aces();
        }
        if (nextDraw == 1 || nextDraw == 11) {
            playerValue += nextDraw;
            System.out.println(playHand);
            System.out.println("Ya drew a " + nextDraw + "!");
        }else{
            System.out.println("Nice try.");
            aces();
        }
        return playerValue;
    }



    public void playerWins(){
        Scanner scan = new Scanner(System.in);
        playerChips += playerBet;
        System.out.println("Woah! You got " + playerValue + " and won " + playerBet + " chips! You now have " + playerChips + " chips! Hit enter to try again.");
        playerValue = 0;
        dealerValue = 0;
        playHand.clear();
        dealHand.clear();
        scan.nextLine();
        playGame();
    }

    public void dealerWins(){
        Scanner scan = new Scanner(System.in);
        playerChips-=playerBet;
        System.out.println(playerValue + "! Welp, them's the breaks, kid. You lost " + playerBet + " chips and now have " + playerChips + " chips. Hit enter to play again.");
        playerValue = 0;
        dealerValue = 0;
        playHand.clear();
        dealHand.clear();
        scan.nextLine();
        playGame();
    }

    public void dealerWinsEarly(){
        Scanner scan = new Scanner(System.in);
        playerChips-=playerBet;
        System.out.println("An easy 21 for me! Welp, them's the breaks, kid. You lost " + playerBet + " chips and now have " + playerChips + " chips. Hit enter to play again.");
        playerValue = 0;
        dealerValue = 0;
        playHand.clear();
        dealHand.clear();
        scan.nextLine();
        playGame();
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

}