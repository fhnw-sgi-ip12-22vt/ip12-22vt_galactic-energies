package ch.fhnw.galacticenergies.menu;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.cutscene.Cutscene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class CutsceneTest extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Galactic Energies");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, "test", () -> {
            // TODO: loadCutscene shortcut?
         start();
        });
    }

    public static void start(){
        var lines = getAssetLoader().loadText("intro.txt");

        var cutscene = new Cutscene(lines);

        getCutsceneService().startCutscene(cutscene);
    }

    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.LIGHTGRAY);
    }

    public static void test(String[] args) {
        launch(args);

    }
}