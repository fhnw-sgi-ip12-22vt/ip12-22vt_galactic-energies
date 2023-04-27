package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
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

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ARROWS;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public  class MovementControllerJoyStick {
    public static void movement() {

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

        final var joystick = new Joystick(pi4j, PIN.D5, PIN.D6, PIN.PWM13, PIN.PWM19, PIN.D26);


        joystick.whileNorth(5, () -> {
            Platform.runLater(() -> {
                RocketController.getRocketControl().up();
                getArrowsControl().buttonUpPressed();
            });


        });

        joystick.whileSouth(5, () -> {
            Platform.runLater(() -> {
                RocketController.getRocketControl().down();
                getArrowsControl().buttonDownPressed();
            });
        });


    }

    private static ArrowsComponent getArrowsControl() {
        return getGameWorld().getSingleton(ARROWS).getComponent(ArrowsComponent.class);
    }
}
