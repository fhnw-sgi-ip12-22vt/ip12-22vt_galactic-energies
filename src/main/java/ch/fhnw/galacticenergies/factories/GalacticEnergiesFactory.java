package ch.fhnw.galacticenergies.factories;

import ch.fhnw.galacticenergies.components.BackgroundStarsViewComponent;
import ch.fhnw.galacticenergies.components.RocketComponent;
import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.image.Image;

import static com.almasb.fxgl.dsl.FXGL.*;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.*;
public class GalacticEnergiesFactory implements EntityFactory {

    private LazyValue<Image> image;

    public GalacticEnergiesFactory()
    {
//        this.image = new LazyValue<>(() -> {
//            var images = IntStream.rangeClosed(1, 4)
//                    .mapToObj(i -> image("ch/fhnw/galacticenergies/images/Enemy" + i + ".png"))
//                    .collect(Collectors.toList());
//            return ImagesKt.merge(images);
//        });
    }

    @Spawns("background")
    public Entity newBackground(SpawnData data)
    {
        return entityBuilder(data)
                .type(BACKGROUND)
                .with(new IrremovableComponent())
                .with(new BackgroundStarsViewComponent(
                        texture("bg/SpaceBackground.jpg")
                ))
                .build();
    }

    @Spawns("rocket")
    public Entity newRocket(SpawnData data)
    {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        Texture texture = texture("Rakete.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(100);

        return entityBuilder(data)
                .type(ROCKET)
                .at(20, getAppHeight() / 2 - 50)
                .viewWithBBox(texture)
                .collidable()
                .with(physics)
                .with(new EffectComponent())
                .with(new RocketComponent(texture("bat_hit.png")))
                .build();
    }
}
