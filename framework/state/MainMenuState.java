package main.framework.state;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import main.framework.object2D.GameObject2D;


public class MainMenuState implements IState {

    /**------------------------------------**/

    private final Scene scene;
    private final GraphicsContext graphicsContext;

    /**------------------------------------**/

    private GameObject2D button;

    public MainMenuState(Scene scene, GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.scene = scene;
    }

    @Override
    public void onEnter() {
        scene.setOnKeyPressed(e ->{
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("Pressed Enter!");
                onExit();
            }
        });
    }

    @Override
    public void update(long currentTime) {
        // main menu
    }

    @Override
    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("PRESS ENTER TO START", 230, 236);
    }

    @Override
    public void onExit() {
        // pushes room 1 to the stack
        StateStack.push("room");
        System.out.println("Exited Main Menu State. Now going to room");
    }
}
