package ch.fhnw.galacticenergies.models;

public class Player {
    int pos_y;
    int lives;

    public void moveUp()
    {

        pos_y += 1;
    }

    public void moveDown()
    {
        pos_y -= 1;
        System.out.println(pos_y);
    }

    public void resetPosition()
    {
        pos_y = 0;
    }

    public int getPos_y() {
        return pos_y;
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
