package ch.fhnw.galacticenergies;

import ch.fhnw.galacticenergies.controllers.GameViewController;
import ch.fhnw.galacticenergies.controllers.PlayerController;
import ch.fhnw.galacticenergies.models.Player;
import ch.fhnw.galacticenergies.services.WindowService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
            Player p = PlayerController.getPlayer();
            switch (e.getCode()) {
                case W -> {
                    //p.moveUp();
                    controller.moveUp();
                }
                case S -> {
                    //p.moveDown();
                    controller.moveDown();
                }
            }

        });
        stage.setTitle("Galactic Energies");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            WindowService.setWindowHeight((Double) newVal);
        });
    }

    public static void open() {
        GameView.launch();
    }
}
