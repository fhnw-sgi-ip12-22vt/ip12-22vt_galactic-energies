package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;

/**
 * The class AsteroidComponent implements the Asteroids
 * @version 1.0
 */
public class AsteroidComponent extends Component {

    private Point2D velocity = new Point2D(0, 0);

    /**
     * Define what happens on each update
     * @param tpf TickPerFrame
     */
    @Override
    public void onUpdate(double tpf) {
        entity.translate(velocity.multiply(tpf));
        checkBorders();
    }

    /**
     * @param velocity defines the Asteroids velocity
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    /**
     * Check if the Asteroid is within the bounds of the game, if not remove it and respawn a new Asteroid
     */
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

    /**
     *
     * @return the AppWidth
     */
    private double getAppWidth() {
        return getGameScene().getAppWidth();
    }

    /**
     *
     * @return the AppHeight
     */
    private double getAppHeight() {
        return getGameScene().getAppHeight();
    }
}
