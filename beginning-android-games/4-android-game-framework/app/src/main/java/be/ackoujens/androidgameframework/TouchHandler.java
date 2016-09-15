package be.ackoujens.androidgameframework;

import android.view.View.OnTouchListener;

import java.util.List;

import be.ackoujens.androidgameframework.Input.TouchEvent;

/**
 * TouchHandler
 * used to register the handler with a View
 */
public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}
