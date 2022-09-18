package com.company;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> deck;
    final String[] types = new String[] {"BAT","RAT","TOAD","ROACH","FLY","SCORPION","SPIDER","STINKBUG"};


    public Deck() {
        this.deck = new ArrayList<Card>();
        //populate deck
        for (int i=0;i<8;i++) {
            for (int j=0; j<8;j++) {
                Card a = new Card(j,types[j]);
                deck.add(a);
            }
        }
    }
    public void print() {
        for (int i=0;i<8;i++) {
            for (int j=0; j<8;j++) {
                System.out.print(deck.get((i*8) +j).getType() + " ");
            }
            System.out.println();
        }
    }

    public void addCards() {

    }
}
