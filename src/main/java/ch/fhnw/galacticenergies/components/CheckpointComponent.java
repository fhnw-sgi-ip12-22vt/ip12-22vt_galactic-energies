package ch.fhnw.galacticenergies.components;


    import ch.fhnw.galacticenergies.controllers.RocketController;
    import ch.fhnw.galacticenergies.events.GameEvent;
    import com.almasb.fxgl.core.math.FXGLMath;
    import com.almasb.fxgl.core.math.Vec2;
    import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
    import com.almasb.fxgl.entity.Entity;
    import com.almasb.fxgl.entity.component.Component;
    import com.almasb.fxgl.particle.ParticleComponent;
    import com.almasb.fxgl.particle.ParticleEmitter;
    import com.almasb.fxgl.particle.ParticleEmitters;
    import com.almasb.fxgl.physics.PhysicsComponent;
    import com.almasb.fxgl.texture.Texture;
    import com.almasb.fxgl.time.LocalTimer;
    import javafx.geometry.Point2D;
    import javafx.util.Duration;

    import java.util.ArrayList;
    import java.util.Random;

    import static com.almasb.fxgl.dsl.FXGL.*;
    import static java.lang.Math.abs;
    import static java.lang.Math.signum;


public class CheckpointComponent extends Component {

    ArrayList<Texture> asteroidImages = new ArrayList<>();
    private static final int BALL_MIN_SPEED = 400;
    PhysicsComponent physics;
    private Vec2 velocity = new Vec2();
    private static final float BOUNCE_FACTOR = 1.2f;

    private float r1;
    private float r2;

    private int x;
    private int y;

    public CheckpointComponent()
    {

        for (int i = 1; i < 5; i++)
        {
            asteroidImages.add(texture("asteroids/Enemy" + i + ".png"));
        }

        Random random = new Random();
        r1 = random.nextFloat(-2, -1);
        r2 = random.nextFloat(-2, 2);

    }

    @Override
    public void onUpdate(double tpf) {
        checkOffscreen();

        if(entity.getX() < 10) {
            entity.removeFromWorld();
            spawn("asteroid");
        }

    }


    public void hit() {
        entity.removeFromWorld();

        fire(new GameEvent(GameEvent.ASTEROID_GOT_HIT));
    }

    private void checkOffscreen() {
        if (getEntity().getBoundingBoxComponent().isOutside(getGameScene().getViewport().getVisibleArea())) {
            physics.overwritePosition(new Point2D(
                getAppWidth() / 2,
                getAppHeight() / 2
            ));
        }
    }
    public int x() {


        return 0;
    }

    public int y() {


        return 0;
    }

}
