package main.framework.animation;


import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.framework.controller.Mover;

public class SpriteAnimator extends Transition{

    private ImageView imageView;
    private int count;
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

    public void update(Mover mover, int offsetYDown, int offsetYLeft, int offsetYRight, int offsetYUp) {
        if(mover.isMoving()) {
            play();
            count = 3;
            offsetX = 0;

            // diagonal movement
            if (mover.isMovingUp() && mover.isMovingRight() ) {
                offsetY = offsetYUp;
                offsetX = 96;
            }
            else if (mover.isMovingUp() && mover.isMovingLeft()) {
                offsetY = offsetYLeft;
                offsetX = 96;
            }
            else if (mover.isMovingDown() && mover.isMovingRight()) {
                offsetY = offsetYRight;
                offsetX = 96;
            }
            else if (mover.isMovingDown() && mover.isMovingLeft()) {
                offsetY = offsetYDown;
                offsetX = 96;
            }

            // straight movement
            else if (mover.getCharacter2D().isFacingDown()) offsetY = offsetYDown;
            else if (mover.getCharacter2D().isFacingLeft()) offsetY = offsetYLeft;
            else if (mover.getCharacter2D().isFacingRight()) offsetY = offsetYRight;
            else if (mover.getCharacter2D().isFacingUp()) offsetY = offsetYUp;
        } else {
            count = 1;
            offsetX = 32;
        }
    }

    public void updateView(Mover mover) {
        // updating
        imageView.setTranslateX(mover.getCharacter2D().getX() - 6); // default is without the minus modifier
        imageView.setTranslateY(mover.getCharacter2D().getY() - 16); // default is without the minus modifier
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac * count), count - 1);
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }

}
