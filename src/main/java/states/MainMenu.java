package main.java.states;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import main.java.GameObject;
import main.java.Main;


public class MainMenu extends IState {

    GameObject button;

    public MainMenu() {
        super();
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void update(Scene scene, long currentTime) {
        scene.setOnKeyPressed(e ->{
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("Pressed Enter!");
                Dungeon dungeon = new Dungeon();
                Main.stateStack.push(dungeon);
                dungeon.onEnter(scene);
                onExit(scene);
            }
        });
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillText("PRESS ENTER TO START", 230, 240);
    }

    @Override
    public void onExit(Scene scene) {

    }
}
