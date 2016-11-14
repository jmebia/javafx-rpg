package main.framework.object2D;

import java.util.ArrayList;

public class Character2D extends GameObject2D {

    private boolean isFacingRight = false;
    private boolean isFacingDown = true;
    private boolean isFacingUp = false;
    private boolean isFacingLeft = false;

    private ArrayList<GameObject2D> collisions = new ArrayList<>();

    /**
     * ====================== CONSTRUCTOR ============================
     *
     * @param name
     * @param width
     * @param height
     * @param x
     * @param y
     **/

    public Character2D(String name, double width, double height, double x, double y) {
        super(name, width, height, x, y);
    }

    public Character2D(String name, double width, double height, double x, double y, double velocity) {
        super(name, width, height, x, y);
        this.setVelocity(velocity);
    }


    /** ============================= GETTERS ============================== **/

    public boolean isFacingRight() {
        boolean facing = false;
        if (isFacingRight && !isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    public boolean isFacingLeft() {
        boolean facing = false;
        if (!isFacingRight && !isFacingDown && !isFacingUp && isFacingLeft) facing = true;
        return facing;
    }

    public boolean isFacingDown() {
        boolean facing = false;
        if (!isFacingRight && isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    public boolean isFacingUp() {
        boolean facing = false;
        if (!isFacingRight && !isFacingDown && isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    // diagonal
    public boolean isFacingUpRight() {
        boolean facing = false;
        if (isFacingRight && isFacingUp) facing = true;

        return facing;
    }

    public boolean isFacingUpLeft() {
        boolean facing = false;
        if (isFacingLeft && isFacingUp) facing = true;

        return facing;
    }

    public boolean isFacingDownRight() {
        boolean facing = false;
        if (isFacingRight && isFacingDown) facing = true;

        return facing;
    }

    public boolean isFacingDownLeft() {
        boolean facing = false;
        if (isFacingDown && isFacingLeft) facing = true;

        return facing;
    }


    /** =========================== SETTERS =========================== **/

    // face setters
    public void setFaceRight() {
        isFacingRight = true;
        isFacingDown = false;
        isFacingUp = false;
        isFacingLeft = false;
    }

    public void setFaceLeft() {
        isFacingRight = false;
        isFacingDown = false;
        isFacingUp = false;
        isFacingLeft = true;
    }

    public void setFaceDown() {
        isFacingRight = false;
        isFacingDown = true;
        isFacingUp = false;
        isFacingLeft = false;
    }

    public void setFaceUp() {
        isFacingRight = false;
        isFacingDown = false;
        isFacingUp = true;
        isFacingLeft = false;
    }

    // diagonal faces
    public void setFaceUpRight() {
        isFacingRight = true;
        isFacingDown = false;
        isFacingUp = true;
        isFacingLeft = false;
    }

    public void setFaceUpLeft() {
        isFacingRight = false;
        isFacingDown = false;
        isFacingUp = true;
        isFacingLeft = true;
    }

    public void setFaceDownRight() {
        isFacingRight = true;
        isFacingDown = true;
        isFacingUp = false;
        isFacingLeft = false;
    }

    public void setFaceDownLeft() {
        isFacingRight = false;
        isFacingDown = true;
        isFacingUp = false;
        isFacingLeft = true;
    }

    /** ==================== COLLISION ============================ **/

    public void addCollision(GameObject2D gameObject2D) {
        collisions.add(gameObject2D);
    }

    public void addCollision(ArrayList<GameObject2D> gameObject2D) {
        collisions.addAll(gameObject2D);
    }

    public String getVerticalCollision() {
        String collided = "NONE";

        // look through the list of collisions for this entities
        for(GameObject2D c : collisions) {

            // vertical check
            if(this.isFacingUp) {
                if (c.getY() + c.getHeight() >= this.getY() && this.getY() + this.getHeight() > c.getY() + c.getHeight()
                        && ((this.getX() >= c.getX() && this.getX() + this.getWidth() <= c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() < c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) ||
                        (this.getX() + this.getWidth() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) )) {

                    collided = "UP";
                    System.out.println(this.getName() + ": Collided Upward");
                }
            }

            else if(this.isFacingDown) {
                if ( this.getY() + this.getHeight() >= c.getY() && this.getY() < c.getY()
                        && ((this.getX() >= c.getX() && this.getX() + this.getWidth() <= c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() < c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) ||
                        (this.getX() + this.getWidth() > c.getX()&& this.getX() + this.getWidth() < c.getX() + c.getWidth()) )) {

                    collided = "DOWN";
                    System.out.println(this.getName() + ": Collided downward");
                }
            }
        }
        return collided;
    }

    public String getHorizontalCollision() {
        // horizontal check
        String collided = "NONE";

        // look through the list of collisions for this entities
        for (GameObject2D c : collisions) {
            if (this.isFacingRight) {
                if (this.getX() + this.getWidth() >= c.getX() && c.getX() > this.getX()
                        && ((this.getY() >= c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() + this.getHeight() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()))) {

                    collided = "RIGHT";
                    System.out.println(this.getName() + ": Collided rightward");
                }
            } else if (this.isFacingLeft) {
                if (this.getX() <= c.getX() + c.getWidth() && c.getX() + c.getWidth() < this.getX() + this.getWidth()
                        && ((this.getY() >= c.getY() && this.getY() + this.getHeight() <= c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() + this.getHeight() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()))) {

                    collided = "LEFT";
                    System.out.println(this.getName() + ": Collided leftward");
                }
            }
        }
        return collided;
    }


    /** ============================== ACTION EVENT HANDLER ===================== **/

    public void actionBy(Character2D c) {
        // get action of Character2D c for this entities
    }

}
