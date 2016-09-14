package be.ackoujens.androidgameframework;

import java.util.List;

/**
 * AndroidInput
 */
public class AndroidInput implements Input {


    public AndroidInput() {

    }

    /**
     * Takes keyCode, returns if corresponding key is pressed or not
     *
     * @param keyCode
     * @return boolean
     */
    @Override
    public boolean isKeyPressed(int keyCode) {
        return false;
    }

    /**
     * Takes pointer, returns if corresponding finger is touching or not
     *
     * @param pointer
     * @return boolean
     */
    @Override
    public boolean isTouchDown(int pointer) {
        return false;
    }

    /**
     * Get x-position of active pointer
     *
     * @param pointer
     * @return x of pointer
     */
    @Override
    public int getTouchX(int pointer) {
        return 0;
    }

    /**
     * Get y-position of active pointer
     *
     * @param pointer
     * @return y of pointer
     */
    @Override
    public int getTouchY(int pointer) {
        return 0;
    }

    /**
     * Get acceleration value on the x-axis
     *
     * @return x acceleration
     */
    @Override
    public float getAccelX() {
        return 0;
    }

    /**
     * Get acceleration value on the y-axis
     *
     * @return y acceleration
     */
    @Override
    public float getAccelY() {
        return 0;
    }

    /**
     * Get acceleration value on the z-axis
     *
     * @return z acceleration
     */
    @Override
    public float getAccelZ() {
        return 0;
    }

    /**
     * Used for event based handling
     * - returns KeyEvent instances that got recorded since last call
     * - events are ordered according to when they occurred
     * - newest event at the end of the list
     * - oldest event at the start of the list
     *
     * @return KeyEvent List
     */
    @Override
    public List<KeyEvent> getKeyEvents() {
        return null;
    }

    /**
     * - returns TouchEvent instances that got recorded since last call
     * - events are ordered according to when they occurred
     * - newest event at the end of the list
     * - oldest event at the start of the list
     *
     * @return TouchEvent List
     */
    @Override
    public List<TouchEvent> getTouchEvents() {
        return null;
    }
}
