package Snake.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeroen on 7/11/2016.
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
    private short score;

    private short length;
    private List<Vector> tail;
    private boolean gameOver;

    public Snake(byte size, Food food) {
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

        if (x > 585 || x < 0 || y > 585 || y < 0) {
            gameOver = true;
        }

        if (tail.size() > 1) {
            Vector head = tail.get(0);
            for (int i = 1; i < tail.size(); i++) {
                if (head.getX() == tail.get(i).getX() && head.getY() == tail.get(i).getY()) {
                    gameOver = true;
                    return;
                }
            }
        }


    }

    public void addTail(short x, short y) {
        score++;
        length++;
        tail.add(0, new Vector(x, y));
        System.out.println(tail.size());
    }

    public boolean eatFood() {
        return this.x == targetFood.getX()
                                && this.y == targetFood.getY();
    }

    public void setDir(int xDir, int yDir) {
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

    //getters
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
}
