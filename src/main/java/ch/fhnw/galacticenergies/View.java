package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.components.AsteroidComponent;
import ch.fhnw.galacticenergies.components.DashboardComponent;
import ch.fhnw.galacticenergies.components.LifeComponent;
import ch.fhnw.galacticenergies.controllers.*;
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

import java.util.Map;
import java.util.stream.IntStream;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.*;
import static com.almasb.fxgl.dsl.FXGL.*;
import javafx.beans.property.ObjectProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

        if(getSettings().getApplicationMode() == ApplicationMode.RELEASE){
            MovementControllerJoyStick.movement();
        }else{
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
        vars.put("amountPlanet",1);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GalacticEnergiesFactory());
        getGameScene().getRoot().setCursor(Cursor.NONE);

        initBackground();
        if(ApplicationMode.RELEASE == getSettings().getApplicationMode()){
            PowerInput.initPower();
        }
        PowerInput.powerLoop();
        PowerController.initText();

    }

    @Override
    protected void initPhysics() {
       FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(ROCKET, ASTEROID) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity ROCKET, Entity ASTEROID) {
                ASTEROID.removeFromWorld();
                uiController.loseLife();

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
                        .forEach( i -> spawn("asteroid"));
        IntStream.range(0, geti("amountPlanet"))
            .forEach( i -> spawn("planet"));
        getArrowsControl().noButtonPressed();

        AsteroidController asteroidController = new AsteroidController();
        asteroidController.init();

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
