package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.Card;
import com.github.zipcodewilmington.casino.games.DeckOfCards;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;

public class WarGame implements GameInterface {
    private IOConsole console = new IOConsole();
    ArrayList<Card> playerSelectedCard = new ArrayList<>(1);
    ArrayList<Card> dealerSelectedCard = new ArrayList<>(1);
    Integer consoleInput = 0;
    Integer dealChoiceIndex = 0;
    String yellow = "\033[0;33m";
    String green = "\033[0;32m";
    String reset = "\033[0m";
    Integer playerCardCount = 0;
    Integer dealerCardCount = 0;
    String playerHandVis = "";
    String selectCardVis = "";
    ArrayList<Card> playerHand = new ArrayList<>(5);
    ArrayList<Card> dealerHand = new ArrayList<>(5);
    ArrayList<Card> playerPile = new ArrayList<>(52);
    ArrayList<Card> dealerPile = new ArrayList<>(52);
    private static DeckOfCards deck1 = new DeckOfCards(false);
    private static DeckOfCards deck2 = new DeckOfCards(deck1);


    String rules = green + "Each player at the start draws 5 cards into their hand.\n" +
            "On each turn each player selects a card from their hand to play against each other.\n\n" + yellow +
            "The highest card wins both cards.  If both cards are the same rank you go to war.\n" +
            "The rank of card determines the amount of cards that will be enlisted in the war.\n\n" + green +
            "At this point each player can place their bets. Each player draws an additional card into their hands.\n" +
            "The highest rank card wins the card pot and the money pot.\n" +
            "A player wins once they have all of the cards\n\n\n\n\n\n\n\n\n"+reset;

    public void playWar() {
        WarGame game = new WarGame();
        game.printRules();
        game.startGame();

    }

    public void printRules() {
        System.out.println(rules);
    }

    public void startGame() {
        deck1.splitDeck(deck1, deck2);
        playerDrawToHand(deck1, playerHand);
        playerDrawToHand(deck2, dealerHand);
        showPlayerHand();
        playHand();
        dealHand();
        showSelections();
        compareCards();


    }


    public void compareCards(){
        if(playerSelectedCard.get(0).getValue() > dealerSelectedCard.get(0).getValue()){
            System.out.println("Your card wins");
            playerPile.add(playerSelectedCard.get(0));
            playerPile.add(dealerSelectedCard.get(0));
            playerSelectedCard.removeAll(playerSelectedCard);
            dealerSelectedCard.removeAll(dealerSelectedCard);
        }
        else if (playerSelectedCard.get(0).getValue() < dealerSelectedCard.get(0).getValue()){
            System.out.println("Your card loses");
            dealerPile.add(playerSelectedCard.get(0));
            dealerPile.add(dealerSelectedCard.get(0));
            playerSelectedCard.removeAll(playerSelectedCard);
            dealerSelectedCard.removeAll(dealerSelectedCard);
        }
        else{
            System.out.println("You look at the dealer's card and see they played the same card.\n You realize\"Maybe we aren't that different\"\n");
            System.out.println("The both of you go off and learn to program the 'War' section for your 'War' game\nsomething that seems...kinda necessary...essential even in a game about war.");
        }

    }

    public void playHand(){
        System.out.println();
        consoleInput = console.getIntegerInput("Type number to select card.");
        switch (consoleInput) {
            case 1:
                playerSelectedCard.add(playerHand.get(0));
                playerHand.remove(0);
                break;
            case 2:
                playerSelectedCard.add(playerHand.get(1));
                playerHand.remove(1);
                break;
            case 3:
                playerSelectedCard.add(playerHand.get(2));
                playerHand.remove(2);
                break;
            case 4:
                playerSelectedCard.add(playerHand.get(3));
                playerHand.remove(3);
                break;
            case 5:
                playerSelectedCard.add(playerHand.get(4));
                playerHand.remove(4);
                break;
        }
    }

    public void dealHand(){
        switch (6/2) {
            case 1:
                dealerSelectedCard.add(dealerHand.get(0));
                dealerHand.remove(0);
                break;
            case 2:
                dealerSelectedCard.add(dealerHand.get(1));
                dealerHand.remove(1);
                break;
            case 3:
                dealerSelectedCard.add(dealerHand.get(2));
                dealerHand.remove(2);
                break;
            case 4:
                dealerSelectedCard.add(dealerHand.get(3));
                dealerHand.remove(3);
                break;
            case 5:
                dealerSelectedCard.add(dealerHand.get(4));
                dealerHand.remove(4);
                break;
        }
    }

    public void dealChoice(){
        dealChoiceIndex = 2;
    }
    public void showSelections(){
        System.out.println("          "+dealerSelectedCard.get(0)+"          "+"\n\n\n\n");
        System.out.println("          "+playerSelectedCard.get(0)+"          "+"\n\n\n\n");
    }

    public void showPlayerHand(){
        playerHandVis = "";
        selectCardVis = "";
        playerCardCount = deck1.size() + playerHand.size() + playerPile.size();
        dealerCardCount = deck2.size() + dealerHand.size() + dealerPile.size();
        for (int i = 0; i < playerHand.size(); i++) {
            playerHandVis += playerHand.get(i);
        }
        System.out.println(playerHandVis + "        Player Card Count:" + playerCardCount+ "\n");
        System.out.println("|      Select Card      |\n");
        for (int i = 1; i < playerHand.size()+1; i++) {
            selectCardVis += "| "+i+" |";
        }
        System.out.println(selectCardVis + "        Dealer Card Count:" + dealerCardCount+ "\n");
    }

    public ArrayList<Card> playerDrawToHand(DeckOfCards deck, ArrayList<Card> c) {
        if (deck.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                     deck.draw();
                     c.add(DeckOfCards.drawnCard);
                }
            }
        else{
            for (int i = 0; i < deck.size(); i++) {
                deck.draw();
                c.add(DeckOfCards.drawnCard);
            }
        }
        return c;
    }

        @Override
        public void add (PlayerInterface player){

        }

        @Override
        public void remove (PlayerInterface player){

        }

        @Override
        public void run () {
            playWar();

        }
    }
