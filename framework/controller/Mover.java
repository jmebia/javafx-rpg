package main.framework.controller;

import main.framework.object2D.Character2D;

import java.util.ArrayList;

public class Mover {

    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private Controller controller;
    private Character2D character2D;
    private ArrayList<String> input;

    public Mover(Controller controller, Character2D character2D) {
        this.character2D = character2D;
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
            if (character2D.isFacingRight() && character2D.getCollision() == null)
                character2D.setX(character2D.getX() + character2D.getVelocity());
            else
                character2D.setFaceRight();
        }

        else if(movingLeft){
            if (character2D.isFacingLeft() && character2D.getCollision() == null)
                character2D.setX(character2D.getX() - character2D.getVelocity());
            else
                character2D.setFaceLeft();
        }

        else if(movingUp){
            if (character2D.isFacingUp() && character2D.getCollision() == null)
                character2D.setY(character2D.getY() - character2D.getVelocity());
            else
                character2D.setFaceUp();
        }

        else if(movingDown){
            if (character2D.isFacingDown() && character2D.getCollision() == null)
                character2D.setY(character2D.getY() + character2D.getVelocity());
            else
                character2D.setFaceDown();
        }

    }

}
