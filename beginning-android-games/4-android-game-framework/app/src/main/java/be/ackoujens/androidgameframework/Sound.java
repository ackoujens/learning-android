package be.ackoujens.androidgameframework;

/**
 * Sound
 * playback of fully loaded audio
 */
public interface Sound {
    /**
     * Play audio file in the specified volume
     * @param volume
     */
    public void play   (float volume);

    /**
     * Close any system resources related to the Sound instance
     */
    public void dispose();
}
