package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.entity.component.Component;


public class LifeComponent extends Component {
    public void setPosition (double posX) {
        entity.setX(posX);
    }
}
