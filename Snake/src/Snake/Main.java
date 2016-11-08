package Snake;

import Snake.model.Game;
import Snake.view.GamePresenter;
import Snake.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Jeroen on 7/11/2016.
 */
public class Main extends Application {
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
