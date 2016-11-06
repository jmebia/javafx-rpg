package main.framework.state;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * serves as the template of other game states or levels
 */

public interface IState {

    public void onEnter();

    public void update(long currentTime);

    public void draw();

    public void onExit();

}
