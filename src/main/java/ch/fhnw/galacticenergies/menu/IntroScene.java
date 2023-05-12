package ch.fhnw.galacticenergies.menu;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.cutscene.Cutscene;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getCutsceneService;

public final class IntroScene extends GameApplication {

    /*
    *
    */
    public static void start(){
        var lines = getAssetLoader().loadText("intro.txt");
        var cutscene = new Cutscene(lines);
        getCutsceneService().startCutscene(cutscene, MainMenu::enableButtons);
    }


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Galactic Energies");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
    }



    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.LIGHTGRAY);
    }
}
