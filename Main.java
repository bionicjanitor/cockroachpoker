package com.company;


import java.util.Collections;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Deck deck = new Deck();
        Collections.shuffle((deck.deck));

        Player[] players = new Player[4];
        int[][] startPos = new int[][]{{1,7},{0,0},{1,0},{10,0}};

        //Create 4 players
        for (int i=0;i<4;i++) {
            players[i] = new Player(i+1,i*90, deck, startPos[i][0], startPos[i][1]);
        }

        Grid grid = new Grid(players);
    }
}
