package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.Player;

public class WarPlayer extends Player implements PlayerInterface {

    Player player;
    CasinoAccount casinoAccount;

    public WarPlayer(Player player, CasinoAccount casinoAccount){
        this.player = player;
        this.casinoAccount = casinoAccount;
    }

    public WarPlayer(){

    }

    public Player getPlayer(){
        return this.player;
    }

    @Override
    public CasinoAccount getCasinoAccount() {
        return casinoAccount;
    }
}
