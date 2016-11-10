package Snake.model;

/**
 * @author Jeroen Claessens
 * @version 1.0
 *
 * Contains an object to store x- and y-coordinate in one object
 * ?? (maybe make this an innerclass of snake) ??
 */
public final class Vector {
    private short x;
    private short y;

    Vector(short x, short y) {
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
