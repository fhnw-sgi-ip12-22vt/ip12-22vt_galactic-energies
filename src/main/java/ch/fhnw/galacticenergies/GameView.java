//package ch.fhnw.galacticenergies;
//
//import ch.fhnw.galacticenergies.controllers.GameViewController;
//import ch.fhnw.galacticenergies.controllers.PlayerController;
//import ch.fhnw.galacticenergies.models.Player;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.input.KeyCode;
//import javafx.stage.Stage;
//
//public class GameView extends Application {
//    @Override
//    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(GameView.class.getResource("game-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        scene.setOnKeyPressed(e -> {
//            Player p = PlayerController.p;
//            if(e.getCode() == KeyCode.W) {
//                p.moveUp();
//                GameViewController.updateSpaceShipY(p.getPos_y());
//            } else if (e.getCode() == KeyCode.S) {
//                p.moveDown();
//                GameViewController.updateSpaceShipY(p.getPos_y());
//            }
//
//        });
//        stage.setMaximized(true);
//        stage.setTitle("Galactic Energies");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void open() {
//        GameView.launch();
//    }
//}
