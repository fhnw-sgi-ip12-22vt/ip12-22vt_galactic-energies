package ch.fhnw.galacticenergies.menu;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.animation.FadeTransition;
import javafx.geometry.Point2D;
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
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

/**
 * Creates the Main Menu
 * @Version 1.0
 */

public class MainMenu extends FXGLMenu {

    private Animation<?> animation;
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

        Button btnIntro = new Button("Intro");
        btnIntro.getStyleClass().add("main_menu_button");
        btnIntro.setOnAction(e -> IntroScene.start());

        VBox buttonVBox = new VBox(5, btnPlay, btnLeaderboard, btnExit);
        buttonVBox.setPrefWidth(getAppWidth()); buttonVBox.setAlignment(Pos.BOTTOM_LEFT); buttonVBox.setTranslateY(getAppHeight() * 0.6);
        getContentRoot().getChildren().addAll(bg, titleHBox, buttonVBox);





        animation = FXGL.animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();

    }

    @Override
    public void onCreate() {
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
    }

    @Override
    protected void onUpdate(double tpf) {

        animation.onUpdate(tpf);
    }
}




