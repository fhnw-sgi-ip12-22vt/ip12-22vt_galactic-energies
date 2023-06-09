package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;

import static com.almasb.fxgl.dsl.FXGL.texture;

/**
 * The class ArrowComponent implements arrows which show the flight direction of the rocket
 *
 * @version 1.0
 */
public class ArrowsComponent extends Component {
    private static final double BUTTON_SIZE = 50;
    private final Texture textureUp = texture("dashboard/Pfeil Hoch.png");
    private final Texture textureDown = texture("dashboard/Pfeil Down.png");
    private final Texture textureNone = texture("dashboard/Pfeil Neutral.png");

    /**
     * shows defined arrow button
     */
    public ArrowsComponent() {
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

    /**
     * This method shows how the up arrow behaves when the corresponding button is pressed
     */
    public void buttonUpPressed() {
        if (entity.getViewComponent().getChildren().contains(textureUp)) {
            return;
        }

        //entity.getViewComponent().removeChild(textureNone);
        entity.getViewComponent().removeChild(textureNone);
        entity.getViewComponent().removeChild(textureDown);
        entity.getViewComponent().addChild(textureUp);

    }

    /**
     * This method shows how the down arrow behaves when the corresponding button is pressed
     */
    public void buttonDownPressed() {
        if (entity.getViewComponent().getChildren().contains(textureDown)) {
            return;
        }
        //entity.getViewComponent().removeChild(textureNone);
        entity.getViewComponent().removeChild(textureNone);
        entity.getViewComponent().removeChild(textureUp);
        entity.getViewComponent().addChild(textureDown);
    }

    /**
     * This method shows how the arrows behave when no button is pressed
     */
    public void noButtonPressed() {
        if (entity.getViewComponent().getChildren().contains(textureNone)) {
            return;
        }

        entity.getViewComponent().addChild(textureNone);
    }
}
