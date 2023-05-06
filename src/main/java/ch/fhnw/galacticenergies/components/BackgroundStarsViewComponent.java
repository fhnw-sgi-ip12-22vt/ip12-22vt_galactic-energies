package ch.fhnw.galacticenergies.components;

import ch.fhnw.galacticenergies.controllers.SpeedController;
import com.almasb.fxgl.dsl.components.view.ChildViewComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.random;
import static com.almasb.fxgl.dsl.FXGL.run;

/**
 * @Version 1.0
 * <p>
 * Initializes and moves the Background
 */

public class BackgroundStarsViewComponent extends ChildViewComponent {


    private final ArrayList<Circle> stars = new ArrayList<>();

    /**
     * First initiation of the Stars
     */
    public BackgroundStarsViewComponent() {

        for (int i = 0; i < 100; i++) {
            Circle currStar = new Circle(random(0, getAppWidth()), random(0, getAppHeight()), 1, Color.WHITE);
            stars.add(currStar);
            getViewRoot().getChildren().add(currStar);
        }

        run(() -> onUpdate(0.016), Duration.seconds(0.016));
    }

    /**
     * On update all stars are moved
     *
     * @param tpf Time per Frame
     */
    @Override
    public void onUpdate(double tpf) {
        for (Circle star : stars) {
            move(star);
        }
    }

    /**
     * Moves each Star to the left if the left border is reached the star is reset to the right borders
     *
     * @param star current Star which is being moved
     */
    private void move(Circle star) {

        star.setCenterX(star.getCenterX() - 0.341562 * SpeedController.getSpeed());
        if (star.getCenterX() <= 0) {
            star.setCenterX(getAppWidth());
        }
    }
}
