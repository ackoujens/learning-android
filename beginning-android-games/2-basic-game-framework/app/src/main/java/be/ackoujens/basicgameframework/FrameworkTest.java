package be.ackoujens.basicgameframework;

import be.ackoujens.basicgameframework.Graphics.PixmapFormat;

/**
 * FrameworkTest
 * - tests completed code and features
 */
public class FrameworkTest extends Screen {
    /**
     * Global resources
     */
    Pixmap awesomePic;
    int x;

    /**
     * Call Game constructor to create a game and apply a simple graphic
     * @param game
     */
    public FrameworkTest(Game game) {
        super(game);
        awesomePic = game.getGraphics().newPixmap("data/pic.png", PixmapFormat.RGB565);
    }

    
    @Override
    public void update(float deltaTime) {
        x += 1;
        if (x > 100)
            x = 0;
        /*
        x += 50 * deltatime;
        if (x > 100)
            x = 0;
         */
    }

    @Override
    public void present(float deltaTime) {
        game.getGraphics().clear(0);
        game.getGraphics().drawPixmap(awesomePic, x, 0, 0, 0,
                awesomePic.getWidth(), awesomePic.getHeight());
    }

    @Override
    public void pause() {
        // nothing to do here
    }

    @Override
    public void resume() {
        // nothing to do here
    }

    @Override
    public void dispose() {
        awesomePic.dispose();
    }


}
