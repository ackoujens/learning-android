package be.ackoujens.basicgameframework;

import be.ackoujens.basicgameframework.Graphics.PixmapFormat;

/**
 * Pixmap
 */
public interface Pixmap {
    /**
     * Get width of Pixmap in pixels
     * @return Pixmap width
     */
    public int getWidth();

    /**
     * Get height of Pixmap in pixel
     * @return Pixmap height
     */
    public int getHeight();

    /**
     * Returns PixelFormat, that the Pixmap is stored with in RAM
     * @return PixelFormat of Pixmap
     */
    public PixmapFormat getFormat();

    /**
     * Release memory and other system resources related to Pixmap instance
     */
    public void         dispose();
}
