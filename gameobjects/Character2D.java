package main.framework.gameobjects;

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
        return isFacingRight;
    }

    public boolean isFacingLeft() {
        return isFacingLeft;
    }

    public boolean isFacingDown() {
        return isFacingDown;
    }

    public boolean isFacingUp() {
        return isFacingUp;
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

    /** ==================== COLLISION ============================ **/

    public void addCollision(GameObject2D gameObject2D) {
        collisions.add(gameObject2D);
    }

    public void addCollision(ArrayList<GameObject2D> gameObject2D) {
        collisions.addAll(gameObject2D);
    }

    public GameObject2D getCollision() {
        GameObject2D collided = null;
        double[] characterBounds = this.getBounds();

        // look through the list of collisions for this entities
        for(GameObject2D c : collisions) {
            double[] otherObjectBounds = c.getBounds();

            // checks if the entities collides with something from the direction he's facing
            if(this.isFacingUp()) {
                // if this y1 is less than the y1 of the other, then collide
                if(characterBounds[1] == otherObjectBounds[3] && (characterBounds[3] != otherObjectBounds[1] &&
                        ((characterBounds[0] > otherObjectBounds[0] && characterBounds[0] < otherObjectBounds[2]) ||
                                (characterBounds[2] < otherObjectBounds[2] && characterBounds[2] > otherObjectBounds[0]) ||
                                (characterBounds[0] == otherObjectBounds[0] && characterBounds[2] == otherObjectBounds[2]) ||
                                (characterBounds[0] > otherObjectBounds[0] && characterBounds[2] < otherObjectBounds[2]) ))) {
                    collided = c;
                    System.out.println(this.getName() + ": Collided Upward");
                }
            }

            else if (this.isFacingDown()) {
                if(characterBounds[3] == otherObjectBounds[1] && (characterBounds[1] != otherObjectBounds[3] &&
                        ((characterBounds[0] > otherObjectBounds[0] && characterBounds[0] < otherObjectBounds[2]) ||
                                (characterBounds[2] < otherObjectBounds[2] && characterBounds[2] > otherObjectBounds[0]) ||
                                (characterBounds[0] == otherObjectBounds[0] && characterBounds[2] == otherObjectBounds[2]) ||
                                (characterBounds[0] > otherObjectBounds[0] && characterBounds[2] < otherObjectBounds[2]) ))) {
                    collided = c;
                    System.out.println(this.getName() + ": Collided Downward");
                }
            }

            else if (this.isFacingLeft()) {
                if(characterBounds[0] == otherObjectBounds[2] && (characterBounds[2] != otherObjectBounds[0] &&
                        ((characterBounds[1] > otherObjectBounds[1] && characterBounds[1] < otherObjectBounds[3]) ||
                                (characterBounds[3] < otherObjectBounds[3] && characterBounds[3] > otherObjectBounds[1]) ||
                                (characterBounds[3] == otherObjectBounds[3] && characterBounds[1] == otherObjectBounds[1]) ||
                                (characterBounds[1] > otherObjectBounds[1] && characterBounds[3] < otherObjectBounds[3]) ))) {
                    collided = c;
                    System.out.println(this.getName() + ": Collided Leftward");
                }
            }

            else if (this.isFacingRight()) {
                if(characterBounds[2] == otherObjectBounds[0] && (characterBounds[0] != otherObjectBounds[2] && (
                        (characterBounds[1] > otherObjectBounds[1] && characterBounds[1] < otherObjectBounds[3]) ||
                                (characterBounds[3] < otherObjectBounds[3] && characterBounds[3] > otherObjectBounds[1]) ||
                                (characterBounds[1] > otherObjectBounds[1] && characterBounds[3] < otherObjectBounds[3]) ))) {
                    collided = c;
                    System.out.println(this.getName() + ": Collided Rightward");
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
