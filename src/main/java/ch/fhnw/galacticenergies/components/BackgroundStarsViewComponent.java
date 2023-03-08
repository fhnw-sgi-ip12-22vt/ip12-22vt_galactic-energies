package ch.fhnw.galacticenergies.components;

import ch.fhnw.galacticenergies.controllers.RocketController;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.view.ChildViewComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.run;

/** class builds the background of the game which moves
 * @version 1.0
 */
public class BackgroundStarsViewComponent extends ChildViewComponent {

    private Texture[] stars;
    public BackgroundStarsViewComponent(Texture... stars)
    {
        this.stars = stars;

        for(Texture t : stars)
        {
            getViewRoot().getChildren().add(t);
        }

        run(() -> onUpdate(0.016), Duration.seconds(0.016));
    }

    /**
     * This method updates the position of the stars by looping through
     * each Texture object in the stars list and calling the move() method on it,
     * passing in the time since the last frame as a parameter.
     * @param tpf time per frame
     */
    @Override
    public void onUpdate(double tpf) {
        for(Texture t : stars)
        {
            move(t, tpf);
        }
    }

    /**
     * This method suggests that the Texture object represents a single star in a starfield,
     * and that the move() method is responsible for animating the star by updating its position every frame.
     * @param star
     * @param tpf
     */
    private void move(Texture star, double tpf)
    {
        float multiplier = RocketController.getRocketControl().getSpeedMultiplier();
        star.setTranslateX(star.getTranslateX() - (tpf + multiplier * 0.05) * FXGLMath.random(1, 30) );

        if(star.getTranslateX() + star.getWidth() <= getAppWidth()){
            star.setTranslateX(0);
        }
    }
}
