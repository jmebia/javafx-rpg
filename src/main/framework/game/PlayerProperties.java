package main.framework.game;


import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import main.framework.controller.Controller;
import main.framework.controller.Mover;
import main.framework.entities.Character;
import main.framework.object2D.Character2D;

public enum PlayerProperties {

    Player1;

    private PerspectiveCamera camera = null;
    private Character character = null;
    private Character2D character2D = null;
    private Controller controller = null;
    private Mover mover = null;

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public Character getCharacter() {
        return character;
    }

    public Character2D getCharacter2D() {
        return character2D;
    }

    public Controller getController() {
        return controller;
    }

    public Mover getMover() {
        return mover;
    }

    public void setCamera(PerspectiveCamera camera) {
        this.camera = camera;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setCharacter2D(Character2D character2D) {
        this.character2D = character2D;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

}
