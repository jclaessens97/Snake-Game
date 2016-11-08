package Snake.model;

/**
 * Created by Jeroen on 7/11/2016.
 */
public final class Game {
    public final byte REFRESHRATE = 60;
    public final double DELAY = 0.15;
    public final byte SIZE = 15;

    private Snake snake;
    private Food food;

    public Game() {
        this.food = new Food(SIZE);
        this.snake = new Snake(SIZE, this.food);
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

}
