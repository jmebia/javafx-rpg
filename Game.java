package main.framework;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import main.framework.state.MainMenuState;
import main.framework.state.IState;

import java.util.Stack;

public class Game extends Application {

    private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    public static Stack<IState> stateStack;

    @Override
    public void init() throws Exception {

        // initialize javafx nodes
        root = new Group();
        scene = new Scene(root);
        canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        // push initial states
        stateStack = new Stack<>();
        stateStack.push(new MainMenuState(scene, gc));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(scene);
        primaryStage.setTitle("RPG Game"); // title displayed on game window
        primaryStage.setResizable(false);
        primaryStage.show();

        // main game loop
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // finds the last pushed state in the stack then puts it in the game loop
                stateStack.lastElement().update(now);
                stateStack.lastElement().draw();
            }
        }.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
