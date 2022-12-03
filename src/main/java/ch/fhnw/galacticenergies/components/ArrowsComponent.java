package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.texture.Texture;

import static com.almasb.fxgl.dsl.FXGL.texture;

public class ArrowsComponent extends Component {
    private final Texture textureUp = texture("dashboard/Pfeil Hoch.png");
    private final Texture textureDown = texture("dashboard/Pfeil Down.png");
    private final Texture textureNone = texture("dashboard/Pfeil Neutral.png");

    private static final double BUTTON_SIZE = 50;

    public ArrowsComponent()
    {
        textureDown.setPreserveRatio(true);
        textureDown.setFitHeight(BUTTON_SIZE);

        textureUp.setPreserveRatio(true);
        textureUp.setFitHeight(BUTTON_SIZE);

        textureNone.setPreserveRatio(true);
        textureNone.setFitHeight(BUTTON_SIZE);
    }
    @Override
    public void onUpdate(double tpf) {

    }

    public void buttonUpPressed()
    {
        if(entity.getViewComponent().getChildren().contains(textureUp)) return;

        entity.getViewComponent().removeChild(textureNone);
        entity.getViewComponent().addChild(textureUp);

    }

    public void buttonDownPressed()
    {
        if(entity.getViewComponent().getChildren().contains(textureDown)) return;
        entity.getViewComponent().removeChild(textureNone);
        entity.getViewComponent().addChild(textureDown);
    }

    public void noButtonPressed()
    {
        entity.getViewComponent().removeChild(textureDown);
        entity.getViewComponent().removeChild(textureUp);

        entity.getViewComponent().addChild(textureNone);
    }

    public static double getBUTTON_SIZE() {
        return BUTTON_SIZE;
    }
}
