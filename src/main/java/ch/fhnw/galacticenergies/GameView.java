package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.controllers.GameViewController;
import ch.fhnw.galacticenergies.controllers.SpeedController;
import ch.fhnw.galacticenergies.services.WindowService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Creation of the stage with the movement of the spaceship (up and down).
 */

public class GameView extends Application {

    private static Stage mainStage;

    public static Stage getStage() {
        return mainStage;
    }
    @Override
    public void start(Stage stage) throws Exception {

        mainStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(GameView.class.getResource("game-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        GameViewController controller = fxmlLoader.getController();
        stage.setTitle("Galactic Energies");
        //stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        controller.initPlayer();
        controller.showSpeed(1);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W -> {
                    controller.moveUp();
                }
                case S -> {
                    controller.moveDown();
                }
                case T -> {
                    SpeedController.speedUp();
                    controller.showSpeed(SpeedController.getSpeed());
                }
                case Z -> {
                    SpeedController.speedDown();
                    controller.showSpeed(SpeedController.getSpeed());
                }
            }
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            WindowService.setWindowHeight((Double) newVal);
        });
    }
}
