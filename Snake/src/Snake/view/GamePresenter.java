package Snake.view;

import Snake.model.Game;
import Snake.model.Snake;
import Snake.model.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Jeroen Claessens
 * @version 1.0
 *
 * Connection class between the model and the view
 */
public final class GamePresenter {
    private Game model;
    private GameView view;

    public GamePresenter(Game model, GameView view) {
        this.model = model;
        this.view = view;
        updateSnake(model.getSnake().getX(), model.getSnake().getY());
        updateFood();
        autoUpdateView();
        pause(); //start in paused state
    }

    /**
     * Updates the snake movement
     * @param x used to set the new x-coordinate of the snakehead
     * @param y used to set the new y-coordinate of the snakehead
     */
    private int length = 1;
    private void updateSnake(short x, short y) {
        Rectangle snake = new Rectangle(model.SIZE, model.SIZE);
        snake.setX(x);
        snake.setY(y);
        view.setSnake(snake, false);
        if (model.getSnake().getTail().size() > length) {
            snake = new Rectangle(model.SIZE, model.SIZE);
            snake.setX(x);
            snake.setY(y);
            view.setSnake(snake, true);
            length++;
        }
    }

    /**
     * If a new food is generated, replace that food
     */
    private void updateFood() {
        Rectangle food = new Rectangle(model.SIZE, model.SIZE);
        food.setX(model.getFood().getX());
        food.setY(model.getFood().getY());
        view.setFood(food);
    }


    /**
     * Sets timeline for autmatic snake movement
     */
    private Timeline tl;
    private void autoUpdateView() {
        tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(model.DELAY), e -> updateView()));
        tl.play();
    }

    /**
     * Puts the information of the model to the view class
     */
    private void updateView() {
        model.getSnake().update();

        view.getScore().setText("Score: " + model.getSnake().getScore());

        for (Vector v : model.getSnake().getTail()) {
            updateSnake(v.getX(), v.getY());
        }

        if (model.getSnake().eatFood()) {
            model.getFood().newFood();
            updateFood();
            //System.out.println("Got food!");
            model.getSnake().addTail(model.getSnake().getX(), model.getSnake().getY());
        }

        if (model.getSnake().isGameOver()) {
            gameOver();
        }
    }

    /**
     * pause/unpause the game
     * the game starts in a paused condition
     */
    private void pause() {
        if (view.getStatus().getText().equals("PAUSED")) {
            view.getStatus().setText("");
            view.getInstruction().setText("Press <esc> to pause!");
            tl.play();
        } else {
            view.getStatus().setText("PAUSED");
            view.getInstruction().setText("Press <esc> to continue!");
            tl.stop();
        }
    }

    /**
     * stops the animation and sets gameover text
     */
    private void gameOver() {
        tl.stop();
        view.getStatus().setText("GAME OVER!");
        view.getInstruction().setText("Press <enter> to try again!");
    }

    /**
     * starts a new game(window) called after gameover
     */
    private void restart() {
        Stage primaryStage = new Stage();
        Game model = new Game();
        GameView view = new GameView();
        GamePresenter presenter = new GamePresenter(model, view);
        Scene scene = new Scene(view);
        scene.setOnKeyPressed(presenter::keyPressed);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(700);
        primaryStage.setTitle("Snake");
        primaryStage.show();

        Stage currentstage = (Stage)this.view.getScene().getWindow();
        currentstage.close();
    }

    /**
     * KeyEventHandler
     * @param e contains the key that is pressed
     */
    public void keyPressed(KeyEvent e) {
    switch (e.getCode()) {
            case UP: model.getSnake().setDir(0,-1);break;
            case DOWN: model.getSnake().setDir(0,1);break;
            case LEFT: model.getSnake().setDir(-1,0);break;
            case RIGHT: model.getSnake().setDir(1,0);break;
            case ESCAPE: pause();break;
            case ENTER: restart();break;
        }
    }
}
