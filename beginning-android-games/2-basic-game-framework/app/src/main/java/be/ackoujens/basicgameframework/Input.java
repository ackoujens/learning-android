package be.ackoujens.basicgameframework;

import java.util.List;

/**
 * Input
 * polling access to the touchscreen, keyboard and accelerometer
 */
public interface Input {
    /**
     * Defines constants to encode a KeyEvent type
     * - type
     * - key code
     * - unicode character in case of KEY_UP
     */
    public static class KeyEvent {
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP   = 1;

        public int  type;
        public int  keyCode;
        public char keyChar;
    }

    /**
     * Defines constants to encode TouchEvent type
     * - type
     * - position of finger relative to UI component origin
     * - pointer ID that was given to a finger
     */
    public static class TouchEvent {
        public static final int TOUCH_DOWN    = 0;
        public static final int TOUCH_UP      = 1;
        public static final int TOUCH_DRAGGED = 2;

        public int type;
        public int x, y;
        public int pointer;
    }

    /**
     * Takes keyCode, returns if corresponding key is pressed or not
     * @param keyCode
     * @return boolean
     */
    public boolean isKeyPressed(int keyCode);

    /**
     * Takes pointer, returns if corresponding finger is touching or not
     * @param pointer
     * @return boolean
     */
    public boolean isTouchDown (int pointer);

    /**
     * Get x-position of active pointer
     * @param pointer
     * @return x of pointer
     */
    public int     getTouchX   (int pointer);

    /**
     * Get y-position of active pointer
     * @param pointer
     * @return y of pointer
     */
    public int     getTouchY   (int pointer);

    /**
     * Get acceleration value on the x-axis
     * @return x acceleration
     */
    public float   getAccelX();

    /**
     * Get acceleration value on the y-axis
     * @return y acceleration
     */
    public float   getAccelY();

    /**
     * Get acceleration value on the z-axis
     * @return z acceleration
     */
    public float   getAccelZ();

    /**
     * Used for event based handling
     * - returns KeyEvent instances that got recorded since last call
     * - events are ordered according to when they occurred
     *  - newest event at the end of the list
     *  - oldest event at the start of the list
     * @return KeyEvent List
     */
    public List<KeyEvent>   getKeyEvents();

    /**
     * - returns TouchEvent instances that got recorded since last call
     * - events are ordered according to when they occurred
     *  - newest event at the end of the list
     *  - oldest event at the start of the list
     * @return TouchEvent List
     */
    public List<TouchEvent> getTouchEvents();
}
