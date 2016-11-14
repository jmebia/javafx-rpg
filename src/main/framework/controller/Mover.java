package main.framework.controller;

import main.framework.entities.Character;
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

    public Character2D getCharacter2D() {
        return character2D;
    }

    public boolean isMoving() {
        boolean retval = false;
        if (movingLeft || movingUp || movingRight || movingDown) retval = true;
        return retval;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
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


    public void updatePos() throws NullPointerException {

        // diagonal
        if(movingUp && movingRight){
            if (character2D.isFacingUpRight()) {
                if (character2D.getHorizontalCollision() == "NONE" && character2D.getVerticalCollision() == "NONE") {
                    character2D.setX(character2D.getX() + character2D.getVelocity()); // move right
                    character2D.setY(character2D.getY() - character2D.getVelocity()); // move up
                    System.out.println("Moving diagonally up-right");
                }
                else if (character2D.getVerticalCollision() == "UP" && character2D.getHorizontalCollision() == "NONE") {
                    character2D.setX(character2D.getX() + character2D.getVelocity() ); // move right

                }
                else if (character2D.getHorizontalCollision() == "RIGHT" && character2D.getVerticalCollision() == "NONE") {
                    character2D.setY(character2D.getY() - character2D.getVelocity() ); // move up

                }
            } else character2D.setFaceUpRight();
        }

        else if(movingUp && movingLeft){
            if (character2D.isFacingUpLeft()) {
                if (character2D.getVerticalCollision() == "NONE" && character2D.getHorizontalCollision() == "NONE") {
                    character2D.setX(character2D.getX() - character2D.getVelocity()); // move left
                    character2D.setY(character2D.getY() - character2D.getVelocity()); // move up

                }
                else if (character2D.getVerticalCollision() == "UP" && character2D.getHorizontalCollision() == "NONE") {
                    character2D.setX(character2D.getX() - character2D.getVelocity() ); // move left

                }
                else if (character2D.getHorizontalCollision() == "LEFT" && character2D.getVerticalCollision() == "NONE") {
                    character2D.setY(character2D.getY() - character2D.getVelocity() ); // move up

                }
            } else character2D.setFaceUpLeft();
        }

        else if(movingDown && movingRight){
            if (character2D.isFacingDownRight()) {
                if (character2D.getVerticalCollision() == "NONE" && character2D.getHorizontalCollision() == "NONE") {
                    character2D.setX(character2D.getX() + character2D.getVelocity()); // move right
                    character2D.setY(character2D.getY() + character2D.getVelocity()); // move down

                }
                else if (character2D.getVerticalCollision() == "DOWN" && character2D.getHorizontalCollision() == "NONE") {
                    character2D.setX(character2D.getX() + character2D.getVelocity() ); // move right

                }
                else if (character2D.getHorizontalCollision() == "RIGHT" && character2D.getVerticalCollision() == "NONE") {
                    character2D.setY(character2D.getY() + character2D.getVelocity() ); // move down

                }
            } else character2D.setFaceDownRight();
        }

        else if(movingDown && movingLeft){
            if (character2D.isFacingDownLeft()) {
                if (character2D.getVerticalCollision() == "NONE" && character2D.getHorizontalCollision() == "NONE") {
                    character2D.setX(character2D.getX() - character2D.getVelocity()); // move left
                    character2D.setY(character2D.getY() + character2D.getVelocity()); // move down

                }
                else if (character2D.getVerticalCollision() == "DOWN" && character2D.getHorizontalCollision() == "NONE") {
                    character2D.setX(character2D.getX() - character2D.getVelocity() ); // move left

                }
                else if (character2D.getHorizontalCollision() == "LEFT" && character2D.getVerticalCollision() == "NONE") {
                    character2D.setY(character2D.getY() + character2D.getVelocity() ); // move down

                }
            } else character2D.setFaceDownLeft();
        }

        // straight movement
        else if(movingRight){
            if (character2D.isFacingRight() && character2D.getHorizontalCollision() == "NONE")
                character2D.setX(character2D.getX() + character2D.getVelocity());
            else
                character2D.setFaceRight();
        }

        else if(movingLeft){
            if (character2D.isFacingLeft() && character2D.getHorizontalCollision() == "NONE")
                character2D.setX(character2D.getX() - character2D.getVelocity());
            else
                character2D.setFaceLeft();
        }

        else if(movingUp){
            if (character2D.isFacingUp() && character2D.getVerticalCollision() == "NONE")
                character2D.setY(character2D.getY() - character2D.getVelocity());
            else
                character2D.setFaceUp();
        }

        else if(movingDown){
            if (character2D.isFacingDown() && character2D.getVerticalCollision() == "NONE")
                character2D.setY(character2D.getY() + character2D.getVelocity());
            else
                character2D.setFaceDown();
        }

    }

}
