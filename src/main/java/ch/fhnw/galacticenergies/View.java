package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.components.DashboardComponent;
import ch.fhnw.galacticenergies.components.LifeComponent;
import ch.fhnw.galacticenergies.controllers.AsteroidController;
import ch.fhnw.galacticenergies.controllers.CheckpointController;
import ch.fhnw.galacticenergies.controllers.LevelController;
import ch.fhnw.galacticenergies.controllers.MovementControllerDEV;
import ch.fhnw.galacticenergies.controllers.MovementControllerJoyStick;
import ch.fhnw.galacticenergies.controllers.PowerController;
import ch.fhnw.galacticenergies.controllers.ViewController;
import ch.fhnw.galacticenergies.data.PowerInput;
import ch.fhnw.galacticenergies.events.GameEvent;
import ch.fhnw.galacticenergies.factories.GalacticEnergiesFactory;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Map;
import java.util.stream.IntStream;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ARROWS;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ASTEROID;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.DASHBOARD;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.LIFE;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.PLANET;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ROCKET;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.WALL;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.onEvent;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class View extends GameApplication {

    private static final int STARTING_LEVEL = 1;

    private ViewController uiController;


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Galactic Energies");
        settings.setVersion("0.2");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
        settings.setIntroEnabled(false);
        settings.setProfilingEnabled(false);
        settings.setManualResizeEnabled(true);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    @Override
    protected void onPreInit() {
        getSettings().setGlobalMusicVolume(0.5);
        getSettings().setGlobalSoundVolume(0.5);


        onEvent(GameEvent.ASTEROID_GOT_HIT, AsteroidController::onAsteroidHit);
    }

    @Override
    protected void initInput() {

        if (getSettings().getApplicationMode() == ApplicationMode.RELEASE) {
            MovementControllerJoyStick.movement();
        } else {
            MovementControllerDEV.movement();
        }

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("lives", 5);
        vars.put("speed", 0);
        vars.put("amountAsteroids", 5);
        vars.put("currentEnergy", 0);
        vars.put("totalEnergy", 0);
        vars.put("level", STARTING_LEVEL);
        vars.put("asteroidsKilled", 0);
        vars.put("amountPlanet", 1);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GalacticEnergiesFactory());
        getGameScene().getRoot().setCursor(Cursor.NONE);

        initBackground();
        if (ApplicationMode.RELEASE == getSettings().getApplicationMode()) {
            PowerInput.initPower();
        }
        PowerInput.powerLoop();
        PowerController.initText();


    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(ROCKET, ASTEROID) {
            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity ROCKET, Entity ASTEROID) {
                ASTEROID.removeFromWorld();
                uiController.loseLife();
            }
        });

        // Checkpoint interaction
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(ROCKET, PLANET) {
            @Override
            protected void onCollisionBegin(Entity ROCKET, Entity PLANET) {
                uiController.setPaused(true);
            }
        });
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
        IntStream.range(0, geti("amountAsteroids"))
            .forEach(i -> spawn("asteroid"));
        IntStream.range(0, geti("amountPlanet"))
            .forEach(i -> spawn("planet"));
        getArrowsControl().noButtonPressed();
        final int[] counter = {0};
        final double[] level = {100, 200, 300};
        getGameTimer().runAtInterval(() -> {
            if (uiController.isPaused()) return;
            if (PowerController.getTotalPower() > level[0] && counter[0] == 0) {
                CheckpointController.addCheckpoint();
                counter[0]++;
            } else if (PowerController.getTotalPower() > level[1] && counter[0] == 1) {
                CheckpointController.addCheckpoint();
                counter[0]++;
            } else if (PowerController.getTotalPower() > level[2] && counter[0] == 2) {
                CheckpointController.addCheckpoint();
                counter[0]++;
            }
        }, Duration.seconds(1));

        IntStream.range(0, geti("amountAsteroids"))
            .forEach(i -> spawn("asteroid"));

        getArrowsControl().noButtonPressed();

        LevelController.setLevel(STARTING_LEVEL);

        uiController = new ViewController(FXGL.getGameScene());

        IntStream.range(0, geti("lives"))
            .forEach(i -> uiController.addLife());
        spawn("asteroid");

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
