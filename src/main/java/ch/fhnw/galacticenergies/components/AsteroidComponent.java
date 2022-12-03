package ch.fhnw.galacticenergies.components;

import ch.fhnw.galacticenergies.events.GameEvent;
import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.dsl.FXGL.*;

public class AsteroidComponent extends Component {

    public void hit() {
        entity.removeFromWorld();

        fire(new GameEvent(GameEvent.ASTEROID_GOT_HIT));
    }
}
