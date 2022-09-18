package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
    ArrayList<Card> discardList;
    ArrayList<Card> hand;
    Card defaultHand[];
    //whether or not a player passed on the current turn
    boolean skippedCard;
    int number;
    int degrees;
    Deck deck;
    int startx;
    int starty;
    HashMap<String, Integer> map;

    public Player(int a, int d, Deck deck, int x, int y) {
        this.skippedCard = false;
        this.map = new HashMap<String, Integer>();
        this.discardList = new ArrayList<>();
        this.deck = deck;
        this.number = a;
        this.hand = new ArrayList<>();
        this.degrees = d;
        this.startx = x;
        this.starty = y;
        this.defaultHand = new Card[8];
        final String[] types = new String[] {"BAT","RAT","TOAD","ROACH","FLY","SCORPION","SPIDER","STINKBUG"};
        int k = 0;
        for (int i=8;i<16;i++) {
            Card c = new Card(i, types[i-8]);
            map.put(types[i-8], 0);
            defaultHand[k] = c;
            k++;
        }
        addCardstoPlayer();
    }

    public void addCardstoPlayer() {
        for (int j=0;j<16;j++) {
            hand.add(deck.deck.get(((this.number-1)*16) +j));
        }
    }

    boolean takeCard(Card card) {
        discardList.add(card);
        map.put(card.getType(), map.get(card.getType()) + 1);
        return checkLoser();
    }

    boolean checkLoser() {
        HashMap<String, Integer> map2 = new HashMap<>();
        for (Card card : discardList) {
            if (map2.containsKey(card.getType())) {
                if (map2.get(card.getType()) == 3) return true;
                map2.put(card.getType(), map2.get(card.getType()) + 1);
                continue;
            }
            map2.put(card.getType(), 1);
        }
        return false;
    }
}
