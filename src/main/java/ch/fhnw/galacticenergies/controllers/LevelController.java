package ch.fhnw.galacticenergies.controllers;

import static com.almasb.fxgl.dsl.FXGL.*;
public class LevelController {

    private static final int MAX_LEVEL = 10;

    public static void nextLevel()
    {
        inc("level", +1);
        var levelNum = geti("level");

        if(levelNum > MAX_LEVEL) {
            getDialogService().showMessageBox("GAME END", getGameController()::exit);
            return;
        }
        setLevel(levelNum);
    }

    public static void setLevel(int level){

    }
}
