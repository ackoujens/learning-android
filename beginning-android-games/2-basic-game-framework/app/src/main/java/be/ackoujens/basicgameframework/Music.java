package be.ackoujens.basicgameframework;

/**
 * Music
 * playback of streamed audio
 */
public interface Music {
    // Start playing music stream
    public void play();

    // Stop playing music stream
    public void stop();

    // Start from the beginning when music stream reaches the end of the audio file
    public void setLooping(boolean looping);

    // Set volume in float values
    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    // Close any system resources related to the Music instance
    public void dispose();
}
