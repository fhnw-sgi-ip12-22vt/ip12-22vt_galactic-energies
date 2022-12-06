package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.components.DashboardComponent;
import ch.fhnw.galacticenergies.components.LifeComponent;
import ch.fhnw.galacticenergies.controllers.AsteroidController;
import ch.fhnw.galacticenergies.controllers.LevelController;
import ch.fhnw.galacticenergies.controllers.RocketController;
import ch.fhnw.galacticenergies.controllers.ViewController;
import ch.fhnw.galacticenergies.events.GameEvent;
import ch.fhnw.galacticenergies.factories.GalacticEnergiesFactory;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.*;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.*;

import java.util.Map;
import java.util.stream.IntStream;

public class View extends GameApplication {

    private static final int STARTING_LEVEL = 1;

    private ViewController uiController;

    private int timeout = 0;

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

        onEvent(GameEvent.ASTEROID_GOT_HIT, AsteroidController::onAsteroidHit);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                RocketController.getRocketControl().up();
                getArrowsControl().buttonUpPressed();
            }

            @Override
            protected void onActionEnd() {
                RocketController.getRocketControl().stop();
                getArrowsControl().noButtonPressed();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                RocketController.getRocketControl().down();
                getArrowsControl().buttonDownPressed();
            }

            @Override
            protected void onActionEnd() {
                RocketController.getRocketControl().stop();
                getArrowsControl().noButtonPressed();
            }
        }, KeyCode.S);

        onKey(KeyCode.SPACE, "Shoot", () -> RocketController.getRocketControl().shoot());
        onKeyDown(KeyCode.K, "nextSpeed", () -> {
            if(geti("speed") == 12) return;
            inc("speed", + 1);
            RocketController.getRocketControl().setSpeedMultiplier(1 + (float)(geti("speed")) / 10);
            getDashboardControl().setSpeedImage(geti("speed"));
        });
        if (!isReleaseMode()) {
            onKeyDown(KeyCode.L, "Next Level", LevelController::nextLevel);
        }
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("lives", 3);
        vars.put("speed", 0);
        vars.put("currentEnergy", 0);
        vars.put("totalEnergy", 0);
        vars.put("level", STARTING_LEVEL);
        vars.put("asteroidsKilled", 0);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GalacticEnergiesFactory());
        getGameScene().getRoot().setCursor(Cursor.NONE);

        initBackground();

    }

    @Override
    protected void initPhysics() {
        // TODO
    }

    private void initBackground() {
        getGameScene().setBackgroundColor(Color.BLACK);
        spawn("background");

        entityBuilder()
                .type(WALL)
                .collidable()
                .with(new IrremovableComponent())
                .buildScreenBoundsAndAttach(40);
    }

    @Override
    protected void initUI() {
        spawn("dashboard");
        spawn("arrows");
        spawn("asteroid");
        getArrowsControl().noButtonPressed();


        LevelController.setLevel(STARTING_LEVEL);

        uiController = new ViewController(FXGL.getGameScene());

        IntStream.range(0, geti("lives"))
                .forEach(i -> uiController.addLife());
    }


    private DashboardComponent getDashboardControl() {
        return getGameWorld().getSingleton(DASHBOARD).getComponent(DashboardComponent.class);
    }

    private ArrowsComponent getArrowsControl() {
        return getGameWorld().getSingleton(ARROWS).getComponent(ArrowsComponent.class);
    }

    private LifeComponent getLifeComponent() {
        return getGameWorld().getSingleton(LIFE).getComponent(LifeComponent.class);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
