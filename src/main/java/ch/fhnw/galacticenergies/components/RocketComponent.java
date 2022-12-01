package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;
import javafx.util.Duration;

public class RocketComponent extends Component {

    private static final int ROCKET_SPEED = 1000;

    private static final float SPEED_DECAY = 0.66f;

    private Effect blur = new MotionBlur();

    private float speed = 0;

    private Vec2 velocity = new Vec2();

    private Texture textureOnHit;

    public RocketComponent(Texture textureOnHit) {
        this.textureOnHit = textureOnHit;
    }

    @Override
    public void onUpdate(double tpf) {
        speed = ROCKET_SPEED * (float) tpf;

        velocity.mulLocal(SPEED_DECAY);
    }

    public void up() {
        velocity.set(-speed, 0);
        applyMoveEffects();
    }

    public void down() {
        velocity.set(speed, 0);
        applyMoveEffects();
    }

    private void applyMoveEffects()
    {
        entity.setScaleY(1.05);
        entity.setScaleX(1 / entity.getScaleY());
        entity.getViewComponent().getParent().setEffect(blur);
    }

    public void stop() {
        entity.setScaleX(1);
        entity.setScaleY(1);
        entity.getViewComponent().getParent().setEffect(null);
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
