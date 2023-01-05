import ch.fhnw.galacticenergies.components.RocketComponent;
import com.almasb.fxgl.entity.Entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RocketMoveTest {

    @Test
    public void test_that_rocket_moves_up() throws InterruptedException {
        RocketComponent r = new RocketComponent(null);
        Entity e = new Entity();

        e.addComponent(r);

        r.up();
        Thread.sleep(1000);

        r.stop();

        assertEquals(1, r.getEntity().getY());
    }
}
