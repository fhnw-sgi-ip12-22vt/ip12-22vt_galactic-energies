package ch.fhnw.galacticenergies.kurbel;

import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;


/**
 * Initiating current meter & defining read and write operations.
 *
 * @Version 1.0
 */
public class Kurbel {

    private static final int CONFIGURATION_REGISTER = 0x00;
    private static final int CALIBRATION_REGISTER = 0x05;
    private static final int POWER_REGISTER = 0x03;
    private final int i2cBus = 0x01;
    private final int device = 0x40;
    private final String deviceName = "Kurbel";
    private final I2C i2c;

    /**
     * Initialisation of the required Parameters
     *
     * @param pi4j gives the Context
     */
    public Kurbel(Context pi4j) {
        i2c = pi4j.create(I2C.newConfigBuilder(pi4j)
            .id("I2C-" + device + "@" + i2cBus)
            .name(deviceName)
            .bus(i2cBus)
            .device(device)
            .build());
    }


    /**
     * @param pi4j     Context
     * @param bus      Bus on which the device is connected
     * @param device   Device number
     * @param deviceId DeviceId
     * @return
     */
    private static I2CConfig buildI2CConfig(Context pi4j, int bus, int device, String deviceId) {
        return I2C.newConfigBuilder(pi4j).id("I2C-" + device + "@" + bus).name(deviceId).bus(bus).device(device)
            .build();
    }

    /**
     * Calibration of ConfigurationRegister
     */
    public void writeConfigurationRegister() {
        i2c.writeRegisterWord(CONFIGURATION_REGISTER, 295);
    }

    /**
     * Calibration of the CalibrationRegister based on previous calculations
     */

    public void writeCalibrationRegister() {
        i2c.writeRegisterWord(CALIBRATION_REGISTER, 17420);
    }

    /**
     * Reading the CalibrationRegister
     *
     * @return CalibrationRegister output
     */
    public int readCalibrationRegister() {
        return i2c.readRegisterWord(CALIBRATION_REGISTER);
    }

    /**
     * Reading the PowerRegister
     *
     * @return PowerRegister content
     */
    public int readPowerRegister() {
        return i2c.readRegisterWord(POWER_REGISTER);
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
