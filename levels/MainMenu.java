package main.framework.levels;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import main.framework.gameobjects.GameObject2D;
import main.framework.Game;
import main.framework.state.IState;


public class MainMenu implements IState {

    /**------------------------------------**/

    private Scene scene;
    private GraphicsContext graphicsContext;

    /**------------------------------------**/

    GameObject2D button;

    public MainMenu(Scene scene, GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.scene = scene;
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void update(long currentTime) {
        scene.setOnKeyPressed(e ->{
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("Pressed Enter!");
                onExit();
            }
        });
    }

    @Override
    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("PRESS ENTER TO START", 230, 240);
    }

    @Override
    public void onExit() {
        // pushes room 1 to the stack
        Game.stateStack.push(new Room1(scene, graphicsContext));
    }
}
