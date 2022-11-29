package ch.fhnw.galacticenergies.services;

public class WindowService {

    static double windowHeight;

    public static double getWindowHeight() {
        return windowHeight;
    }

    public static void setWindowHeight(double windowHeight) {
        WindowService.windowHeight = windowHeight;
    }
}
