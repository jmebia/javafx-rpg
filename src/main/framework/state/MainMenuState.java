package main.framework.state;

import javafx.application.Platform;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.framework.object2D.GameObject2D;


public class MainMenuState implements IState {

    /**------------------------------------**/

    private final Scene scene;
    private final GraphicsContext graphicsContext;
    private PerspectiveCamera camera;
    /**------------------------------------**/

    private int currentMarker = 1;
    private int minMarker = 1;
    private int maxMarker = 3;

    private GameObject2D button;

    public MainMenuState(Scene scene, GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.scene = scene;
    }

    @Override
    public void init() {
        //camera
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(28);
        scene.setCamera(camera);

        scene.getCamera().setTranslateX(256);
        scene.getCamera().setTranslateY(256);

        scene.setOnKeyPressed(event -> {

            // choose menu option
            if (event.getCode() == KeyCode.DOWN) {
                if (currentMarker < maxMarker) currentMarker++;
                else currentMarker = minMarker;
            } else if (event.getCode() == KeyCode.UP) {
                if (currentMarker > minMarker) currentMarker--;
                else currentMarker = maxMarker;
            }

            // trigger
            if (event.getCode() == KeyCode.ENTER) {
                switch (currentMarker) {
                    case 1:
                        System.out.println("New Game Selected!");
                        StateStack.push("room");
                        break;
                    case 2:
                        System.out.println("Load Game Selected!");
                        break;
                    case 3:
                        System.out.println("Exit Selected!");
                        Platform.exit();
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        });
    }

    @Override
    public void onEnter() {

        currentMarker = 1;

        //camera
        scene.setCamera(camera);

        scene.getCamera().setTranslateX(256);
        scene.getCamera().setTranslateY(256);

        scene.setOnKeyPressed(event -> {

            // choose menu option
            if (event.getCode() == KeyCode.DOWN) {
                if (currentMarker < maxMarker) currentMarker++;
                else currentMarker = minMarker;
            } else if (event.getCode() == KeyCode.UP) {
                if (currentMarker > minMarker) currentMarker--;
                else currentMarker = maxMarker;
            }

            // trigger
            if (event.getCode() == KeyCode.ENTER) {
                switch (currentMarker) {
                    case 1:
                        System.out.println("New Game Selected!");
                        StateStack.push("room");
                        break;
                    case 2:
                        System.out.println("Load Game Selected!");
                        break;
                    case 3:
                        System.out.println("Exit Selected!");
                        Platform.exit();
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        });

    }

    @Override
    public void update(long currentTime) {
        // main menu
    }

    @Override
    public void draw() {

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0,0,512,512);

        graphicsContext.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        graphicsContext.setFill((currentMarker == 1? Color.BLUE : Color.BLACK));
        graphicsContext.fillText("New Game", 64, 384);

        graphicsContext.setFill((currentMarker == 2? Color.BLUE : Color.BLACK));
        graphicsContext.fillText("Load Game", 64, 416);

        graphicsContext.setFill((currentMarker == 3? Color.BLUE : Color.BLACK));
        graphicsContext.fillText("Exit", 64, 448);
    }

    @Override
    public void onExit() {
        scene.setOnKeyPressed(null);
        scene.setOnKeyReleased(null);
        // pushes room 1 to the stack
        StateStack.push("room");
        System.out.println("Exited Main Menu State. Now going to room");
    }

    @Override
    public void onClose() {

    }
}
