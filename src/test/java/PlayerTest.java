import ch.fhnw.galacticenergies.controllers.PlayerController;
import ch.fhnw.galacticenergies.models.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {


    Player player = PlayerController.getPlayer();

//    @Test
//    public void testMovement() {
//        System.out.println(player.getY_Pos());
//        double yPos= player.getY_Pos();
//        int counter = 0;
//
//        for (int i = 1; i < 50; i=i+5) {
//            PlayerController.moveUp();
//            counter = i;
//        }
//        assertEquals( yPos - counter ,player.getY_Pos());
//        yPos= player.getY_Pos();
//        for (int i = 1; i < 50; i=i+5) {
//            PlayerController.moveDown(500);
//            counter = i;
//        }
//        assertEquals(yPos + counter,player.getY_Pos());
//    }
    @Test
    public void testLives(){
        int lives = player.getLives();
        int counter =0;

        for(int i = 1; i>=0; i--) {
            player.loseLives();
            counter++;
        }
        assertEquals(lives-counter,player.getLives());
        }
    }
