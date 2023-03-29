package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.components.DashboardComponent;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ARROWS;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.DASHBOARD;
import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Defines the movement of the rocket with keys.
 * @version 1.0
 */
public class MovementControllerDEV {

    public MovementControllerDEV(){

    }

    /**
     * Moves rocket up and down with a key.
     */
    public static void movement() {
        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                RocketController.getRocketControl().down();
                getArrowsControl().buttonDownPressed();
            }

            @Override
            protected void onActionEnd() {
                RocketController.getRocketControl().stop();
                getArrowsControl().noButtonPressed();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                RocketController.getRocketControl().up();
                getArrowsControl().buttonUpPressed();
            }

            @Override
            protected void onActionEnd() {
                RocketController.getRocketControl().stop();
                getArrowsControl().noButtonPressed();
            }
        }, KeyCode.W);


//        onKeyDown(KeyCode.K, "nextSpeed", () -> {
//            if (geti("speed") == 12) return;
//            System.out.println(geti("speed"));
//            inc("speed", +1);
//            RocketController.getRocketControl().setSpeedMultiplier(1 + (float) (geti("speed")) / 10);
//            getDashboardControl().setSpeedImage(geti("speed"));
//        });
        if (!isReleaseMode()) {
            onKeyDown(KeyCode.L, "Next Level", LevelController::nextLevel);
        }


    }
    static DashboardComponent getDashboardControl() {
        return getGameWorld().getSingleton(DASHBOARD).getComponent(DashboardComponent.class);
    }

    private static ArrowsComponent getArrowsControl() {
        return getGameWorld().getSingleton(ARROWS).getComponent(ArrowsComponent.class);
    }
}
