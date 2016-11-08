package main.framework.state;


import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class CombatState implements IState {

    /**----------------------------------**/

    private Scene scene;
    private GraphicsContext graphicsContext;

    /**----------------------------------**/

    private Character playerCharacter;

    public CombatState(Scene scene, GraphicsContext graphicsContext) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void update(long currentTime) {

    }

    @Override
    public void draw() {

    }

    @Override
    public void onExit() {

    }
}
