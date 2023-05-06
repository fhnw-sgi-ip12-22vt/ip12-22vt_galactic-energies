package ch.fhnw.galacticenergies.controllers;

import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

/**
 * Defines the level of the current game
 *
 * @version 1.0
 */
public class LevelController {

    private static final int MAX_LEVEL = 10;

    /**
     * Show the next level in the game
     */
    public static void nextLevel() {
        inc("level", +1);
        var levelNum = geti("level");

        if (levelNum > MAX_LEVEL) {
            getDialogService().showMessageBox("GAME END", getGameController()::exit);
            return;
        }
        setLevel(levelNum);
    }

    public static void setLevel(int level) {
        spawn("rocket");
    }
}
