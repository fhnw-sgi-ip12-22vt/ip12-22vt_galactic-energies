package ch.fhnw.galacticenergies.controllers;

import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class LevelController {

    private static final int MAX_LEVEL = 10;

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
