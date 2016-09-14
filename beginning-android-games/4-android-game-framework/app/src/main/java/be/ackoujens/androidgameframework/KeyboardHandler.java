package be.ackoujens.androidgameframework;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

import java.util.ArrayList;
import java.util.List;

/**
 * KeyboardHandler
 */
public class KeyboardHandler implements OnKeyListener {
    boolean[] pressedKeys = new boolean[128];
    Pool<KeyEvent> keyEventPool;
    List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
    List<KeyEvent> keyEvents = new ArrayList <KeyEvent>();

    public KeyboardHandler() {

    }

    /**
     * Called when a hardware key is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
