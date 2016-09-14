package be.ackoujens.androidgameframework;

/**
 * Graphics
 */
public interface Graphics {
    /**
     * Encodes supported pixel formats
     */
    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    /**
     * Load JPEG or PNG format specified by the fileName argument
     * @param fileName
     * @param format
     * @return Pixmap made from selected image
     */
    public Pixmap newPixmap (String fileName, PixmapFormat format);

    /**
     * Clears complete framebuffer with the given color
     * @param color
     */
    public void   clear     (int color);

    /**
     * Will set the pixel at x,y in the framebuffer to a given color
     * @param x
     * @param y
     * @param color
     */
    public void   drawPixel (int x, int y,  int color);

    /**
     * Draw a line from start to end coordinate in a given color
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param color
     */
    public void   drawLine  (int x, int y,  int x2,       int y2,     int color);


    /**
     * Draw a rectangle starting from the top-left x,y in a given color
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public void   drawRect  (int x, int y,  int width,    int height, int color);

    /**
     * Draws rectangular portions of a Pixmap to the framebuffer
     * @param pixmap
     * @param x
     * @param y
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    public void   drawPixmap(Pixmap pixmap, int x,        int y,
                                            int srcX,     int srcY,
                                            int srcWidth, int srcHeight);

    /**
     * Draws the entire Pixmap to the framebuffer
     * @param pixmap
     * @param x
     * @param y
     */
    public void   drawPixmap(Pixmap pixmap, int x,        int y);

    /**
     * Return the width of the framebuffer in pixels
     * @return Width of framebuffer
     */
    public int    getWidth();

    /**
     * Return the height of the framebuffer in pixels
     * @return height of framebuffer
     */
    public int    getHeight();
}
