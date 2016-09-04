package be.ackoujens.basicgameframework;

/**
 * Audio
 * loading audio files for streaming and memory playback
 */
public interface Audio {
    /**
     * Takes a filename and returns a Music instance
     * - throws IOException if file loading fails
     * @param filename
     * @return Music
     */
    public Music newMusic(String filename);

    /**
     * Takes a filename and returns a SOund instance
     * - throws IOException if file loading fails
     * @param filename
     * @return Sound
     */
    public Sound newSound(String filename);
}
