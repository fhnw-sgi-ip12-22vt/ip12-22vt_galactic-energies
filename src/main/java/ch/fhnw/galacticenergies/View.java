package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.components.AsteroidComponent;
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
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.input.UserAction;
import com.pi4j.Pi4J;
import com.pi4j.catalog.components.Joystick;
import com.pi4j.catalog.components.helpers.PIN;
import com.pi4j.library.pigpio.PiGpio;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider;
import com.pi4j.plugin.pigpio.provider.pwm.PiGpioPwmProvider;
import com.pi4j.plugin.pigpio.provider.serial.PiGpioSerialProvider;
import com.pi4j.plugin.pigpio.provider.spi.PiGpioSpiProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Map;
import java.util.stream.IntStream;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.*;
import static com.almasb.fxgl.dsl.FXGL.*;

public class View extends GameApplication {

    private static final int STARTING_LEVEL = 1;

    private ViewController uiController;
    private boolean gameRunning = true;
    private final int timeout = 0;

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

        // Initialize Pi4J context

        /*final var piGpio = PiGpio.newNativeInstance();
        final var pi4j = Pi4J.newContextBuilder()
                .noAutoDetect()
                .add(new RaspberryPiPlatform() {
                    @Override
                    protected String[] getProviders() {
                        return new String[]{};
                    }
                })
                .add(PiGpioDigitalInputProvider.newInstance(piGpio),
                        PiGpioDigitalOutputProvider.newInstance(piGpio),
                        PiGpioPwmProvider.newInstance(piGpio),
                        PiGpioSerialProvider.newInstance(piGpio),
                        PiGpioSpiProvider.newInstance(piGpio),
                        LinuxFsI2CProvider.newInstance()
                )
                .build();
        */

        // var pi4j = Pi4J.newAutoContext();
        //final var joystick = new Joystick(pi4j, PIN.D5, PIN.D6, PIN.PWM13, PIN.PWM19, PIN.D26);

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

        /*joystick.whileNorth(5, () -> {
            Platform.runLater(() -> {
                System.out.println("north!!!!!");
                RocketController.getRocketControl().up();
                getArrowsControl().buttonUpPressed();
            });


        });

        joystick.whileSouth(5, () -> {
            Platform.runLater(() -> {
                System.out.println("south!!!!!");
                RocketController.getRocketControl().down();
                getArrowsControl().buttonDownPressed();
            });
        });*/


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


        onKeyDown(KeyCode.K, "nextSpeed", () -> {
            if (geti("speed") == 12) return;
            inc("speed", +1);
            RocketController.getRocketControl().setSpeedMultiplier(1 + (float) (geti("speed")) / 10);
            getDashboardControl().setSpeedImage(geti("speed"));
        });
        if (!isReleaseMode()) {
            onKeyDown(KeyCode.L, "Next Level", LevelController::nextLevel);
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

        AsteroidController asteroidController = new AsteroidController();
        asteroidController.init();

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
