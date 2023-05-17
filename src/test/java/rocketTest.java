import ch.fhnw.galacticenergies.components.RocketComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ROCKET;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Testing of the rocket
 *
 * @version 1.0
 */
public class rocketTest {

    /**
     * Mock injection
     */
    @InjectMocks
    private RocketComponent rocketComponent = new RocketComponent(null);


    /**
     * Setting static AppHeight to 100
     */
    @BeforeEach
    void setUpDSLMock() {
        MockedStatic<FXGL> fxgl = Mockito.mockStatic(FXGL.class);
        fxgl.when(FXGL::getAppHeight).thenReturn(100);
    }

    /**
     * Testing of the Movement functions of the Rocket
     * up: Y gets smaller
     * down: Y gets bigger
     */
    @Test
    public void testRocketMovement() {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);


        Entity rocket = new Entity();
        rocket.setType(ROCKET);

        rocket.addComponent(physics);
        rocket.addComponent(rocketComponent);


        PhysicsWorld physicsWorld = new PhysicsWorld(100, 10);
        physicsWorld.setGravity(0, 0);

        physicsWorld.onEntityAdded(rocket);

        double before = rocket.getY();
        rocket.getComponent(RocketComponent.class).down();
        rocketComponent.onUpdate(1.0);
        physicsWorld.onUpdate(1.0);
        physics.onUpdate(1.0);
        rocket.getComponent(RocketComponent.class).stop();

        assertTrue(rocket.getY() > before);


        before = rocket.getY();
        rocket.getComponent(RocketComponent.class).up();
        rocketComponent.onUpdate(1.0);
        physicsWorld.onUpdate(1.0);
        physics.onUpdate(1.0);
        rocket.getComponent(RocketComponent.class).stop();

        assertTrue(rocket.getY() < before);

    }

}
