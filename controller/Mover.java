package main.framework.controller;

import main.framework.gameobjects.Character;

import java.util.ArrayList;

public class Mover {

    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private Controller controller;
    private Character character;
    private ArrayList<String> input;

    public Mover(Controller controller, Character character) {
        this.character = character;
        this.controller = controller;
    }

    public void update() {

        input = controller.getInputs();

        if(input.contains("RIGHT"))
            movingRight = true;
        else
            movingRight = false;


        if(input.contains("LEFT"))
            movingLeft = true;
        else
            movingLeft = false;


        if(input.contains("UP"))
            movingUp = true;
        else
            movingUp = false;


        if(input.contains("DOWN"))
            movingDown = true;
        else
            movingDown = false;

        updatePos();
    }


    public void updatePos() {

        if(movingRight){
            if (character.isFacingRight() && character.getCollision() == null)
                character.setX(character.getX() + character.getVelocity());
            else
                character.setFaceRight();
        }

        else if(movingLeft){
            if (character.isFacingLeft() && character.getCollision() == null)
                character.setX(character.getX() - character.getVelocity());
            else
                character.setFaceLeft();
        }

        else if(movingUp){
            if (character.isFacingUp() && character.getCollision() == null)
                character.setY(character.getY() - character.getVelocity());
            else
                character.setFaceUp();
        }

        else if(movingDown){
            if (character.isFacingDown() && character.getCollision() == null)
                character.setY(character.getY() + character.getVelocity());
            else
                character.setFaceDown();
        }

    }

}
