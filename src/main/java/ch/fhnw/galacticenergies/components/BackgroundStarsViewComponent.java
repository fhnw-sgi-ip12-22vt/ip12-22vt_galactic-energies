package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.view.ChildViewComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.run;
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

    @Override
    public void onUpdate(double tpf) {
        for(Texture t : stars)
        {
            move(t, tpf);
        }
    }

    private void move(Texture star, double tpf)
    {
        star.setTranslateX(star.getTranslateX() - tpf * FXGLMath.random(1, 30));

        if(star.getTranslateX() + star.getWidth() <= getAppWidth()){
            star.setTranslateX(0);
        }
    }
}
