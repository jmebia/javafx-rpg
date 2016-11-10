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

    Sample game level
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
    Image image;
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
        // Initialize game objects
        // 2d characters and controller
        player = new Character2D("player", /*w*/32, /*h*/32, /*x*/32, /*y*/32, 2);
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

        // set up image
        image = new Image(getClass().getResourceAsStream("../resources/EntitySet.png"));

        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(0, 32, 32, 32));
        root.getChildren().add(imageView);

        // instantiates sprite animator
        animator = new SpriteAnimator( imageView,
                Duration.millis(1500), 3, 3,/*offsetX*/ 0, /*offsetY*/ 0, 32, 32
        );

        animationThread = new SpriteAnimationThread(animator);

        animationThread.run();
    }

    @Override
    public void update(long currentTime) {
        animator.update(this.player);
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
