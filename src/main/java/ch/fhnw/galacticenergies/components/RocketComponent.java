package ch.fhnw.galacticenergies.components;

import ch.fhnw.galacticenergies.Config;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class RocketComponent extends Component {

    private static final float SPEED_DECAY = 0.66f;

    private static final float BOUNCE_FACTOR = 0.2f;

    private PhysicsComponent physics;

    private MotionBlur blur = new MotionBlur();

    private float speed = 0;

    private boolean canShoot = true;
    private double lastShot = 0;

    private Vec2 velocity = new Vec2();

    private Texture textureOnHit;

    public RocketComponent(Texture textureOnHit) {
        this.textureOnHit = textureOnHit;
    }

    @Override
    public void onUpdate(double tpf) {
        speed = Config.ROCKET_SPEED * (float) tpf;

        velocity.mulLocal(SPEED_DECAY);

        if (entity.getY() < 0) {
            velocity.set(0, BOUNCE_FACTOR * (float) + entity.getY());
        } else if (entity.getBottomY() > getAppHeight()) {
            velocity.set(0, BOUNCE_FACTOR * (float) + (entity.getBottomY() - getAppHeight()));
        }

        if (!canShoot) {
            if ((getGameTimer().getNow() - lastShot) >= 1.0 / Config.ROCKET_ATTACK_SPEED) {
                canShoot = true;
            }
        }

        physics.setBodyLinearVelocity(velocity);
    }

    public void up() {
        velocity.set(0, speed);
        applyMoveEffects();
    }

    public void down() {
        velocity.set(0, -speed);
        applyMoveEffects();
    }

    private void applyMoveEffects()
    {
        entity.setScaleX(1.05);
        entity.setScaleY(1 / entity.getScaleY());
        blur.setRadius(3);
        entity.getViewComponent().getParent().setEffect(blur);
    }

    public void stop() {
        entity.setScaleX(1);
        entity.setScaleY(1);
        entity.getViewComponent().getParent().setEffect(null);
    }

    public void shoot()
    {
        if(!canShoot) return;

        canShoot = false;
        lastShot = getGameTimer().getNow();

        spawn("rocketBullet", new SpawnData(0,0).put("owner", getEntity()));
    }

    public void onHit() {
        entity.getComponent(EffectComponent.class).startEffect(new HitEffect());
    }

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
