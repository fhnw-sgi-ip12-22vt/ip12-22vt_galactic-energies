package ch.fhnw.galacticenergies.menu;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.cutscene.Cutscene;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getCutsceneService;

/**
 * the class IntroScene sets the settings for the Introduction in the game
 *
 * @version 1.0
 */
public final class IntroScene extends GameApplication {

    /**
     * starts the Cutscene service according to the file intro.txt
     */
    public static void start() {
        var lines = getAssetLoader().loadText("intro.txt");
        var cutscene = new Cutscene(lines);
        getCutsceneService().startCutscene(cutscene, MainMenu::enableButtons);
    }

    /**
     * Initializes Introduction settings
     *
     * @param settings sets current game settings
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Galactic Energies");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
    }


    /**
     * Sets the background color of the Introduction
     */
    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.LIGHTGRAY);
    }
}
