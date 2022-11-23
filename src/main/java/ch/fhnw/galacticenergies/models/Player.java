package ch.fhnw.galacticenergies.models;

public class Player {
    int pos_y;

    public void moveUp()
    {
        pos_y += 1;
    }

    public void moveDown()
    {
        pos_y -= 1;
    }
}
