package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.IOConsole;

public class WarGame implements GameInterface {
    private IOConsole console = new IOConsole();

    String rules = "Each player at the start draws 5 cards into their hand.\n" +
            "On each turn each player selects a card from their hand to play against each other.\n" +
            "The highest card wins both cards.  If both cards are the same rank you go to war.\n" +
            "The rank of card determines the amount of cards that will be enlisted in the war.\n" +
            "At this point each player can place their bets. Each player draws an additional card into their hands.\n" +
            "The highest rank card wins the card pot and the money pot.\n" +
            "A player wins once they have all of the cards";

    public void playWar(){
        WarGame game = new WarGame();
        game.printRules();

    }

    public void printRules() {
        System.out.println(rules);
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        playWar();

    }
}
