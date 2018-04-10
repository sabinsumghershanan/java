package com.game.mancala;

import org.junit.Test;

import static com.game.mancala.Mancala.gameBoard;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by C24604 on 24-6-2017.
 */
public class PlayGameTest {

    @Test
    public void sampleTest() throws Exception {

        gameBoard = Mancala.init();

        PlayGame game = new PlayGame();

        assertThat(game.move(13,new Player(1)), is(1));

    }


    @Test
    public void errorTestWhenSowEndInSamePit() throws Exception {
        Mancala.gameBoard = new int[]{5, 1, 4, 5, 1, 3, 14, 6, 13, 0, 1, 2, 13, 4, 1};
        PlayGame game = new PlayGame();


          Mancala.play(new Player(1), 6);

           int expected[] = new int[]{5, 2, 5, 6, 2, 4, 1, 8, 14, 1, 2, 3, 14, 5, 1};

        assertThat(Mancala.gameBoard, is(expected));

    }


}