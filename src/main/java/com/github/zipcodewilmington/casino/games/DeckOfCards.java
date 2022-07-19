package com.github.zipcodewilmington.casino.games;

import java.util.*;

public class DeckOfCards  {

    public ArrayList<Card> deck;
    public static Card drawnCard;

    public static boolean blackJackTrueWarFalse = true;

    public DeckOfCards(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public static boolean isBlackJackTrueWarFalse() {
        return blackJackTrueWarFalse;
    }

    public static void setBlackJackTrueWarFalse(boolean blackJackTrueWarFalse) {
        DeckOfCards.blackJackTrueWarFalse = blackJackTrueWarFalse;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> shuffle(){
        Collections.shuffle(deck);
        return deck;
    }

    public int size(){
        return deck.size();
    }

    public Card get(int i){
        return deck.get(i);
    }

    public Card draw(){
        drawnCard = deck.get(0);
        deck.remove(0);
        return drawnCard;

    }

    public boolean removeAll() {
        return deck.removeAll(deck);
    }

    public Card addCard(int index, Card c){
        deck.add(index, c);
        return null;
    }


    public  ArrayList<Card> addAllCard(int index,ArrayList<Card> d){
            deck.addAll(d);
        return null;
    }

    public DeckOfCards splitDeck(DeckOfCards a, DeckOfCards b){
        int deckSize = a.size();
        for(int i = deckSize - 1; i > deckSize/2 - 1; i--){
            a.getDeck().remove(i);

        }
        for(int i = deckSize/2 - 1; i >= 0; i--){
            b.getDeck().remove(i);
        }
        return b;
    }

    public DeckOfCards(DeckOfCards a){
        Collections.shuffle(a.getDeck());
        deck = new ArrayList<Card>(a.size());
        for(int i = 0; i < a.size(); i++){
            deck.add(new Card("A", "♠", 14));
            deck.get(i).setFaceName(a.get(i).getFaceName());
            deck.get(i).setSuit(a.get(i).getSuit());
            deck.get(i).setValue(a.get(i).getValue());
        }


    }

    public DeckOfCards(boolean blackJackTrueWarFalse){
        if(blackJackTrueWarFalse == true){blackJackTrueWarFalse = true;}
        else{blackJackTrueWarFalse = false;}
        List<String> suits = Card.getValidSuits();
        List<String> faceNames = Card.getValidFaceNames();
        List<Integer> values = Card.getValidValues();

        deck = new ArrayList<>();
        int i;
        for (String suit: suits){
            i = 0;
            for (String faceName: faceNames){
                deck.add(new Card(faceName, suit, values.get(i)));
//                System.out.println(deck.size()+". "+faceName+" of "+suit+" value:"+values.get(i));
                i++;
            }
        }
    }
}
