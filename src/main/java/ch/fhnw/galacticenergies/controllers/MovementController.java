package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.View;
import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.components.DashboardComponent;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ARROWS;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.DASHBOARD;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.isReleaseMode;
import static com.almasb.fxgl.dsl.FXGL.onKeyDown;

/**
 * Defines the movement of the rocket with keys.
 *
 * @version 1.0
 */
public class MovementController {

    /**
     * Moves rocket up and down with a key.
     */
    public static void movement() {
        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                if (ViewController.isPaused()) {
                    return;
                }

                RocketController.getRocketControl().down();
                getArrowsControl().buttonDownPressed();
            }

            @Override
            protected void onActionEnd() {
                RocketController.getRocketControl().stop();
                getArrowsControl().noButtonPressed();
            }
        }, KeyCode.DOWN);

        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                if (ViewController.isPaused()) {
                    return;
                }

                RocketController.getRocketControl().up();
                getArrowsControl().buttonUpPressed();
            }

            @Override
            protected void onActionEnd() {
                RocketController.getRocketControl().stop();
                getArrowsControl().noButtonPressed();
            }
        }, KeyCode.UP);

        if (!isReleaseMode()) {
            onKeyDown(KeyCode.L, "Next Level", View.levelController::nextLevel);
        }
    }

    static DashboardComponent getDashboardControl() {
        return getGameWorld().getSingleton(DASHBOARD).getComponent(DashboardComponent.class);
    }

    private static ArrowsComponent getArrowsControl() {
        return getGameWorld().getSingleton(ARROWS).getComponent(ArrowsComponent.class);
    }
}
