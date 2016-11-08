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
 * Created by Jeroen on 7/11/2016.
 */
public class GamePresenter {
    private Game model;
    private GameView view;

    public GamePresenter(Game model, GameView view) {
        this.model = model;
        this.view = view;
        updateSnake(model.getSnake().getX(), model.getSnake().getY());
        updateFood();
        autoUpdateView();
    }

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

    private void updateFood() {
        Rectangle food = new Rectangle(model.SIZE, model.SIZE);
        food.setX(model.getFood().getX());
        food.setY(model.getFood().getY());
        view.setFood(food);
    }

    private Timeline tl;

    private void autoUpdateView() {
        tl = new Timeline(model.REFRESHRATE);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(model.DELAY), e -> updateView()));
        tl.play();
    }

    private void updateView() {
        model.getSnake().update();

        view.getScore().setText("Score: " + model.getSnake().getScore());

        for (Vector v : model.getSnake().getTail()) {
            updateSnake(v.getX(), v.getY());
        }

        if (model.getSnake().eatFood()) {
            model.getFood().newFood();
            updateFood();
            System.out.println("Got food!");
            model.getSnake().addTail(model.getSnake().getX(), model.getSnake().getY());
        }

        if (model.getSnake().isGameOver()) {
            gameOver();
        }
    }

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

    private void gameOver() {
        tl.stop();
        view.getStatus().setText("GAME OVER!");
        view.getInstruction().setText("Press <enter> to try again!");
    }

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

    public void keyPressed(KeyEvent e) {
        KeyCode keyCode = e.getCode();
        switch (keyCode) {
            case UP: model.getSnake().setDir(0,-1);break;
            case DOWN: model.getSnake().setDir(0,1);break;
            case LEFT: model.getSnake().setDir(-1,0);break;
            case RIGHT: model.getSnake().setDir(1,0);break;
            case ESCAPE: pause();break;
            case ENTER: restart();break;
        }
    }
}
