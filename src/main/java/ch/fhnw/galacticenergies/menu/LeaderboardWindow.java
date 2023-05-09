package ch.fhnw.galacticenergies.menu;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.ui.MDIWindow;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGLForKtKt.addUINode;

/**
 * Shows how to use MDIWindow.
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class LeaderboardWindow extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
    }

    @Override
    protected void initGame() {
        var defaultWindow = new MDIWindow();
        defaultWindow.setPrefSize(200, 150);
        defaultWindow.relocate(50, 50);

        addUINode(defaultWindow);

        var noDecorWindow = new MDIWindow();
        noDecorWindow.setCloseable(false);
        noDecorWindow.setMinimizable(false);
        noDecorWindow.setPrefSize(200, 150);
        noDecorWindow.relocate(350, 50);

        addUINode(noDecorWindow);

        var notResizableWindow = new MDIWindow();
        notResizableWindow.setManuallyResizable(false);
        notResizableWindow.setPrefSize(200, 150);
        notResizableWindow.relocate(650, 50);
        notResizableWindow.setBackground(Background.fill(Color.WHITE));


        Pane content = new Pane();
        Label text = new Label();
        text.setWrapText(true);
        text.setText("text text text text text text text text text text text");

        content.getChildren().add(text);
        notResizableWindow.setContentPane(content);

        addUINode(notResizableWindow);

        var noMoveWindow = new MDIWindow();
        noMoveWindow.setMovable(false);
        noMoveWindow.setPrefSize(200, 150);
        noMoveWindow.relocate(950, 50);

        addUINode(noMoveWindow);
    }
}


