import ch.fhnw.galacticenergies.components.RocketComponent;
import ch.fhnw.galacticenergies.controllers.RocketController;
import com.almasb.fxgl.app.services.FXGLAssetLoaderService;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.Texture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Optional;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ROCKET;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static com.almasb.fxgl.dsl.FXGL.*;

public class rocketTest {



    @InjectMocks
    private RocketComponent rocketComponent = new RocketComponent(null);

    @BeforeEach
    void setUpDSLMock() {
        MockedStatic<FXGL> fxgl = Mockito.mockStatic(FXGL.class);
        fxgl.when(FXGL::getAppHeight).thenReturn(100);
    }

    @Test
    public void testRocketMovement(){
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
