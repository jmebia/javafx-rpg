package main.framework.state;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * serves as the template of other game states or levels
 */

public abstract class IState {

    public IState() { onEnter(); }

    public void onEnter() {}

    public void update(Scene scene, long currentTime) {}

    public void draw(GraphicsContext gc) {}

    public void onExit(Scene scene) {}

}
