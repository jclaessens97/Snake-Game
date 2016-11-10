package Snake.model;

/**
 * @author Jeroen Claessens
 * @version 1.0
 *
 * Model class to gain access to snake & food from the presenter with only 1 model
 */
public final class Game {
    public final float DELAY = 0.15f; //time between snake-move animations
    public final byte SIZE = 15; //blocksize

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
