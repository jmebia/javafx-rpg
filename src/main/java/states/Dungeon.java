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

    }

    public void onEnter(Scene scene) {
        // Initialize game objects
        player = new Character("player", 32, 32, 32, 32, 2);
        playerController = new Controller(scene);
        playerMover = new Mover(playerController, player);
    }

    @Override
    public void update(Scene scene, long currentTime) {
        playerMover.update();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 512, 512); // background

        gc.setFill(Color.BLUE);
        gc.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

}
