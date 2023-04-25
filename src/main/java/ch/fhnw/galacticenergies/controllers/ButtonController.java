package ch.fhnw.galacticenergies.controllers;

import com.pi4j.Pi4J;
import com.pi4j.catalog.components.Joystick;
import com.pi4j.catalog.components.helpers.PIN;
import com.pi4j.context.Context;
import com.pi4j.catalog.components.SimpleButton;
import com.pi4j.library.pigpio.PiGpio;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider;
import com.pi4j.plugin.pigpio.provider.pwm.PiGpioPwmProvider;
import com.pi4j.plugin.pigpio.provider.serial.PiGpioSerialProvider;
import com.pi4j.plugin.pigpio.provider.spi.PiGpioSpiProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;
import javafx.application.Platform;
import com.pi4j.catalog.components.SimpleButton;


/**
     * This example app initializes all four directional buttons and registers event handlers for every button. While this example itself does
     * not do much, it showcases how it could be used for controlling a player character in a game. Before the application exits it will cleanly
     * unregister all previously configured event handlers.
     */
    public class ButtonController {

    public static void movement() {

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

        final var button = new SimpleButton(pi4j, PIN.PWM18, false);


        button.onDown      (() -> System.out.println("Pressing the button"));
        button.whilePressed(() -> System.out.println("Pressing"), 1000);
        button.onUp        (() -> System.out.println("Stopped pressing."));

// Wait for 15 seconds while handling events before exiting

// Unregister all event handlers to exit this application in a clean way

    }
    }
