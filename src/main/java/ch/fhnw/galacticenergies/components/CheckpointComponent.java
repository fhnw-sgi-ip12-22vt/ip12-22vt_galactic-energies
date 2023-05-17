package ch.fhnw.galacticenergies.components;

import ch.fhnw.galacticenergies.View;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.texture;

public class CheckpointComponent extends Component {
    ArrayList<Texture> planetImages = new ArrayList<>();
    private Point2D velocity = new Point2D(0, 0);
    private float r1;
    private float r2;

    public CheckpointComponent() {

        for (int i = 1; i < 10; i++) {
            planetImages.add(texture("planet/planet" + i + ".png"));
        }
        Random random = new Random();
        r1 = random.nextFloat(-2, -1);
        r2 = random.nextFloat(-2, 2);
    }

    @Override
    public void onUpdate (double tpf) {
        entity.translate(velocity.multiply(tpf));
        checkBorders();

        if (entity.getX() < 0 - entity.getWidth()) {
            entity.removeFromWorld();
            View.checkpointController.addCheckpoint();
        }
    }

    public void setVelocity (Point2D velocity) {
        this.velocity = velocity;
    }

    private void checkBorders () {
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
}
