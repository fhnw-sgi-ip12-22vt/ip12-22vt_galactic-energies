package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.data.PowerInput;
import java.util.*;

public class PowerController {

	private static double currentPower;
	private static double totalPower;
	private static int lastTimestamp = 0;

	public static void calcPower(){
		int currentTimestamp = 0;
		String currentPowerStr;
		List <List> input = new ArrayList<>();
		while(1==1) {
			input = PowerInput.readCSV();
			input.remove(0);
			currentPowerStr = (String) input.get(input.size() - 1).get(7);
			setCurrentPower(Double.valueOf(currentPowerStr));

			for (int i = 0; i < input.size(); i++) {
				currentTimestamp = Integer.valueOf((String) input.get(i).get(2));
				if (currentTimestamp > lastTimestamp) {
					addTotalPower(Double.valueOf((String) input.get(i).get(7)));
					lastTimestamp = currentTimestamp;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}
	}



	public static double getCurrentPower() {
		return currentPower;
	}

	public static void setCurrentPower(double currentPower) {
		PowerController.currentPower = currentPower;
	}

	/**
	 *
	 * @return return the total value of the power.
	 */
	public static void addTotalPower(double power) {
		totalPower += power;

	}
	public static double getTotalPower() {

		return totalPower;
	}

	/**
	 *
	 * @return calculate the total value of the power.
	 */
	public double calculateTotalPower() {
		return 0;
	}

}
