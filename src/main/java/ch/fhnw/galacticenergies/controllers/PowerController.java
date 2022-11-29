package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.data.PowerInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the current and totalPower that is being produced
 * @version 1.0
 */

public class PowerController {

    private static double currentPower;
    private static double totalPower;
    private static int lastTimestamp = 0;

    /**
     * Processes the ArrayLists that were created in PowerInput
     * Calculates the totalPower that was generated and stores it in totalPower
     */
    public static void calcPower() {
        int currentTimestamp = 0;
        String currentPowerStr;
        List<List> input = new ArrayList<>();
        /**
         * Endless loop that updates the current and total Power that is beeing generated
         */
        while (1 == 1) {
            input = PowerInput.readCSV();
            input.remove(0);
            currentPowerStr = (String) input.get(input.size() - 1).get(7);
            setCurrentPower(Double.valueOf(currentPowerStr));
            // loop through the input ArrayList
            for (int i = 0; i < input.size(); i++) {
                //read the timestamp of the current entry
                currentTimestamp = Integer.valueOf((String) input.get(i).get(2));
                //if the timestamp is newer then the lastTimestamp that has been processed
                if (currentTimestamp > lastTimestamp) {
                    //Add the power generated in this entry to totalPower
                    addTotalPower(Double.valueOf((String) input.get(i).get(7)));
                    lastTimestamp = currentTimestamp;
                }
            }
            /**
             * pauses for 1 second
             */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Getter for currentPower
     *
     * @return currentPower
     */
    public static double getCurrentPower() {
        return currentPower;
    }

    /**
     * Set the currentPower
     *
     * @param currentPower
     */
    public static void setCurrentPower(double currentPower) {
        PowerController.currentPower = currentPower;
    }

    /**
     * Adds power to totalPower
     *
     * @param power amount of power to be added to total Power
     */

    public static void addTotalPower(double power) {
        totalPower += power;

    }

    /**
     * @return return the total value of the power.
     */
    public static double getTotalPower() {

        return totalPower;
    }

    /**
     * @return calculate the total value of the power.
     */
    public double calculateTotalPower() {
        return 0;
    }

}
