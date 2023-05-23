package ch.fhnw.galacticenergies.data;

import ch.fhnw.galacticenergies.controllers.PowerController;
import ch.fhnw.galacticenergies.kurbel.Kurbel;
import com.almasb.fxgl.app.ApplicationMode;
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

import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;

/**
 * Reads the Data form INA219 with i2c
 *
 * @version 1.0
 */

@SuppressWarnings("GrazieInspection")
public class PowerInput {
    private static Kurbel k;

    /**
     * initializes Pi4j providers & configures the required registers
     */
    public static void initPower() {
        final var piGpio = PiGpio.newNativeInstance();
        final var pi4j = Pi4J.newContextBuilder()
            .noAutoDetect()
            .add(new RaspberryPiPlatform() {
                @Override
                protected String[] getProviders() {
                    return new String[] {};
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

        k = new Kurbel(pi4j);
        k.writeConfigurationRegister();
        k.writeCalibrationRegister();
    }

    /**
     * Loop in which the Power currently measured by the INA219 is read and transferred to the PowerController
     */
    public static void powerLoop() {
        getGameTimer().runAtInterval(() -> {
            if (ApplicationMode.RELEASE == getSettings().getApplicationMode()) {
                PowerController.setCurrentPower(k.readPowerRegister());
            } else {
                PowerController.setCurrentPower(26);
            }
        }, Duration.seconds(1));
    }
}


