package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.Player;

public class BlackJackPlayer extends Player implements PlayerInterface {

    Player player;
    CasinoAccount casinoAccount;

    public BlackJackPlayer(Player player, CasinoAccount casinoAccount){
        this.player = player;
        this.casinoAccount = casinoAccount;
    }

    public BlackJackPlayer(){

    }

    public Player getPlayer(){
        return this.player;
    }

    @Override
    public CasinoAccount getCasinoAccount() {
        return casinoAccount;
    }
}
