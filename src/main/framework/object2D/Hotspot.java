package main.framework.object2D;

import java.util.ArrayList;


public class Hotspot extends GameObject2D {
    /**
     * ====================== CONSTRUCTOR ============================
     *
     * @param name
     * @param width
     * @param height
     * @param x
     * @param y
     **/

    private ArrayList<Character2D> allowedCharacter2Ds = new ArrayList<>();

    public Hotspot(String name, double width, double height, double x, double y) {
        super(name, width, height, x, y);
    }

    public void addTriggerCharacter(Character2D character2D) {
        allowedCharacter2Ds.add(character2D);
    }

    public boolean isCharacterOnHotspot() {

        boolean triggered = false;

        for (Character2D character2D : allowedCharacter2Ds) {

            if( this.intersects(character2D.getLayoutBounds()) ) {
                // returns true if character2D touches the hotspot
                triggered = true;
            }
        }

        return triggered;
    }

}
