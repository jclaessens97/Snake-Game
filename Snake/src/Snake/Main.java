package Snake;

import Snake.model.Game;
import Snake.view.GamePresenter;
import Snake.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Jeroen Claessens
 * @version 1.0
 *
 * Main class of Snake
 */
public final class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
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
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
