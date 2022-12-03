package ch.fhnw.galacticenergies.components;

import ch.fhnw.galacticenergies.Config;
import ch.fhnw.galacticenergies.enums.GalacticEnergiesType;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;

@Required(OwnerComponent.class)
public class RocketBulletComponent extends Component {

    private OwnerComponent owner;

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(tpf* Config.ROCKET_BULLET_SPEED);
    }
}
