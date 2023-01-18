package ch.fhnw.galacticenergies.kurbel

import com.pi4j.config.exception.ConfigException;
import com.pi4j.context.Context;
import com.pi4j.catalog.components.helpers.ContinuousMeasuringException;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;

import java.util.Arrays;
import java.util.function.Consumer;

public class kurbel {
    private final int i2cBus;
    private static final int CONFIGURATION_REGISTER = 0x00;
    private final I2C i2c;


    public kurbel() {
        this.i2cBus = 0x40;

    }


        public int readConfigurationRegister() {
            return i2c.readRegisterWord(CONFIGURATION_REGISTER);
        }


}
