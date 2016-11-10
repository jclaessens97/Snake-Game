package Snake.model;

import java.util.Random;

/**
 * @author Jeroen Claessens
 * @version 1.0
 *
 * Data for the food & method to renew the object with new coordinates
 */
public final class Food {
    private byte size;
    private short x;
    private short y;

    public Food(byte size) {
        this.size = size;
        newFood();
    }

    /**
     * Generates new random coordinates and store it in this object
     */
    public void newFood() {
        byte cols = (byte)(600 / size);
        byte rows = (byte)(600 / size);

        Random rnd = new Random();
        this.x = (short)(rnd.nextInt(cols) * size);
        this.y = (short)(rnd.nextInt(rows) * size);

        //System.out.println(x + "," + y);
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }
}
