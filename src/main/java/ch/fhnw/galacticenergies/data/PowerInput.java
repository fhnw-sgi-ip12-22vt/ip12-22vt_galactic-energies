package ch.fhnw.galacticenergies.data;

import ch.fhnw.galacticenergies.controllers.PowerController;
import ch.fhnw.galacticenergies.kurbel.Kurbel;
import com.pi4j.Pi4J;
import com.pi4j.library.pigpio.PiGpio;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider;
import com.pi4j.plugin.pigpio.provider.pwm.PiGpioPwmProvider;
import com.pi4j.plugin.pigpio.provider.serial.PiGpioSerialProvider;
import com.pi4j.plugin.pigpio.provider.spi.PiGpioSpiProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;
import javafx.util.Duration;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;

/**
 *
 * @version 1.0
 */

public class PowerInput {
    private static Kurbel k;
    public static void initPower(){
        System.out.println("start");
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
        System.out.println("kurbel");



        k = new Kurbel(pi4j);
        k.writeConfigurationRegister();
        k.writeCalibrationRegister();
    }
    public static void powerLoop(){
        getGameTimer().runAtInterval(() -> {
                System.out.println(k.readPowerRegister());
                PowerController.setCurrentPower(k.readPowerRegister());
            }
        , Duration.seconds(1));
    }
}


