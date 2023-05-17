import ch.fhnw.galacticenergies.components.AsteroidComponent;
import ch.fhnw.galacticenergies.components.RocketComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Random;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ASTEROID;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ROCKET;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test asteroidComponent
 */
public class asteroidTest {

    /**
     * Mock injection
     */
    @InjectMocks
    private AsteroidComponent asteroidComponent = new AsteroidComponent();
    @InjectMocks
    private RocketComponent rocketComponent = new RocketComponent(null);

    /**
     * Setting static AppHeight to 100
     */
    @BeforeEach
    void setUpDSLMock() {
        MockedStatic<FXGL> fxgl = Mockito.mockStatic(FXGL.class);
        fxgl.when(FXGL::getAppHeight).thenReturn(100);
        fxgl.when(FXGL::getAppWidth).thenReturn(200);
    }

    /**
     * Testing of the Movement functions of the asteroid
     *
     */
    @Test
    public void testAsteroidMovement(){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        Entity asteroid = new Entity();
        asteroid.setType(ASTEROID);
        Random r = new Random();

        asteroid.addComponent(physics);
        asteroid.addComponent(asteroidComponent);
        Point2D velocity = new Point2D(r.nextFloat(-100, -75),
            r.nextFloat(0, 25));

        asteroidComponent.setVelocity(velocity);

        PhysicsWorld physicsWorld = new PhysicsWorld(100, 10);
        physicsWorld.setGravity(0, 0);

        physicsWorld.onEntityAdded(asteroid);

        double before = asteroid.getX();
        System.out.println(asteroid.getX());
        asteroidComponent.onUpdate(1.0);
        //asteroid.getComponent(AsteroidComponent.class).
        physicsWorld.onUpdate(1.0);
        physics.onUpdate(1.0);
        //asteroidComponent.getEntity().getComponent(AsteroidComponent.class)
        System.out.println(asteroid.getX());

        assertTrue(asteroid.getX() > before);
    }

}
