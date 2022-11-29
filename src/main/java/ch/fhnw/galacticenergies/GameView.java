package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.controllers.GameViewController;
import ch.fhnw.galacticenergies.controllers.PlayerController;
import ch.fhnw.galacticenergies.models.Player;
import ch.fhnw.galacticenergies.services.WindowService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
        scene.setOnKeyPressed(e -> {
            Player p = PlayerController.p;
            if(e.getCode() == KeyCode.W) {
                p.moveUp();
                controller.updateSpaceShipY();
            } else if (e.getCode() == KeyCode.S) {
                p.moveDown();
                controller.updateSpaceShipY();
            }

        });
        stage.setTitle("Galactic Energies");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();

        WindowService.setWindowHeight(stage.getHeight());
    }

    public static void open() {
        GameView.launch();
    }
}
