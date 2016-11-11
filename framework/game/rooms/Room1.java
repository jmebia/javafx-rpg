package main.framework.game.rooms;

import javafx.geometry.Rectangle2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.framework.animation.SpriteAnimationThread;
import main.framework.animation.SpriteAnimator;
import main.framework.object2D.Character2D;
import main.framework.controller.Controller;
import main.framework.controller.Mover;
import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;
import main.framework.state.IState;

import static main.framework.game.Game.root;

/*
 * Sample game level
 */


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
    private ImageView imageView;

    // sprite
    Image tileset;
    Image image; // player sprite image
    SpriteAnimator animator;

    // animation thread
    private SpriteAnimationThread animationThread;
    /**---------------------------------**/

    public Room1(Scene scene, GraphicsContext graphicsContext) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void onEnter() {
        // scene bg
        scene.setFill(Color.BLACK);

        // change canvas size
        graphicsContext.getCanvas().setHeight(512);
        graphicsContext.getCanvas().setWidth(768);

        // Initialize game objects
        // 2d characters and controller
        player = new Character2D("player", /*w*/32, /*h*/32, /*x*/32, /*y*/32, 2);
        playerController = new Controller(scene);
        playerMover = new Mover(playerController, player);

        // 2d game objects (objects with no collisions)
        wall = new GameObject2D("wall", 32, 32, 256, 224);
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

        // set up images
        image = new Image(getClass().getResourceAsStream("../resources/EntitySet.png"));
        tileset = new Image(getClass().getResourceAsStream("../resources/tileset.png"));

        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(0, 32, 32, 32));
        root.getChildren().add(imageView);

        // instantiates sprite animator
        animator = new SpriteAnimator( imageView,
                Duration.millis(300), 3, 3,/*offsetX*/ 0, /*offsetY*/ 0, 32, 32
        );

        animationThread = new SpriteAnimationThread(animator);

        animationThread.run();
    }

    @Override
    public void update(long currentTime) {
        animator.update(this.playerMover, 0, 32, 64, 96);
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

        // draw grass floor
        for (int y = 32; y <= 512; y += 32) {
            for (int x = 0; x <= 768; x += 32) {
                graphicsContext.drawImage(tileset, 416, 0, 32, 32, x, y, 32, 32);
            }
        }

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
