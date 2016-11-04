package main.java.states;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.*;
import main.java.Character;

import java.util.ArrayList;


public class Dungeon extends IState {

    Character player;
    ArrayList<GameObject> cellWallsRight;
    ArrayList<GameObject> cellWallsLeft;
    ArrayList<GameObject> cellWallsUpper;
    ArrayList<GameObject> cellWallsLower;
    Hotspot hsDoor;
    Controller playerController;
    Mover playerMover;

    public Dungeon() {
        super();
    }

    @Override
    public void onEnter() {
        // Initialize game objects


    }

    @Override
    public void update(Scene scene, long currentTime) {

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillText("Dungeon Mode", 200, 200);
    }

}
