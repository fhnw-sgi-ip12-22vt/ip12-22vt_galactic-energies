package ch.fhnw.galacticenergies.kurbel;

import com.pi4j.Pi4J;
import com.pi4j.library.pigpio.PiGpio;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider;
import com.pi4j.plugin.pigpio.provider.pwm.PiGpioPwmProvider;
import com.pi4j.plugin.pigpio.provider.serial.PiGpioSerialProvider;
import com.pi4j.plugin.pigpio.provider.spi.PiGpioSpiProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;

public class KrubelTest {
    public static void main(String[] args) {
        System.out.println("start");
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
        System.out.println("kurbel");


        Kurbel k = new Kurbel(pi4j);
        k.writeConfigurationRegister();
        k.writeCalibrationRegister();

        System.out.println(k.readCalibrationRegister());

        while (1 == 1) {
            System.out.println(k.readPowerRegister());
            System.out.println();
        }

    }
}
