package main.framework.gameobjects;

import java.util.ArrayList;


public class Hotspot extends GameObject {
    /**
     * ====================== CONSTRUCTOR ============================
     *
     * @param name
     * @param width
     * @param height
     * @param x
     * @param y
     **/

    private ArrayList<Character> allowedCharacters = new ArrayList<>();

    public Hotspot(String name, int width, int height, int x, int y) {
        super(name, width, height, x, y);
    }

    public void addTriggerCharacter(Character character) {
        allowedCharacters.add(character);
    }

    public boolean isCharacterOnHotspot() {

        boolean triggered = false;

        for (Character character : allowedCharacters) {

            double[] characterBounds = character.getBounds();
            double[] hotspotBounds = this.getBounds();

            if(characterBounds[0] >= hotspotBounds[0] && characterBounds[1] >= hotspotBounds[1] &&
                    characterBounds[2] <= hotspotBounds[2] && characterBounds[3] <= hotspotBounds[3] ) {
                // returns true if character is inside the hotspot
                triggered = true;
            }
        }

        return triggered;
    }

}
