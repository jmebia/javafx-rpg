package main.java;

import java.util.ArrayList;

public class Mover {

    private Controller controller;
    private Character character;

    public Mover(Controller controller, Character character) {
        this.character = character;
        this.controller = controller;
    }


    public void update() {

        ArrayList<String> input = controller.getInputs();

        if(input.contains("RIGHT")){
            if (character.isFacingRight() && character.getCollision() == null)
                character.setX(character.getX() + character.getVelocity());
            else
                character.setFaceRight();
        }

        if(input.contains("LEFT")){
            if (character.isFacingLeft() && character.getCollision() == null)
                character.setX(character.getX() - character.getVelocity());
            else
                character.setFaceLeft();
        }

        if(input.contains("UP")){
            if (character.isFacingUp() && character.getCollision() == null)
                character.setY(character.getY() - character.getVelocity());
            else
                character.setFaceUp();
        }

        if(input.contains("DOWN")){
            if (character.isFacingDown() && character.getCollision() == null)
                character.setY(character.getY() + character.getVelocity());
            else
                character.setFaceDown();
        }

    }


    /**
    if (character.isFacingRight() && character.getCollision() == null)
            character.setX(character.getX() + character.getWidth());
                    else
                            character.setFaceRight();

    */
}
