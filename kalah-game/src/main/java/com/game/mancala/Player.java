/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.mancala;

/**
 *
 *
 *
 */

/**
 * Class for describing the player
 */
public class Player {

    //Player's id
    public int id;

    //Player's points
    public int gamePoints;

    //Allowed pits to choose for the First and the Second player respectively
    int availableSlots[];

    //Costructor of the player
    Player(int playerId) {
        this.id = playerId;
        this.availableSlots = new int[6];
        if (this.id == 1) {

            availableSlots[0] = 1;
            availableSlots[1] = 2;
            availableSlots[2] = 3;
            availableSlots[3] = 4;
            availableSlots[4] = 5;
            availableSlots[5] = 6;

        } else {
            availableSlots[0] = 8;
            availableSlots[1] = 9;
            availableSlots[2] = 10;
            availableSlots[3] = 11;
            availableSlots[4] = 12;
            availableSlots[5] = 13;
        }

    }
//method to calculate the points for each player
    public void points() {

        if (id == 1)
            this.gamePoints = Mancala.gameBoard[7];

        if (id == 2)
            this.gamePoints = Mancala.gameBoard[0];

    }

}
