package Snake.model;

/**
 * Created by Jeroen on 7/11/2016.
 */
public final class Vector {
    private short x;
    private short y;

    public Vector(short x, short y) {
        this.x = x;
        this.y = y;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
