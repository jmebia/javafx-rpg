package main.framework.game.rooms;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.framework.object2D.Character2D;
import main.framework.controller.Controller;
import main.framework.controller.Mover;
import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;
import main.framework.state.IState;


public class Room1 implements IState {

    /**---------------------------------**/
    private final Scene scene;
    private final GraphicsContext graphicsContext;
    /**---------------------------------**/

    /**----------game objects------------**/
    private Character2D player;
    private GameObject2D wall;
    private Hotspot hsDoor;
    private Controller playerController;
    private Mover playerMover;
    private PerspectiveCamera camera = new PerspectiveCamera(true);
    /**---------------------------------**/

    public Room1(Scene scene, GraphicsContext graphicsContext) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void onEnter() {
        // Initialize game objects
        // 2d characters and controller
        player = new Character2D("player", 32, 32, 32, 32, 2);
        playerController = new Controller(scene);
        playerMover = new Mover(playerController, player);

        // 2d game objects (objects with no collisions)
        wall = new GameObject2D("wall", 32, 32, 150, 150);
        player.addCollision(wall);

        // hotspots and triggers
        hsDoor = new Hotspot("door", 32, 32 , 256, 256);

        /* cell wall instantiation
        for(int i) {
            cellWallsUpper.add(new GameObject2D("wall", 32, 32, 0, 0));
        } */

        // set up camera
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        scene.setCamera(camera);
    }

    @Override
    public void update(long currentTime) {
        playerMover.update();
        camera.setTranslateY(player.getY());
        camera.setTranslateX(player.getX());
    }

    @Override
    public void draw() {
        /*
         * placeholder graphics for 2d objects including the hotspots
         *
         */
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, 512, 512); // background

        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());

        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(hsDoor.getX(), hsDoor.getY(), hsDoor.getWidth(), hsDoor.getHeight());

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void onExit() {
        player = null;
        playerController = null;
        playerMover = null;
    }

}
