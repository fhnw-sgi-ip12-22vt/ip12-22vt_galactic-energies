package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.entity.component.Component;

/**
 * shows the lives of the player
 *
 * @version 1.0
 */
public class LifeComponent extends Component {
    public void setPosition(double posX) {
        entity.setX(posX);
    }
}
