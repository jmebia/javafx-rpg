package main.framework.game.rooms;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.framework.animation.SpriteAnimator;
import main.framework.game.PlayerProperties;
import main.framework.object2D.Character2D;
import main.framework.controller.Controller;
import main.framework.controller.Mover;
import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;
import main.framework.state.IState;
import main.framework.state.StateStack;

import java.util.ArrayList;

import static main.framework.game.Game.root;

/*
 * Sample game level
 */


public class Room1 implements IState {

    /**---------------------------------**/
    private final Scene scene;
    private final GraphicsContext graphicsContext;
    private PerspectiveCamera perspectiveCamera;
    /**---------------------------------**/

    /**--------------------------- game objects ----------------------------**/
    private Character2D player;
    private ArrayList<GameObject2D> sideWalls;
    private GameObject2D talker2D;

    // hotspots
    private Hotspot enemy;
    private Hotspot talker;

    // player properties
    private Controller playerController;
    private Mover playerMover;
    private ImageView imageView;

    // sprite
    Image tileset;
    Image playerSprite; // player sprite playerSprite
    Image otherSprites;
    SpriteAnimator animator;
    /**---------------------------------------------------------------------**/

    public Room1(Scene scene, GraphicsContext graphicsContext) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void init() {

        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, 2048, 2048);

        // set up camera
        perspectiveCamera = new PerspectiveCamera(true);
        perspectiveCamera.setTranslateZ(-1000);
        perspectiveCamera.setNearClip(0.1);
        perspectiveCamera.setFarClip(2000.0);
        perspectiveCamera.setFieldOfView(25);
        scene.setCamera(perspectiveCamera);

        // change canvas size
        graphicsContext.getCanvas().setHeight(2048);
        graphicsContext.getCanvas().setWidth(2048);

        // Initialize game objects
        // 2d characters and controller
        player = new Character2D("player", /*w*/20, /*h*/20, /*x*/768, /*y*/900, 2); // always make x and y even
        playerController = new Controller(scene);
        playerMover = new Mover(playerController, player);

        // 2d game objects (objects with no collisions)
        sideWalls = new ArrayList<>();

        // invisible walls instantiation
        for(int x = 512; x <= 1536; x+= 32) {
            sideWalls.add(new GameObject2D("wall", 32, 32, x, 512));
        }
        for(int x = 512; x <= 1536; x+= 32) {
            sideWalls.add(new GameObject2D("wall", 32, 32, x, 1536));
        }
        for(int y = 512; y <= 1536; y+= 32) {
            sideWalls.add(new GameObject2D("wall", 32, 32, 512, y));
        }
        for(int y = 512; y <= 1536; y+= 32) {
            sideWalls.add(new GameObject2D("wall", 32, 32, 1536, y));
        }

        talker2D = new GameObject2D("talker", 32, 32, 768, 768);
        player.addCollision(sideWalls);
        player.addCollision(talker2D); // add a collision to talker

        // hotspots and triggers
        enemy = new Hotspot("door", 32, 32 , 768, 832); // combat initiator
        talker = new Hotspot("talker", 64, 64, 752, 752); // dialogue initiator
        enemy.addTriggerCharacter(player);
        talker.addTriggerCharacter(player);

        // set up images
        playerSprite = new Image(getClass().getResourceAsStream("../resources/sprites.png"));
        otherSprites = new Image(getClass().getResourceAsStream("../resources/EntitySet.png"));
        tileset = new Image(getClass().getResourceAsStream("../resources/tileset.png"));

        imageView = new ImageView(playerSprite);
        imageView.setViewport(new Rectangle2D(0, 32, 32, 32));
        root.getChildren().add(imageView);

        // instantiates sprite animator
        animator = new SpriteAnimator( imageView,
                Duration.millis(300), 3, 3, /* offsetX */ 0, /* offsetY */ 0, 32, 32
        );
        animator.setCycleCount(Animation.INDEFINITE);
    }

    @Override
    public void onEnter() {
        // on enter draw this
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, 2048, 2048);

        player = PlayerProperties.Player1.getCharacter2D();
        playerController = new Controller(scene);
        playerMover = new Mover(playerController, player);

        animator.play();
    }

    @Override
    public void update(long currentTime) {
        animator.update(this.playerMover, 0, 32, 64, 96);
        animator.updateView(playerMover);
        playerMover.update();
        perspectiveCamera.setTranslateY(player.getY());
        perspectiveCamera.setTranslateX(player.getX());

        if (enemy != null) {
            if (enemy.isCharacterOnHotspot()) {
                System.out.println("Player is standing on door!");
                StateStack.push("combat");
                enemy = null;
                onExit();
            }
        }

        if(playerController.getInputs().contains("ESCAPE")) {
            onExit();
            StateStack.push("gameMenu");
        }
    }

    @Override
    public void draw() {

        // draw grass floor
        for (int y = 512; y <= 1536; y += 32) {
            for (int x = 512; x <= 1536; x += 32) {
                graphicsContext.drawImage(tileset, 0, 0, 32, 32, x, y, 32, 32);
            }
        }

        // draw walls
        for (GameObject2D wall : sideWalls) {
            graphicsContext.drawImage(tileset, 32, 0, 32, 32, wall.getX(), wall.getY(), wall.getHeight(), wall.getWidth());
        }

        // combat initiator hotspot
        if(enemy != null)
            graphicsContext.drawImage(otherSprites, 128, 0, 32, 32, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

        graphicsContext.drawImage(otherSprites, 32, 0, 32, 32, talker2D.getX(), talker2D.getY(), talker2D.getWidth(), talker2D.getHeight());

        graphicsContext.setFill((talker.isCharacterOnHotspot()? new Color(1, 0, 0, 0.3) : new Color(1, 1, 0, 0.3)));
        graphicsContext.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void onExit() {
        scene.setOnKeyPressed(null);
        scene.setOnKeyReleased(null);
        animator.stop();
        // set global player properties
        PlayerProperties.Player1.setCharacter2D(player);
    }

    @Override
    public void onClose() {
        imageView.setImage(null);
        animator = null;
    }


}
