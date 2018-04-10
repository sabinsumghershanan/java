package com.game.mancala;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by C24604 on 24-6-2017.
 */
public class GameBoardTest {
    @Test
    public void init() throws Exception {
        int[] expected = {0, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 1};
        assertThat(Mancala.init(), is(expected));
    }

}