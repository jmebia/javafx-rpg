package main.framework.levels;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.framework.gameobjects.Character;
import main.framework.controller.Controller;
import main.framework.controller.Mover;
import main.framework.gameobjects.GameObject;
import main.framework.gameobjects.Hotspot;
import main.framework.state.IState;

import java.util.ArrayList;


public class Room1 extends IState {

    Character player;
    ArrayList<GameObject> cellWallsRight;
    ArrayList<GameObject> cellWallsLeft;
    ArrayList<GameObject> cellWallsUpper;
    ArrayList<GameObject> cellWallsLower;
    Hotspot hsDoor;
    Controller playerController;
    Mover playerMover;

    public Room1(Scene scene) {
        onEnter(scene);
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

        gc.fillText("Hello!", 1,1);
    }

}
