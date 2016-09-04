package be.ackoujens.basicgameframework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * FileIO
 * reading and writing files
 * packaged files
 * level files, images, audio files, high scores, game settings, save game states
 */
public interface FileIO {
    // Specify a filename and get a stream in return.
    // Throw an IOException if something goes wrong.

    /**
     * Assets will be read from the applications APK file
     * @param filename
     * @return InputStream
     * @throws IOException
     */
    public InputStream  readAsset(String filename) throws IOException;

    /**
     * Files will be read from the SD card
     * @param filename
     * @return InputStream
     * @throws IOException
     */
    public InputStream  readFile (String filename) throws IOException;

    /**
     * Files will br written on the SD card
     * @param filename
     * @return OutputStream
     * @throws IOException
     */
    public OutputStream writeFile(String filename) throws IOException;
}
