package be.ackoujens.androidgameframework;

/**
 * Game
 * hide the complexity for ease of use
 */
public interface Game {
    /**
     * Access to Input module
     * @return Input instance
     */
    public Input getInput();

    /**
     * Access to FileIO module
     * @return FileIO instance
     */
    public FileIO getFileIO();

    /**
     * Access to Graphics module
     * @return Graphics instance
     */
    public Graphics getGraphics();

    /**
     * Access to Audio module
     * @return Audio instance
     */
    public Audio getAudio();

    /**
     * Set current screen of the game
     * @param screen
     */
    public void setScreen(Screen screen);

    /**
     * Returns currently active Screen instance
     * @return Active Screen
     */
    public Screen getCurrentScreen();

    /**
     * Abstract method to be overridden
     * @return First screen of game
     */
    public Screen getStartScreen();
}
