import ch.fhnw.galacticenergies.controllers.PlayerController;
import ch.fhnw.galacticenergies.models.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {


    Player player = PlayerController.getPlayer();

    @Test
    public void testMovement() {
        double yPos= player.getY_Pos();
        int counter = 0;

        for (int i = 0; i < 10; i++) {
            PlayerController.moveUp();
            counter = i+1;
        }
        assertEquals( yPos + counter ,player.getY_Pos());
        yPos= player.getY_Pos();
        for (int i = 0; i < 10; i++) {
            PlayerController.moveDown();
            counter = i+1;
        }
        assertEquals(yPos - counter,player.getY_Pos());
    }
}
