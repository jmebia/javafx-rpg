package main.framework.gameobjects;

public class GameObject {

    private String name;

    // movement and placement properties
    private double width;
    private double height;
    private double X;
    private double Y;
    private double velocity = 0;

    /** ====================== CONSTRUCTOR ============================ **/

    public GameObject(String name, double width, double height, double x, double y) {
        this.name = name;
        this.width = width;
        this.height = height;
        X = x;
        Y = y;
    }

    /** ============================ GETTERS ================================ **/

    public String getName() {
        return name;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getVelocity() {
        return velocity;
    }

    /** =========================== SETTERS ============================= **/

    public void setName(String name) {
        this.name = name;
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }

    public void setVelocity(double v) {
        velocity = v;
    }


    /** ========================= COLLISION HANDLERS ===================== **/

    public double[] getBounds() {
        double[] bounds = {
                X,              // 0
                Y,              // 1
                X + getWidth(), // 2
                Y + getHeight() // 3
        };
        return bounds;
    }

}
