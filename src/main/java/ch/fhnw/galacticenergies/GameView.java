package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.controllers.GameViewController;
import ch.fhnw.galacticenergies.controllers.SpeedController;
import ch.fhnw.galacticenergies.services.WindowService;
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
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Creation of the stage with the movement of the spaceship (up and down).
 */

public class GameView extends Application {

    private static Stage mainStage;

    public static Stage getStage() {
        return mainStage;
    }
    @Override
    public void start(Stage stage) throws Exception {

        mainStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(GameView.class.getResource("game-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        GameViewController controller = fxmlLoader.getController();
        stage.setTitle("Galactic Energies");
        //stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        controller.initPlayer();
        controller.showSpeed(1);
        controller.showArrow(1);

        // Initialize Pi4J context

        final var piGpio = PiGpio.newNativeInstance();
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



       // var pi4j = Pi4J.newAutoContext();
        final var joystick = new Joystick(pi4j, PIN.D5, PIN.D6, PIN.PWM13, PIN.PWM19, PIN.D26);

        joystick.whileNorth(30, () -> {
            System.out.println("north!!!!!");
            Platform.runLater(() -> {
                controller.moveUp();
                controller.showArrow(3);
            });

        });
        joystick.onNorth(() -> {
            System.out.println("North !!!!!!");
            Platform.runLater(() -> {
                controller.moveUp();
                controller.showArrow(3);
            });

        });

        joystick.whileSouth(30, () -> {
                    System.out.println("north!!!!!");
                    Platform.runLater(() -> {
                        controller.moveDown();
                        controller.showArrow(2);
                    });
                });

        joystick.onSouth(() -> {
                    System.out.println("South !!!!!!");
                    Platform.runLater(() -> {
                        controller.moveDown();
                        controller.showArrow(2);
                    });
                });
        joystick.onEast(() -> {
            System.out.println("East !!!!!!");
        });

        joystick.onWest(() -> {
            System.out.println("West !!!!!!");
        });


        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W -> {
                    controller.moveUp();
                    controller.showArrow(3);
                }
                case S -> {
                    controller.moveDown();
                    controller.showArrow(2);
                }
                case T -> {
                    SpeedController.speedUp();
                    controller.showSpeed(SpeedController.getSpeed());
                }
                case Z -> {
                    SpeedController.speedDown();
                    controller.showSpeed(SpeedController.getSpeed());
                }
            }
        });
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.W ||  e.getCode() == KeyCode.S ) controller.showArrow(1);
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            WindowService.setWindowHeight((Double) newVal);
        });
    }
}
