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

        private float r1;
        private float r2;
        private Point2D velocity = new Point2D(0, 0);

        public CheckpointComponent() {

            for (int i = 1; i < 10; i++)
            {
                planetImages.add(texture("planet/planet" + i + ".png"));
            }
            Random random = new Random();
            this.r1 = random.nextFloat(-2, -1);
            this.r2 = random.nextFloat(-2, 2);
        }

    @Override
    public void onUpdate(double tpf) {
        entity.translate(velocity.multiply(tpf));
        checkBorders();
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    private void checkBorders() {
        double x = entity.getX();
        double y = entity.getY();
        double width = entity.getWidth();
        double height = entity.getHeight();

        if (x + width > getAppWidth()) {
            velocity = new Point2D(-velocity.getX(), velocity.getY());
            entity.setX(getAppWidth() - width);
        }

        if (y < 0) {
            velocity = new Point2D(velocity.getX(), -velocity.getY());
            entity.setY(0);
        }

        if (y + height > getAppHeight()) {
            velocity = new Point2D(velocity.getX(), -velocity.getY());
            entity.setY(getAppHeight() - height);
        }
    }

    private double getAppWidth() {
        return getGameScene().getAppWidth();
    }

    private double getAppHeight() {
        return getGameScene().getAppHeight();
    }
}
