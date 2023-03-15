package ch.fhnw.galacticenergies.components;

import ch.fhnw.galacticenergies.events.GameEvent;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Random;
import static com.almasb.fxgl.dsl.FXGL.fire;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class CheckpointComponent extends Component {
        ArrayList<Texture> planetImages = new ArrayList<>();
        private static final int BALL_MIN_SPEED = 400;
        PhysicsComponent physics;
        private Vec2 velocity = new Vec2();
        private static final float BOUNCE_FACTOR = 1.2f;

        private float r1;
        private float r2;

        public CheckpointComponent() {

            for (int i = 1; i < 10; i++)
            {
                planetImages.add(texture("planet/planet" + i + ".png"));
            }
            Random random = new Random();
            r1 = random.nextFloat(-2, -1);
            r2 = random.nextFloat(-2, 2);
        }

        @Override
        public void onUpdate(double tpf) {
            limitVelocity();
            checkOffscreen();

            if(entity.getX() < 10) {
                entity.removeFromWorld();
                spawn("planet");
            }
        }

        private void limitVelocity() {
            // we don't want the ball to move too slow in X direction
            if (abs(physics.getVelocityX()) < BALL_MIN_SPEED) {
                var signX = signum(physics.getVelocityX());

                // if 0, then choose direction to the right
                if (signX == 0.0)
                    signX = -1.0;

                physics.setVelocityX(signX * BALL_MIN_SPEED);
            }

            // we don't want the ball to move too slow in Y direction
            if (abs(physics.getVelocityY()) < BALL_MIN_SPEED) {
                var signY = signum(physics.getVelocityY());

                // if 0, then choose upwards direction
                if (signY == 0.0)
                    signY = -1.0;

                physics.setVelocityY(signY * BALL_MIN_SPEED);
            }
            velocity.set(r1,r2);

            if (entity.getY() <= 10){
                r2 = r2*-1;
                velocity.set(r1, r2);
            }
            if (entity.getBottomY() >= getAppHeight()-5){
                r2 = r2*-1;
                //entity.setY(getAppHeight()-100);
                velocity.set(r1, r2);
            }
            physics.setBodyLinearVelocity(velocity);
        }

//        public void hit() {
//            entity.removeFromWorld();
//
//
//            fire(new GameEvent(GameEvent.ASTEROID_GOT_HIT));
//        }

        private void checkOffscreen() {
            if (getEntity().getBoundingBoxComponent().isOutside(getGameScene().getViewport().getVisibleArea())) {
                physics.overwritePosition(new Point2D(
                    getAppWidth() / 2,
                    getAppHeight() / 2
                ));
            }
        }
}
