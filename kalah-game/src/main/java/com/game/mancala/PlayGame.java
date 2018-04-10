package com.game.mancala;

import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

/**
 *This class is responsible for implementing all the game rules
 */
//
public class PlayGame {

    //Number of stones into the selected pit
    int stones;

    //Total number of pits
    final int TOTAL_NUMBER_OF_PITS = 14;


    /**
     *
     * @param selectedPit
     * @param player
     * @return flag
     * @throws IOException
     * This method will implement the move of the player
     *
     */

    public int move(int selectedPit, Player player) throws IOException {

        stones = Mancala.gameBoard[selectedPit];

        //restricting the player to chose his own pit
        if (player.id == 1) {
            if (!ArrayUtils.contains(player.availableSlots, selectedPit) || Mancala.gameBoard[selectedPit] == 0 ) {
                Mancala.flag = 1;
            } //Else make the move
            else {
                moveAround(0, 7, player.availableSlots, player, selectedPit);
            }
        }

        //If one of the players chooses an opponent's pit, has to choose again
        if (player.id == 2) {
            if (!ArrayUtils.contains(player.availableSlots, selectedPit) || Mancala.gameBoard[selectedPit] == 0) {

                Mancala.flag = 2;

            } //Else make the move
            else {
                moveAround(7, 0, player.availableSlots, player, selectedPit);
            }

        }
        return Mancala.flag;
    }

    /**
     *
     * @param opponentsHouse
     * @param playersHouse
     * @param availableSlots
     * @param player
     * @param selectedPit
     * @throws IOException
     *
     * for sowing the  stones in the appropriate pits
     */

    public void moveAround(int opponentsHouse, int playersHouse, int[] availableSlots, Player player, int selectedPit) throws IOException {

        //A value which when is set indicates that the stones will meet the opponent's house in their way 
        //and should skip it
        int skipMode = 0;

        //value which will show that the player can take the opponents' stone if he arrive in an empty pit
        //ending with a stone
        boolean captureMode;

        captureMode = captureOpponentsStonesCheck(availableSlots, selectedPit , player);

    //sowing the stones to the right
        for (int i = 1; i < stones + 1; i++) {

            //Keep sowing until you reach the end of the board 
            if (selectedPit + i < TOTAL_NUMBER_OF_PITS) {

                //skip opponents house
                if (selectedPit + i == opponentsHouse) {
                    skipMode = 1;
                }

                if (skipMode == 1) {
                    Mancala.gameBoard[selectedPit + i + 1]++;
                } else {
                    Mancala.gameBoard[selectedPit + i]++;
                }

            } //When you reach the end of the board start from the beginning
            else {

                //skip opponents house
                if (selectedPit + i - TOTAL_NUMBER_OF_PITS == opponentsHouse) {
                    skipMode = 1;
                }

                if (skipMode == 1) {
                    Mancala.gameBoard[selectedPit + i + 1 - TOTAL_NUMBER_OF_PITS]++;
                    System.out.println("pit"+selectedPit + "i"+ i);
                } else {
                    Mancala.gameBoard[selectedPit + i - TOTAL_NUMBER_OF_PITS]++;
                }
            }
        }


        //Empty player's selected pit
        Mancala.gameBoard[selectedPit] = 0;

        // incase if he lands on the same pit
        if(selectedPit + (stones) - TOTAL_NUMBER_OF_PITS == selectedPit ){
            Mancala.gameBoard[selectedPit] = 1;
        }

        //Capture opponent's stones if needed
        if (captureMode) {

            if (selectedPit + stones < TOTAL_NUMBER_OF_PITS) {
                Mancala.gameBoard[playersHouse] = Mancala.gameBoard[playersHouse] + Mancala.gameBoard[selectedPit + stones] + Mancala.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones];
                Mancala.gameBoard[selectedPit + stones] = 0;
                Mancala.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones] = 0;
            } else {
                Mancala.gameBoard[playersHouse] = Mancala.gameBoard[playersHouse] + Mancala.gameBoard[selectedPit + stones + 1 - TOTAL_NUMBER_OF_PITS] + Mancala.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones - 1 + TOTAL_NUMBER_OF_PITS];
                Mancala.gameBoard[selectedPit + stones + 1 - TOTAL_NUMBER_OF_PITS] = 0;
                Mancala.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones - 1 + TOTAL_NUMBER_OF_PITS] = 0;
            }
        }

        //Check if the player wins an extra round
        playAgainCheck(playersHouse, player, selectedPit);

        //Check if the game is over
        gameOverCheck(player);
    }
    //to check if the player lands on home to play again
    private void playAgainCheck(int house, Player player, int selectedPit) throws IOException {

        if (selectedPit + stones < TOTAL_NUMBER_OF_PITS) {
            if (selectedPit + stones == house) {

                Mancala.flag = player.id;
            } else {
                Mancala.flag = 3 - player.id;
            }
        } else if (selectedPit + stones - TOTAL_NUMBER_OF_PITS == house) {

            Mancala.flag = player.id;
        } else {
            Mancala.flag = 3 - player.id;
        }
    }
    //method to capture the opponents stone if the player lands the stone in an emoty pit
    private boolean captureOpponentsStonesCheck(int[] availableSlots, int selectedPit , Player player) {

        if (selectedPit + stones < TOTAL_NUMBER_OF_PITS) {
            if (ArrayUtils.contains(availableSlots, selectedPit + stones) && Mancala.gameBoard[selectedPit + stones] == 0 && Mancala.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones] > 0) {
                return true;
            } else {
                return false;
            }
        } else if (
                ( (player.id == 1 || ( player.id == 2 && stones <= 13) )
                        && ( ArrayUtils.contains(availableSlots, selectedPit + stones + 1 - TOTAL_NUMBER_OF_PITS)
                                && Mancala.gameBoard[selectedPit + stones + 1 - TOTAL_NUMBER_OF_PITS] == 0
                                && Mancala.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones - 1 + TOTAL_NUMBER_OF_PITS] > 0))

                || (  player.id == 2 && (stones > 13) && ArrayUtils.contains(availableSlots, selectedPit + stones % 13)
                        && Mancala.gameBoard[selectedPit + stones % 13] == 0
                        && Mancala.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones % 13] > 0 )
                ) {
            return true;
        }  else {
            return false;
        }
    }
    //to check if the game is over
    private void gameOverCheck(Player player) {

        if (Mancala.gameBoard[player.availableSlots[0]] == 0 && Mancala.gameBoard[player.availableSlots[1]] == 0 && Mancala.gameBoard[player.availableSlots[2]] == 0 && Mancala.gameBoard[player.availableSlots[3]] == 0 && Mancala.gameBoard[player.availableSlots[4]] == 0 && Mancala.gameBoard[player.availableSlots[5]] == 0) {
            Mancala.flag = 3;
        }

    }

}
