package com.game.mancala;

import java.io.IOException;

/**
 *For instantiating the game and the players
 *creating the two players and starting a new game
 */

public class Mancala {

    //Array for storing the last position of the game and the board
    public static int[] gameBoard;

    //To indicate the turns of the players
    //If flag = First Player's Id then it is First player's turn
    //If flag = Second Player's Id then it is Second player's turn
    public static int flag;

    //Player objects
    public static Player playerOne;
    public static Player playerTwo;

    public static int[] init() throws IOException {

        //Initialising the players
        playerOne = new Player(1);

        playerTwo = new Player(2);

        //Initialising the game's board
        gameBoard = GameBoard.init();

        //The flag is set for the first player's turn
        Mancala.flag = gameBoard[14];

        return gameBoard;
    }

    public static int play(Player player, int pit) throws IOException {

        //for starting a new round
        PlayGame game = new PlayGame();

        //method to make moves for the players respective to the turn
        //Return the flag indicating who is playing next
        Mancala.flag = game.move(pit, player);

        return Mancala.flag;

    }

}
