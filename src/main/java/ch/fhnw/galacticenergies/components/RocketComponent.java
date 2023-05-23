package ch.fhnw.galacticenergies.components;

import ch.fhnw.galacticenergies.Config;
import ch.fhnw.galacticenergies.controllers.SpeedController;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.effect.MotionBlur;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

/**
 * The class RocketComponent implements the Rocket
 * @version 1.0
 */
@SuppressWarnings("unused")
public class RocketComponent extends Component {

    private static final float SPEED_DECAY = 0.66f;

    private static final float BOUNCE_FACTOR = 0.2f;
    private final MotionBlur blur = new MotionBlur();
    private final Vec2 velocity = new Vec2();
    private final Texture textureOnHit;
    private PhysicsComponent physics;
    private float speed = 1;
    private boolean canShoot = true;

    public RocketComponent(Texture textureOnHit) {
        this.textureOnHit = textureOnHit;
    }

    /**
     * Define what happens on each update
     * @param tpf TickPerFrame
     */
    @Override
    public void onUpdate(double tpf) {
        float speedMultiplier = 1;
        speed = Config.ROCKET_SPEED * (float) tpf * speedMultiplier;
        velocity.mulLocal(SPEED_DECAY);

        if (entity.getY() < 0) {
            velocity.set(0, BOUNCE_FACTOR * (float) entity.getY());
        } else if (entity.getBottomY() > getAppHeight()) {
            velocity.set(0, BOUNCE_FACTOR * (float) (entity.getBottomY() - getAppHeight()));
        }

        if (!canShoot) {
            double lastShot = 0;
            if ((getGameTimer().getNow() - lastShot) >= 1.0 / Config.ROCKET_ATTACK_SPEED) {
                canShoot = true;
            }
        }

        physics.setBodyLinearVelocity(velocity);
    }

    /**
     * Move the Rocket down
     */
    public void up() {
        if(SpeedController.getSpeed() < 1){
            return;
        }
        velocity.set(0, speed);
        applyMoveEffects();
    }
    /**
     * Move the Rocket up
     */
    public void down() {
        if(SpeedController.getSpeed() < 1){
            return;
        }
        velocity.set(0, -speed);
        applyMoveEffects();
    }

    /**
     * Apply the move effect
     */
    private void applyMoveEffects() {
        entity.setScaleX(1.05);
        entity.setScaleY(1 / entity.getScaleY());
        blur.setRadius(3);
        entity.getViewComponent().getParent().setEffect(blur);
    }

    /**
     * Stop the Rocket
     */
    public void stop() {
        entity.setScaleX(1);
        entity.setScaleY(1);
        entity.getViewComponent().getParent().setEffect(null);
    }


    /**
     * Show a Hit effect
     */
    public class HitEffect extends com.almasb.fxgl.dsl.components.Effect {

        public HitEffect() {
            super(Duration.seconds(1));
        }

        @Override
        public void onStart(Entity entity) {
            entity.getViewComponent().addChild(textureOnHit);
        }

        @Override
        public void onEnd(Entity entity) {
            entity.getViewComponent().removeChild(textureOnHit);
        }
    }
}
