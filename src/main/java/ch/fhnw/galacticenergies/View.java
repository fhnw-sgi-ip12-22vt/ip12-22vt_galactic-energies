package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.controllers.LevelController;
import ch.fhnw.galacticenergies.factories.GalacticEnergiesFactory;
import ch.fhnw.galacticenergies.components.RocketComponent;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.*;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.*;

import java.util.Map;

public class View extends GameApplication {

    private static final int STARTING_LEVEL = 1;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Galactic Energies");
        settings.setVersion("0.1");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(false);
        settings.setIntroEnabled(false);
        settings.setProfilingEnabled(false);
        settings.setManualResizeEnabled(true);
    }

    @Override
    protected void onPreInit() {
        getSettings().setGlobalMusicVolume(0.5);
        getSettings().setGlobalSoundVolume(0.5);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                getRocketControl().up();
            }

            @Override
            protected void onActionEnd() {
                getRocketControl().stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                getRocketControl().down();
            }

            @Override
            protected void onActionEnd() {
                getRocketControl().stop();
            }
        }, KeyCode.S);

        if(!isReleaseMode()) {
            onKeyDown(KeyCode.L, "Next Level", () -> LevelController.nextLevel());
        }
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("lives", 3);
        vars.put("speed", 0);
        vars.put("currentEnergy", 0);
        vars.put("totalEnergy", 0);
        vars.put("level", STARTING_LEVEL);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GalacticEnergiesFactory());

        initBackground();
    }

    private void initBackground()
    {
        getGameScene().setBackgroundColor(Color.BLACK);

        spawn("rocket");
    }

    private RocketComponent getRocketControl() {
        return getGameWorld().getSingleton(ROCKET).getComponent(RocketComponent.class);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
