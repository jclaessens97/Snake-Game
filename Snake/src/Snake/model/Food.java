package Snake.model;

import java.util.Random;

/**
 * Created by Jeroen on 7/11/2016.
 */
public final class Food {
    private byte size;
    private short x;
    private short y;

    public Food(byte size) {
        this.size = size;
        newFood();
    }

    public void newFood() {
        byte cols = (byte)(600 / size);
        byte rows = (byte)(600 / size);

        Random rnd = new Random();
        this.x = (short)(rnd.nextInt(cols) * size);
        this.y = (short)(rnd.nextInt(rows) * size);

        System.out.println(x + "," + y);
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }
}
