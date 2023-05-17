package ch.fhnw.galacticenergies.factories;

import ch.fhnw.galacticenergies.components.ArrowsComponent;
import ch.fhnw.galacticenergies.components.AsteroidComponent;
import ch.fhnw.galacticenergies.components.BackgroundStarsViewComponent;
import ch.fhnw.galacticenergies.components.CheckpointComponent;
import ch.fhnw.galacticenergies.components.DashboardComponent;
import ch.fhnw.galacticenergies.components.LifeComponent;
import ch.fhnw.galacticenergies.components.RocketComponent;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ARROWS;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ASTEROID;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.BACKGROUND;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.DASHBOARD;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.LIFE;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.PLANET;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ROCKET;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppCenter;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.texture;

public class GalacticEnergiesFactory implements EntityFactory {

    /**
     * define constructor for GalacticEnergiesFactory class
     */
    public GalacticEnergiesFactory() {

    }

    /**
     * define method for spawning background entities
     *
     * @param data
     * @return
     */
    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return entityBuilder(data)
            .type(BACKGROUND)
            .with(new IrremovableComponent())
            .with(new BackgroundStarsViewComponent(
            ))
            .build();
    }

    /**
     * define method for spawning rocket entities
     *
     * @param data
     * @return
     */
    @Spawns("rocket")
    public Entity newRocket(SpawnData data) {
        // create a new entity with specified components and properties
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

    /**
     * define method for spawning dashboard entities
     *
     * @param data
     * @return
     */
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
            .at(getAppCenter().getY(), getAppCenter().getX() + texture.getFitHeight())
            .view(s)
            .with(new DashboardComponent())
            .build();
    }

    /**
     * define method for spawning life entities
     *
     * @param data
     * @return
     */
    @Spawns("life")
    public Entity newLive(SpawnData data) {
//        Texture textureLife = texture("heart.png");
//        textureLife.setFitWidth(20);
//        textureLife.setFitHeight(20);
        Rectangle live = new Rectangle();
        live.setWidth(20);
        live.setHeight(10);
        live.setFill(Color.WHITE);

        return entityBuilder(data)
            .type(LIFE)
            .at(getAppWidth(), 10)
            .view(live)
            .with(new LifeComponent())
            .build();
    }

    @Spawns("arrows")
    public Entity newArrows(SpawnData data) {
        Texture texture = texture("dashboard/Pfeil Neutral.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(20);
        texture.setFitWidth(20);
        return entityBuilder(data)
            .type(ARROWS)
            .at(getAppWidth() / 2 - texture.getFitWidth() / 1.5, getAppHeight() - texture.getHeight() / 2)
            .with(new ArrowsComponent())
            .build();
    }

    private DashboardComponent getDashboardControl() {
        return getGameWorld().getSingleton(DASHBOARD).getComponent(DashboardComponent.class);
    }

    @Spawns("asteroid")
    public Entity newAsteroid(SpawnData data) {
        Random r = new Random();
        Texture texture = texture("asteroids/Enemy" + r.nextInt(1, 4) + ".png");
        texture.setScaleX(0.5);
        texture.setScaleY(0.5);
        Point2D velocity = new Point2D(r.nextFloat(-100, -75),
            r.nextFloat(0, 25));

        AsteroidComponent asteroidComponent = new AsteroidComponent();
        asteroidComponent.setVelocity(velocity);

        return entityBuilder(data)
            .type(ASTEROID)
            .at(getAppWidth() + texture.getFitWidth(), r.nextFloat(0, getAppHeight()))
            .viewWithBBox(texture)
            .with(asteroidComponent)
            .scale(0.5, 0.5)
            .collidable()
            .with(new OffscreenCleanComponent())
            .build();
    }

    @Spawns("planet")
    public Entity newPlanet(SpawnData data) {
        Random r = new Random();
        Texture texture = texture("planet/planet" + r.nextInt(1, 9) + ".png");
        texture.setPreserveRatio(true);
        texture.setFitWidth(texture.getHeight());

        Random random = new Random();

        Point2D velocity = new Point2D(r.nextFloat(-100, -75),
            r.nextFloat(0, 25));

        CheckpointComponent checkpointComponent = new CheckpointComponent();
        checkpointComponent.setVelocity(velocity);

        return entityBuilder(data)
            .type(PLANET)
            .at(getAppWidth() + texture.getFitWidth(), random.nextFloat(0, getAppHeight()))
            .viewWithBBox(texture)
            .collidable()
            .with(checkpointComponent)
            .with(new OffscreenCleanComponent())
            .scale(0.25, 0.25)
            .build();
    }

}
