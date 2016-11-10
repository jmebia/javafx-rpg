package main.framework.animation;


import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.framework.controller.Mover;
import main.framework.object2D.Character2D;

public class SpriteAnimator extends Transition{

    private ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;

    public SpriteAnimator(
            ImageView imageView,
            Duration duration,
            int count, int columns,
            int offsetX, int offsetY,
            int width, int height) {

        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
    }

    public void update(Character2D character) {
        if (character.isFacingDown()) offsetY = 0;
        else if (character.isFacingLeft()) offsetY = 32;
        else if (character.isFacingRight()) offsetY = 64;
        else if (character.isFacingUp()) offsetY = 96;

        // updating
        imageView.setTranslateX(character.getX());
        imageView.setTranslateY(character.getY());
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac * count), count - 1);
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }

}
