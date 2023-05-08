package ch.fhnw.galacticenergies.menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;


public class MainMenu extends FXGLMenu {
    public MainMenu (MenuType type) {
        super(type);

        Texture bg = getAssetLoader().loadTexture("bg/SpaceBackground.jpg");
        bg.setFitWidth(getAppWidth());
        bg.setFitHeight(getAppHeight());

        Label title = new Label("Galactic Energies");
        title.setFont(Font.font("Broadway",48));
        title.setTextFill(Color.WHITE);

        HBox titleHBox = new HBox(title);
        titleHBox.setPrefWidth(getAppWidth());
        titleHBox.setAlignment(Pos.CENTER); titleHBox.setTranslateY(50);

        Button btnPlay = new Button("Play");
        btnPlay.getStyleClass().add("main_menu_button");
        // fireNewGame() clears the Scene and calls initGame(), to spawn all entities.
        btnPlay.setOnAction(e -> fireNewGame());

        Button btnLeaderboard = new Button("Leaderboard");
        btnLeaderboard.getStyleClass().add("main_menu_button");
        Button btnExit = new Button("Exit");
        btnExit.getStyleClass().add("main_menu_button");
        btnExit.setOnAction(e -> fireExit());

        VBox buttonVBox = new VBox(5, btnPlay, btnLeaderboard, btnExit);
        buttonVBox.setPrefWidth(getAppWidth()); buttonVBox.setAlignment(Pos.BOTTOM_LEFT); buttonVBox.setTranslateY(getAppHeight() * 0.6);
        getContentRoot().getChildren().addAll(bg, titleHBox, buttonVBox);

       // mainButton newGame = new mainButton("New Game", this::fireNewGame);

       //getContentRoot().getChildren().addAll(createBackground(getAppWidth(),getAppHeight()));

    }



    protected Node createBackground(double w, double h){
        return FXGL.texture("bg/SpaceBackground.jpg",w,h);
    }

  /*  public static final class mainButton extends StackPane{
        private String name;
        private Runnable action;

        public mainButton(String name, Runnable action){
            this.action = action;
            this.name = name;

            setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER){
                    action.run();
                }
            });

        }
    }*/

   //
}



