package ch.fhnw.galacticenergies.services;

import javafx.beans.property.DoubleProperty;

public class WindowService {

    private static double windowHeight;

    public static double getWindowHeight() {
        return windowHeight;
    }

    public static void setWindowHeight(double windowHeight) {
        WindowService.windowHeight = windowHeight;
    }
}
