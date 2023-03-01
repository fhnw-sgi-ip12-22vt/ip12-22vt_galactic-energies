package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.components.DashboardComponent;
import ch.fhnw.galacticenergies.components.LifeComponent;
import ch.fhnw.galacticenergies.controllers.*;
import ch.fhnw.galacticenergies.enums.GalacticEnergiesType;
import ch.fhnw.galacticenergies.events.GameEvent;
import ch.fhnw.galacticenergies.factories.GalacticEnergiesFactory;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.concurrent.Async;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.pi4j.catalog.components.Joystick;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import com.pi4j.Pi4J;
import com.pi4j.library.pigpio.PiGpio;
import com.pi4j.catalog.components.Joystick;
import com.pi4j.catalog.components.helpers.PIN;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider;
import com.pi4j.plugin.pigpio.provider.pwm.PiGpioPwmProvider;
import com.pi4j.plugin.pigpio.provider.serial.PiGpioSerialProvider;
import com.pi4j.plugin.pigpio.provider.spi.PiGpioSpiProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;


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
        settings.setFullScreenFromStart(true);
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

        if(!Config.DEVELOPMENT_MODE){

            System.out.println("das if isch perfekt");

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
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GalacticEnergiesFactory());
        getGameScene().getRoot().setCursor(Cursor.NONE);

        initBackground();

    }

    @Override
    protected void initPhysics() {
       /* FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(ROCKET, ASTEROID) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity ROCKET, Entity ASTEROID) {
                ASTEROID.removeFromWorld();
                uiController.loseLife();

            }
        });*/
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
        /*IntStream.range(0, geti("amountAsteroids"))
                        .forEach( i -> spawn("asteroid"));*/
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
