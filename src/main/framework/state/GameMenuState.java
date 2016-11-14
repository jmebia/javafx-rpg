package main.framework.state;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.framework.object2D.GameObject2D;


public class GameMenuState implements IState {

    private final Scene scene;
    private GraphicsContext graphicsContext;

    // Marker for item focused
    private GameObject2D marker2D;
    private int minMarker = 1;
    private int currentMarker = 1;
    private int maxMarker = 4;

    // in game menu box
    private GameObject2D menuBox;

    public GameMenuState (Scene scene, GraphicsContext gc) {
        this.scene = scene;
        graphicsContext = gc;
    }

    @Override
    public void init() {

        scene.setOnKeyPressed(e -> {
            // return to game
            if (e.getCode() == KeyCode.ESCAPE) {
                StateStack.pop();
                onExit();
            }

            // choose menu option
            if (e.getCode() == KeyCode.DOWN) {
                if (currentMarker < maxMarker) currentMarker++;
                else currentMarker = minMarker;
            } else if (e.getCode() == KeyCode.UP) {
                if (currentMarker > minMarker) currentMarker--;
                else currentMarker = maxMarker;
            }

            // trigger
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
                switch (currentMarker) {
                    case 1:
                        System.out.println("Save Game Selected!");
                        break;
                    case 2:
                        System.out.println("Load Game Selected!");
                        break;
                    case 3:
                        System.out.println("Exit to Main Menu Selected!");
                        StateStack.popToMainMenu();
                        break;
                    case 4:
                        Platform.exit();
                        System.out.println("Close");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }

        });

        // instantiates menu 2D objects
        menuBox = new GameObject2D("menu", 128, 160, scene.getCamera().getTranslateX() + 64, scene.getCamera().getTranslateY() - 192);
        marker2D = new GameObject2D("marker", 12, 12, menuBox.getX() + 16, menuBox.getY() + 32);
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void update(long currentTime) {
        marker2D.setY(menuBox.getY() + 32 * currentMarker);
    }

    @Override
    public void draw() {

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(menuBox.getX(), menuBox.getY(), menuBox.getWidth(), menuBox.getHeight());

        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.strokeRect(menuBox.getX() - 2, menuBox.getY() - 2, menuBox.getWidth() + 2, menuBox.getHeight() + 2);

        graphicsContext.setFill(Color.WHITE);

        graphicsContext.setFont(Font.font("Verdana", FontWeight.MEDIUM, 14));
        graphicsContext.fillText("Save", menuBox.getX() + 32, menuBox.getY() + 32);
        graphicsContext.fillText("Load", menuBox.getX() + 32, menuBox.getY() + 64);
        graphicsContext.fillText("Main Menu", menuBox.getX() + 32, menuBox.getY() + 96);
        graphicsContext.fillText("Quit Game", menuBox.getX() + 32, menuBox.getY() + 128);

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText(">", marker2D.getX(), marker2D.getY());
        // graphicsContext.fillRect(marker2D.getX(), marker2D.getY(), marker2D.getWidth(), marker2D.getHeight());
    }

    @Override
    public void onExit() {
        scene.setOnKeyPressed(null);
        StateStack.getCurrentState().onEnter();
    }

    @Override
    public void onClose() {

    }
}
