package ch.fhnw.galacticenergies.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

/** AsteroidHandler is defined in the class CollisionHandler
 * @version 1.0
 */
public class AsteroidHandler extends CollisionHandler {
    public AsteroidHandler(Object a, Object b) {
        super(a, b);
    }

    @Override
    protected void onCollisionBegin(Entity asteroid, Entity player) {

    }
}
