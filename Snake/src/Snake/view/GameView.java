package Snake.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeroen Claessens
 * @version 1.0
 *
 * View class contains all graphics and draw methods
 */
public final class GameView extends BorderPane {
    //style constants
    private final String backgroundStyle = "-fx-background-color: #1e1a9b;";
    private final String canvasStyle = "-fx-background-color: #000;";
    private final String snakeStyle = "-fx-fill: #00cc22;";
    private final String foodStyle = "-fx-fill: #ba0060;";

    private HBox topPane;
    private Pane canvas;

    private List<Rectangle> snake;
    private Rectangle food;

    private Label status;
    private Label instruction;
    private Label score;

    public GameView() {
        initNodes();
        layoutNodes();
    }

    /**
     * Initialise nodes with their default states
     */
    private void initNodes() {
        this.topPane = new HBox();
        this.canvas = new Pane();
        snake = new ArrayList<>();
        status = new Label();
        instruction = new Label();
        score = new Label("Score: 0");
    }

    /**
     * Layout & Style nodes
     */
    private void layoutNodes() {
        this.setStyle(backgroundStyle);
        this.setMinSize(700,700);

        this.status.setFont(Font.font("Courier new", FontWeight.EXTRA_BOLD, 36));
        this.status.setTextFill(Color.WHITE);
        this.status.setTranslateX(50);
        this.status.setTranslateY(25);

        this.instruction.setFont(Font.font("Courier new", 14));
        this.instruction.setTextFill(Color.WHITE);
        this.instruction.setTranslateX(60);
        this.instruction.setTranslateY(40);

        this.score.setFont(Font.font("Courier new", FontWeight.BOLD, 18));
        this.score.setTextFill(Color.WHITE);
        this.score.setTranslateX(80);
        this.score.setTranslateY(35);

        this.topPane.setMinSize(700,50);
        this.topPane.setMaxSize(700,50);
        this.topPane.getChildren().addAll(status, instruction, score);


        this.canvas.setStyle(canvasStyle);
        this.canvas.setBorder(new Border(
                new BorderStroke(Color.RED,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(2),
                        new Insets(-2))));
        this.canvas.setMinSize(600,600);
        this.canvas.setMaxSize(600,600);

        setAlignment(canvas, Pos.CENTER);

        //add nodes
        this.setTop(topPane);
        this.setCenter(canvas);
    }

    /**
     * draws the snake
     * method called from presenter
     * @param snake which part of the snake needs to be drawn
     * @param add true if food is eaten and the snake extends his length
     */
    void setSnake(Rectangle snake, boolean add) {
        //remove if no food is eaten
        if (!add && this.snake.size() > 0) {
            this.snake.remove(0);
            this.canvas.getChildren().remove(1);
        }

        snake.setStyle(snakeStyle);
        this.snake.add(snake);

        for (Rectangle r : this.snake) {
            this.canvas.getChildren().remove(r);
            this.canvas.getChildren().add(r);
        }

    }

    /**
     * draws the food
     * method called from presenter
     * @param food contains coordinates where the food needs to be drawn
     */
    void setFood(Rectangle food) {
        canvas.getChildren().remove(this.food);
        this.food = food;
        food.setStyle(foodStyle);
        canvas.getChildren().add(0, food);
    }

    //region setters
    Label getStatus() {
        return status;
    }

    Label getInstruction() {
        return instruction;
    }

    Label getScore() {
        return score;
    }
    //endregion
}
