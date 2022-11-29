package ch.fhnw.galacticenergies.models;

import ch.fhnw.galacticenergies.services.WindowService;

public class Player {
    int pos_y = 0;
    int lives;

    private final int step = 5;

    public void moveUp()
    {
        System.out.println("pos: " + pos_y);
        if(pos_y - step < 0) return;
        pos_y -= step;
    }

    public void moveDown()
    {
        System.out.println(WindowService.getWindowHeight());
        if(WindowService.getWindowHeight() < pos_y + step) return;
        pos_y += step;
        System.out.println("Pos: " + pos_y);
    }

    public void resetPosition()
    {
        pos_y = 0;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void removeLive()
    {
        if(this.lives == 1) {

        } else {
            this.lives -= 1;
        }

    }


}
