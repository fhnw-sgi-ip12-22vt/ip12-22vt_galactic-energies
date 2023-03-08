package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

/** shows the lives of the player
 * @version 1.0
 */
public class LifeComponent extends Component {
    public void setPosition(double posX)
    {
        entity.setX(posX);
    }
}
