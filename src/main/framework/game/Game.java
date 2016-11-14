package main.framework.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.framework.state.CombatState;
import main.framework.state.GameMenuState;
import main.framework.state.MainMenuState;
import main.framework.state.StateStack;
import main.framework.game.rooms.Room1;

import java.awt.*;

public class Game extends Application {

    public static AnimationTimer gameLoop;
    public static Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

   // Image image = new Image(getClass().getResourceAsStream("EntitySet.png"));

    // SpriteAnimator sa;

    @Override
    public void init() throws Exception {

        // initialize javafx nodes
        root = new Group();
        scene = new Scene(root);
        canvas = new Canvas(512, 512);
        root.getChildren().addAll(canvas);
        gc = canvas.getGraphicsContext2D();
        scene.setFill(Color.BLACK);
        // add initial states
        StateStack.addState("mainmenu", new MainMenuState(scene, gc));
        StateStack.addState("room", new Room1(scene, gc));
        StateStack.addState("gameMenu", new GameMenuState(scene, gc));
        StateStack.addState("combat", new CombatState(scene, gc));

        StateStack.push("mainmenu");
        canvas = new Canvas(2048, 2048);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(scene);
        primaryStage.setTitle("RPG Game"); // title displayed on game window
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

        // main game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // finds the last pushed state in the stack then puts it in the game loop
                StateStack.getCurrentState().update(now);
                StateStack.getCurrentState().draw();
            }
        };

        gameLoop.start();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
