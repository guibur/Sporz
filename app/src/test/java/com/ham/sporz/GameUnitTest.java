package com.ham.sporz;

import com.ham.sporz.model.Game;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTest {
    @Test
    public void killPlayer() {
        Game game = new Game();
        game.addPlayer("Pierre Martin", "P");
        game.getPlayer(0).kill();
        assertFalse(game.getPlayer(0).isAlive());
    }
}