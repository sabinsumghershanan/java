package com.game.mancala;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by C24604 on 24-6-2017.
 */
public class MancalaTest {

    @Test
    public void shouldTestFirstStepOfBothPlayers() throws Exception {
        Mancala.init();
        assertThat("Initial Move by Player 1 success", Mancala.play(Mancala.playerOne,3),is(2));
        assertThat("Calculating the home1 points for the first move", Mancala.gameBoard[7],is(1));
        assertThat("unsuccessful move by player First", Mancala.play(Mancala.playerOne,10),is(1));

        assertThat("if the first move by player Second is not successful", Mancala.play(Mancala.playerTwo, 3),is(2));
        assertThat("Calculating the home1 points for the first move", Mancala.gameBoard[0],is(0));
        assertThat("unsuccessful move by player Second", Mancala.play(Mancala.playerTwo,10),is(1));

    }

}