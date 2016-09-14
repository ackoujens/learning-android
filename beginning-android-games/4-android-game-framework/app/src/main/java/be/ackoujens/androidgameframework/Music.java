package be.ackoujens.androidgameframework;

/**
 * Music
 * playback of streamed audio
 */
public interface Music {
    /**
     * Start playing music stream
     */
    public void play();

    /**
     * Stop playing music stream
     */
    public void stop();

    /**
     * Start from the beginning when music stream reaches the end of the audio file
     * @param looping
     */
    public void setLooping(boolean looping);

    /**
     * Set volume in float values
     * @param volume
     */
    public void setVolume(float volume);

    /**
     * Check if audio is playing
     * @return boolean
     */
    public boolean isPlaying();

    /**
     * Check if audio is stopped
     * @return boolean
     */
    public boolean isStopped();

    /**
     * Check if audio is looping
     * @return boolean
     */
    public boolean isLooping();

    /**
     * Close any system resources related to the Music instance
     */
    public void dispose();
}
