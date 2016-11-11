package main.framework.animation;

import javafx.animation.Animation;
import javafx.scene.image.ImageView;
import main.framework.controller.Mover;

public class SpriteAnimationThread implements Runnable {

    SpriteAnimator animator;

    // CONSTRUCTOR
    public SpriteAnimationThread(SpriteAnimator spriteAnimator) {
        animator = spriteAnimator;
        animator.setCycleCount(Animation.INDEFINITE);
    }

    @Override
    public void run() {
        System.out.println("Running thread...");
    }

}
