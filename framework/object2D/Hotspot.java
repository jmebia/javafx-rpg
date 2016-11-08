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

    public Hotspot(String name, int width, int height, int x, int y) {
        super(name, width, height, x, y);
    }

    public void addTriggerCharacter(Character2D character2D) {
        allowedCharacter2Ds.add(character2D);
    }

    public boolean isCharacterOnHotspot() {

        boolean triggered = false;

        for (Character2D character2D : allowedCharacter2Ds) {

            double[] characterBounds = character2D.getBounds();
            double[] hotspotBounds = this.getBounds();

            if(characterBounds[0] >= hotspotBounds[0] && characterBounds[1] >= hotspotBounds[1] &&
                    characterBounds[2] <= hotspotBounds[2] && characterBounds[3] <= hotspotBounds[3] ) {
                // returns true if character2D is inside the hotspot
                triggered = true;
            }
        }

        return triggered;
    }

}
