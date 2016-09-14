package be.ackoujens.androidgameframework;

/**
 * Screen
 * abstract class so we can implement some bookkeeping
 */
public abstract class Screen {
    /**
     * Core game instance
     */
    protected final Game game;

    /**
     * Create game instance
     * @param game
     */
    public Screen(Game game) {
        this.game = game;
    }

    /**
     * Update screen
     * @param deltaTime
     */
    public abstract void update(float deltaTime);

    /**
     * Present (updated) screen
     * @param deltaTime
     */
    public abstract void present(float deltaTime);

    /**
     * Pause the game state
     */
    public abstract void pause();

    /**
     * Resume the game from where it left
     */
    public abstract void resume();

    /**
     * Destroy the game when finished
     */
    public abstract void dispose();
}
