package com.game.mancala;

/**
 * To initialise the game board
 */
public class GameBoard {

    public static int[] init() {

        //To initialise the game board with 6 pits
        int[] board = {0, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, Mancala.playerOne.id};

        return board;
    }
}
