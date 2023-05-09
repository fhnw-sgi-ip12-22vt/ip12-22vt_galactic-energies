package ch.fhnw.galacticenergies.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

public class AsteroidHandler extends CollisionHandler {
    public AsteroidHandler (Object a, Object b) {
        super(a, b);
    }

    @Override
    protected void onCollisionBegin (Entity asteroid, Entity player) {

    }
}
