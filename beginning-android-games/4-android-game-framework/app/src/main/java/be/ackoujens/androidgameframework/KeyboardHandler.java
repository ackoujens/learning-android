package be.ackoujens.androidgameframework;

import android.view.View;
import android.view.View.OnKeyListener;

import java.util.ArrayList;
import java.util.List;

import be.ackoujens.androidgameframework.Input.KeyEvent;
import be.ackoujens.androidgameframework.Pool;
import be.ackoujens.androidgameframework.Pool.PoolObjectFactory;

/**
 * KeyboardHandler
 * - implements OnKeyListener so it can receive key events from a View
 * - array with 128 Booleans for the state of each key
 * - pool that holds the instances of our KeyEvent class
 * - third member holds the keyEvents that have not yet been consumed by our game, new key events on the UI thread get added to this list
 * - last member stores the KeyEvents that we return by calling KeyboardHandler.getKeyEvents()
 */
public class KeyboardHandler implements OnKeyListener {
    boolean[] pressedKeys = new boolean[128];
    Pool<KeyEvent> keyEventPool;
    List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
    List<KeyEvent> keyEvents = new ArrayList <KeyEvent>();

    /**
     * KeyboardHandler
     * - access View to receive key events from
     * - create the Pool instance with a proper PoolObjectFactory
     * - register the handler as an OnKeyListener with the View
     * - make sure that the View will receive key events by making it the focused View
     * @param view
     */
    public KeyboardHandler(View view) {
        PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>() {
            @Override
            public KeyEvent createObject() {
                return new KeyEvent();
            }
        };
        keyEventPool = new Pool<KeyEvent>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    /**
     * Called when a hardware key is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * - block ACTION_MULTIPLE events
     * - synchronized block bacause we are receiving events from the UI thread
     *      - fetch KeyEvent instance from the Pool -> recycled instance or new one
     *      - set the KeyEvent's keyCode & keyChar members
     *      - decode the Android KeyEvent type -> set type of our KeyEvent + element in the pressedKey array
     *      - add KeyEvent to the previously defined keyEventBuffer list
     *
     * @param v       The view the key has been dispatched to.
     * @param keyCode The code for the physical key that was pressed
     * @param event   The KeyEvent object containing full information about
     *                the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
        if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE) return false;

        synchronized (this) {
            KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char)event.getUnicodeChar();

            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                keyEvent.type = KeyEvent.KEY_DOWN;
                if (keyCode > 0 && keyCode < 127) {
                    pressedKeys[keyCode] = true;
                }
            }

            if (event.getAction() == android.view.KeyEvent.ACTION_UP) {
                keyEvent.type = KeyEvent.KEY_UP;
                if (keyCode > 0 && keyCode < 127) {
                    pressedKeys[keyCode] = false;
                }
            }

            keyEventsBuffer.add(keyEvent);
        }
        return false;
    }

    /**
     * isKeyPressed
     * @param keyCode
     * @return if keyCode is pressed or not
     */
    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode > 127) return false;
        return pressedKeys[keyCode];
    }

    /**
     * getKeyEvents
     * - loop through keyEvents array and insert all of it's keyEvents into our Pool
     * - clear keyEvents list and fill it with the events in our keyEventsBuffer list
     * - clear keyEventsBuffer list
     * - return newly filled keyEvents list to the caller
     * @return
     */
    public List<KeyEvent> getKeyEvents() {
        synchronized (this) {
            int len = keyEvents.size();
            for (int i = 0; i < len; i++) {
                keyEventPool.free(keyEvents.get(i));
            }
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }
    /*
    Example
    -------
    UI thread:   onKey() ->
                 keyEvents = { },         keyEventsBuffer = {KeyEvent1}, pool = { }
    Main thread: getKeyEvents() ->
                 keyEvents = {KeyEvent1}, keyEventsBuffer = { },         pool = { }
    UI thread:   onKey() ->
                 keyEvents = {KeyEvent1}, keyEventsBuffer = {keyEvent2}, pool = { }
    Main thread: getKeyEvents() ->
                 keyEvents = {KeyEvent2}, keyEventsBuffer = { },         pool = {keyEvent1}
    UI thread:   onKey() ->
                 keyEvents = {KeyEvent2}, keyEventsBuffer = {KeyEvent1}, pool = { }
     */

    /*
    1. New event in the UI thread. Nothing in Pool yet -> new KeyEvent instance -> inserted in keyEventsBuffer
    2. getKeyEvents() on main thread -> takes KeyEvent1 from the keyEventsBuffer list -> puts into keyEvents list that is returned to caller
    3. Another event in the UI thread. Still nothing in Pool yet -> create new KeyEvent instance -> inserted in keyEventsBuffer
    4. getKeyEVents() on main thread -> upon entry into method keyEvents list still holds KeyEvent1 -> insertion loop places event into our Pool -> clears keyEvents list & inserts any KeyEvent into keyEventsBuffer (KeyEvent2) = we recycled a key event
    5. New event in the UI thread. We have a free KeyEvent in our Pool -> reuse = NO GARBAGE COLLECTION
    !! getKeyEvents needs to be called frequently or keyEvents list fills up quickly and no objects are returned to the Pool !!
     */
}
