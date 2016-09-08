package be.ackoujens.androidtesting;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

/**
 * MultiTouch
 * registering and handling multi touch events
 */

// Needed when accessing API's that are not part of the minimum SDK
@TargetApi(5)

/**
 * Implementing OnTouchListener to keep track of coords and touch state of 10 fingers
 * Global variables for keeping data about all 10 fingers
 */
public class MultiTouch extends Activity implements OnTouchListener {
    StringBuilder builder = new StringBuilder();
    TextView      textView;
    float[]       x       = new float[10];
    float[]       y       = new float[10];
    boolean[]     touched = new boolean[10];
    int[]         id      = new int[10];

    /**
     * Displaying information about all 10 fingers
     * Updates textView afterwards
     */
    public void updateTextView() {
        builder.setLength(0);
        for (int i = 0; i < 10; i++) {
            builder.append(touched[i]);
            builder.append(", ");
            builder.append(id[i]);
            builder.append(", ");
            builder.append(x[i]);
            builder.append(", ");
            builder.append(y[i]);
            builder.append("\n");
        }
        textView.setText(builder.toString());
    }

    /**
     * Initialize new TextView and set as content view
     * Initialize all pointer id's
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate             (savedInstanceState);
        textView = new TextView    (this);
        textView.setText           ("Touch and drag (multiple fingers supported)!");
        textView.setOnTouchListener(this);
        setContentView             (textView);
        for (int i = 0; i < 10; i++) {
            id[i] = -1;
        }
        updateTextView();
    }

    /**
     * Handle multi touch events
     * @param v
     * @param event
     * @return boolean
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Get event type by masking the integer returned by event.getAction()
        int action = event.getAction() & MotionEvent.ACTION_MASK;

        // Extract pointer index and fetch the corresponding pointer identifier from MotionEvent
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < 10; i++) {
            if (i >= pointerCount) {
                touched[i] = false;
                id[i] = -1;
                continue;
            }

            if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) {
                // if it's an up/down/cancel/out event, mask the id to see if we should process it for this touch point
                continue;
            }

            int pointerId = event.getPointerId(i);
            switch (action) {

                /**
                 * A touch-down event happened
                 * - set touch state for pointer identifier to true
                 * - save current coords of that pointer
                 */
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    touched[i] = true;
                    id[i]      = pointerId;
                    x[i]       = (int)event.getX(i);
                    y[i]       = (int)event.getY(i);
                    break;

                /**
                 * A touch-up event happened
                 * - set touch state for pointer identifier to false
                 * - save last known coords of that pointer
                 */
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                    touched[i] = false;
                    id[i]      = -1;
                    x[i]       = (int)event.getX(i);
                    y[i]       = (int)event.getY(i);
                    break;

                /**
                 * One or more fingers where dragged across the screen
                 * - check how many events are contained in MotionEvent
                 * - update coords for the pointer indices 0 to MotionEvent.getPointerCount()-1
                 * - fetch corresponding pointer id for each event and update coords
                 */
                case MotionEvent.ACTION_MOVE:
                    touched[i] = true;
                    id[i]      = pointerId;
                    x[i]       = (int)event.getX(i);
                    y[i]       = (int)event.getY(i);
                    break;
            }
        }
        updateTextView();
        return true;
    }
}
