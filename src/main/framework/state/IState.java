package main.framework.state;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * serves as the template of other game states or rooms
 */

public interface IState {

    void init();

    void onEnter();

    void update(long currentTime);

    void draw();

    void onExit();

    void onClose();

}
