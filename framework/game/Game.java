package main.framework.game;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.framework.animation.SpriteAnimator;
import main.framework.state.MainMenuState;
import main.framework.state.StateStack;
import main.framework.game.rooms.Room1;

public class Game extends Application {

    private Group root;
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

        // add initial states
        StateStack.addState("mainmenu", new MainMenuState(scene, gc));
        StateStack.addState("room", new Room1(scene, gc));

        StateStack.push("mainmenu");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        final ImageView imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(0, 32, 32, 32));
        root.getChildren().add(imageView);
        sa = new SpriteAnimator(
                imageView,
                Duration.millis(500), 3, 3, 0, 32, 32, 32
        );

        sa.setCycleCount(Animation.INDEFINITE);
        sa.play();
        */

        primaryStage.setScene(scene);
        primaryStage.setTitle("RPG Game"); // title displayed on game window
        primaryStage.setResizable(false);
        primaryStage.show();

        // main game loop
        new AnimationTimer() {
            @Override
            public void handle(long now) {


                // finds the last pushed state in the stack then puts it in the game loop
                // StateStack.getCurrentState().update(now);
                // StateStack.getCurrentState().draw();
            }
        }.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
