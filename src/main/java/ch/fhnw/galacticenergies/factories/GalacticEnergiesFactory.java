package ch.fhnw.galacticenergies.factories;

import ch.fhnw.galacticenergies.components.*;
import ch.fhnw.galacticenergies.services.SemiRingService;
import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.ImagesKt;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.almasb.fxgl.dsl.FXGL.*;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.*;

public class GalacticEnergiesFactory implements EntityFactory {

    private LazyValue<Image> image;

    public GalacticEnergiesFactory() {
        this.image = new LazyValue<>(() -> {
            var images = IntStream.rangeClosed(1, 13)
                    .mapToObj(i -> image("dashboard/Steuerboard Level " + i + ".png"))
                    .collect(Collectors.toList());
            return ImagesKt.merge(images);
        });
    }

    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return entityBuilder(data)
                .type(BACKGROUND)
                .with(new IrremovableComponent())
                .with(new BackgroundStarsViewComponent(
//                        texture("bg/bg_blue1.png"),
                        texture("bg/ColorBlend.png"),
                        texture("bg/Stars3.png")
//                        texture("bg/StarsBackground1.png"),
//                        texture("bg/StarsBackground2.png")
                ))
                .build();
    }

    @Spawns("rocket")
    public Entity newRocket(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        Texture texture = texture("Rocket.png");
        texture.setPreserveRatio(true);
        texture.setFitWidth(70);

        return entityBuilder(data)
                .type(ROCKET)
                .at(10, getAppHeight() / 2 - (texture.getFitHeight() / 2))
                .viewWithBBox(texture)
                .collidable()
                .with(physics)
                .with(new EffectComponent())
                .with(new RocketComponent(texture("bat_hit.png")))
                .build();
    }

    @Spawns("dashboard")
    public Entity newDashboard(SpawnData data) {
        Texture texture = texture("dashboard/Steuerboard Level 1.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(100);


        StackPane s = new StackPane();
        s.getChildren().add(texture);
        StackPane.setAlignment(texture, Pos.BOTTOM_CENTER);


        return entityBuilder(data)
                .type(DASHBOARD)
                .at(getAppWidth() / 2 - 100, getAppHeight() - texture.getFitHeight() )
                .view(s)
                .with(new DashboardComponent())
                .build();
    }


    @Spawns("life")
    public Entity newLive(SpawnData data) {
        Texture textureLife = texture("heart.png");
        textureLife.setFitWidth(20);
        textureLife.setFitHeight(20);

        return entityBuilder(data)
                .type(LIFE)
                .at(getAppWidth(), 10)
                .viewWithBBox(textureLife)
                .with(new LifeComponent())
                .build();
    }

    @Spawns("arrows")
    public Entity newArrows(SpawnData data)
    {
        Texture texture = texture("dashboard/Pfeil Neutral.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(20);

        return entityBuilder(data)
                .type(ARROWS)
                .at(getAppWidth() / 2 - ArrowsComponent.getBUTTON_SIZE(), getAppHeight() - ArrowsComponent.getBUTTON_SIZE() - 5)
                .with(new ArrowsComponent())
                .build();
    }
}
