package ch.fhnw.galacticenergies.kurbel;

import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;

public class Kurbel {
    private final int i2cBus;
    private static final int CONFIGURATION_REGISTER = 0x00;
    private final I2C i2c;


    public Kurbel(Context pi4j) {
        this.i2cBus = 0x01;
        i2c = pi4j.create(buildI2CConfig(pi4j, i2cBus, ADDRESS.GND.getAddress(), "0x40"));
    }

    private static I2CConfig buildI2CConfig(Context pi4j, int bus, int device, String deviceId) {
        return I2C.newConfigBuilder(pi4j).id("I2C-" + device + "@" + bus).name(deviceId).bus(bus).device(device).build();
    }

    public void writeConfigurationRegister(){
        i2c.writeRegisterWord(CONFIGURATION_REGISTER, 295);
    }


        public int readConfigurationRegister() {
            return i2c.readRegisterWord(CONFIGURATION_REGISTER);
        }
    public enum ADDRESS {
        /**
         * Device address if pin is connected to GND
         */
        GND(0x48),
        /**
         * Device address if pin is connected to VDD
         */
        VDD(0x49),
        /**
         * Device address if pin is connected to SDA
         */
        SDA(0x4A),
        /**
         * Device address if pin is connected to SCL
         */
        SCL(0x4B);
        /**
         * device address on I2C
         */
        private final int address;

        /**
         * Set the address for a device on an I2C bus
         *
         * @param address device address on I2C
         */
        ADDRESS(int address) {
            this.address = address;
        }

        /**
         * Returns the address from the device on an I2C bus
         *
         * @return Returns the address form the device
         */
        public int getAddress() {
            return address;
        }
    }


}
