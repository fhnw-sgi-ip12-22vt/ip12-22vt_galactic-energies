package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.controllers.AsteroidController;
import ch.fhnw.galacticenergies.controllers.CheckpointController;
import ch.fhnw.galacticenergies.controllers.LevelController;
import ch.fhnw.galacticenergies.controllers.MovementController;
import ch.fhnw.galacticenergies.controllers.PowerController;
import ch.fhnw.galacticenergies.controllers.RocketController;
import ch.fhnw.galacticenergies.controllers.ViewController;
import ch.fhnw.galacticenergies.data.PowerInput;
import ch.fhnw.galacticenergies.factories.GalacticEnergiesFactory;
import ch.fhnw.galacticenergies.factories.LoadingSceneFactory;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;

import java.util.Map;
import java.util.stream.IntStream;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ARROWS;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ASTEROID;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.PLANET;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ROCKET;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.WALL;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.spawn;

/**
 * Main Class which starts the Game and initializes the required settings
 */
public class View extends GameApplication {

    private static final int STARTING_LEVEL = 1;
    public static final LevelController levelController = new LevelController();
    public static final AsteroidController asteroidController = new AsteroidController();
    public static final CheckpointController checkpointController = new CheckpointController();
    private ViewController uiController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Galactic Energies");
        settings.setVersion("1.0");
        settings.getCSSList().add("ui.css");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
        settings.setIntroEnabled(false);
        settings.setMainMenuEnabled(true);
        settings.setProfilingEnabled(false);
        settings.setManualResizeEnabled(true);
        settings.setApplicationMode(ApplicationMode.RELEASE);
        settings.setSceneFactory(new LoadingSceneFactory());

        settings.setTicksPerSecond(60);
    }

    @Override
    protected void onPreInit() {
        getSettings().setGlobalMusicVolume(0.5);
        getSettings().setGlobalSoundVolume(0.5);
    }

    @Override
    protected void initInput() {
        MovementController.movement();
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("lives", 5);
        vars.put("speed", 0);
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
        asteroidController.setMaxAsteroids(5);
        initBackground();
        ViewController.setPaused(false);
        levelController.setSavedPower(0);
        if (ApplicationMode.RELEASE == getSettings().getApplicationMode()) {
            PowerInput.initPower();
        }
        PowerInput.powerLoop();
        PowerController.initText();

        levelController.setUiTextLevel(getUIFactoryService().newText("", Color.WHITE, 22));
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(ROCKET, ASTEROID) {
            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity ROCKET, Entity ASTEROID) {
                ViewController.setPaused(true);
                asteroidController.removeAllAsteroids();
                checkpointController.removeCheckpoint();
                RocketController.getRocketControl().getEntity().setY((double) getAppHeight() / 2);
                uiController.loseLife();

            }
        });

        // Checkpoint interaction
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(ROCKET, PLANET) {
            @Override
            protected void onCollisionBegin(Entity ROCKET, Entity PLANET) {
                ViewController.setPaused(true);
                PLANET.removeFromWorld();
                asteroidController.removeAllAsteroids();
                levelController.nextLevel();
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
        spawn("rocket");
        getArrowsControl().noButtonPressed();

        asteroidController.init();

        getArrowsControl().noButtonPressed();

        uiController = new ViewController(FXGL.getGameScene());

        IntStream.range(0, geti("lives"))
            .forEach(i -> uiController.addLife());

    }

    private ArrowsComponent getArrowsControl() {
        return getGameWorld().getSingleton(ARROWS).getComponent(ArrowsComponent.class);
    }
}
