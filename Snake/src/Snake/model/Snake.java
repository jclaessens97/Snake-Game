package Snake.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jeroen Claessens
 * @version 1.0
 *
 * Contains all mechanics of the snake
 */
public final class Snake {
    private byte size;
    private short x;
    private short y;
    private short xSpeed;
    private short ySpeed;
    private byte xDir;
    private byte yDir;
    private Food targetFood;

    private short length;
    private List<Vector> tail;

    private short score;
    private boolean gameOver;

    Snake(byte size, Food food) {
        this.size = size;
        this.x = 45;
        this.y = 45;
        this.xSpeed = 1;
        this.ySpeed = 0;
        this.xDir = 1;
        this.yDir = 0;
        this.targetFood = food;
        this.length = 1;
        this.tail = new ArrayList<>();
        this.tail.add(new Vector(x, y));
        this.gameOver = false;
        this.score = 0;
    }

    /**
     *  Update movement of the snake
     */
    public void update() {
        for (int i = 0; i < length - 1; i++) {
            tail.set(i, tail.get(i + 1));
        }

        this.x += (this.xSpeed * this.size) * xDir;
        this.y += (this.ySpeed * this.size) * yDir;

        tail.set(length - 1, new Vector(x, y));

        if (length == 1) {
            tail.set(0, new Vector(this.x, this.y));
        }

        detectCollision();
    }

    /**
     *  Detects collision with the edges of the canvas
     *  If length > 1 check if the snake collisions with itself
     */
    private void detectCollision() {
        //edges
        if (x > 585 || x < 0 || y > 585 || y < 0) {
            gameOver = true;
            return;
        }

        if (tail.size() > 1) {
            byte index = 0;
            final Vector head = tail.get(length - 1);
            for (Vector part : tail) {
                if (index == length - 1) continue;
                if (head.getX() == part.getX() && head.getY() == part.getY()) {
                    gameOver = true;
                    return;
                }
                index++;
            }
        }
    }

    /**
     * Add length
     * @param x x-coordinate where the last tail needs to be added
     * @param y y-coordinate where the last tail needs to be added
     */
    public void addTail(short x, short y) {
        length++;
        tail.add(0, new Vector(x, y));
        //System.out.println(tail.size());
    }

    /**
     * Checks if food is found at the head of the snake.
     * If so, add score +1 and return true
     * @return food found or not
     */
    public boolean eatFood() {
        if (this.x == targetFood.getX() && this.y == targetFood.getY()) {
            score++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets direction for the snake to be in
     * If direction is instantly changed on the same axis while lenght > 1, ignore the direction change
     * @param xDir 0: stationary, 1: move to the right, -1: move to the left
     * @param yDir 0: stationary, 1: move down, -1: move up:
     */
    public void setDir(int xDir, int yDir) {
        if (length > 1) {
            if (xDir > 0 && this.xDir < 0) return;
            if (xDir < 0 && this.xDir > 0) return;
            if (yDir > 0 && this.yDir < 0) return;
            if (yDir < 0 && this.yDir > 0) return;
        }

        this.xDir = (byte)xDir;
        this.yDir = (byte)yDir;

        if (xDir != 0) {
            this.xSpeed = 1;
            this.ySpeed = 0;
        }
        if (yDir != 0) {
            this.ySpeed = 1;
            this.xSpeed = 0;
        }
    }

    //region getters
    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public List<Vector> getTail() {
        return tail;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public short getScore() {
        return score;
    }
    //endregion
}
