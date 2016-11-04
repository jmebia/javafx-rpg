package main.java;


import javafx.scene.Scene;

import java.util.ArrayList;

public class Controller {

    private ArrayList<String> input;

    public Controller(Scene scene) {

        input = new ArrayList<>();

        scene.setOnKeyPressed(e -> {

            String keyCode = e.getCode().toString();
            if (!input.contains(keyCode))
                input.add(keyCode);

        });

        scene.setOnKeyReleased(e -> {

            String keyCode = e.getCode().toString();
            input.remove(keyCode);

        });
    }

    public ArrayList<String> getInputs() {
        return input;
    }

}
