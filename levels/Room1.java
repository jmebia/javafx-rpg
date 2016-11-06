package main.framework.levels;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.framework.gameobjects.Character2D;
import main.framework.controller.Controller;
import main.framework.controller.Mover;
import main.framework.gameobjects.GameObject2D;
import main.framework.gameobjects.Hotspot;
import main.framework.state.IState;

import java.util.ArrayList;


public class Room1 implements IState {

    /**---------------------------------**/

    private Scene scene;
    private GraphicsContext graphicsContext;

    PerspectiveCamera camera = new PerspectiveCamera(true);

    /**---------------------------------**/

    /**----------game objects------------**/
    Character2D player;
    ArrayList<GameObject2D> cellWallsRight;
    ArrayList<GameObject2D> cellWallsLeft;
    ArrayList<GameObject2D> cellWallsUpper;
    ArrayList<GameObject2D> cellWallsLower;
    Hotspot hsDoor;
    Controller playerController;
    Mover playerMover;
    /**---------------------------------**/

    public Room1(Scene scene, GraphicsContext graphicsContext) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;

        // set up camera
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);

        scene.setCamera(camera);

        onEnter();
    }

    @Override
    public void onEnter() {
        // Initialize game objects
        player = new Character2D("player", 32, 32, 32, 32, 2);
        playerController = new Controller(scene);
        playerMover = new Mover(playerController, player);
    }

    @Override
    public void update(long currentTime) {
        playerMover.update();
        camera.setTranslateY(player.getY());
        camera.setTranslateX(player.getX());
    }

    @Override
    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, 512, 512); // background

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void onExit() {

    }

}
