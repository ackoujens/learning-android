package be.ackoujens.androidtesting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

/**
 * SingleTouch
 * registering and handling single touch events
 */
public class SingleTouch extends Activity implements OnTouchListener {
    StringBuilder builder = new StringBuilder();
    TextView textView;

    /**
     * Create textView and set TouchListener & ContentView
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Touch and drag (one finger only)!");
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    /**
     * OnTouch handler
     * - ACTION_DOWN: when finger touches screen
     * - ACTION_MOVE: when finger moves
     * - ACTION_CANCEL: when current gesture is cancelled
     * - ACTION_UP: when finger is lifted
     * - Getting x and y position of finger
     * @param v
     * @param event
     * @return boolean
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        builder.setLength(0);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                builder.append("down, ");
                break;
            case MotionEvent.ACTION_MOVE:
                builder.append("move, ");
                break;
            case MotionEvent.ACTION_CANCEL:
                builder.append("cancel, ");
                break;
            case MotionEvent.ACTION_UP:
                builder.append("up, ");
                break;
        }

        builder.append(event.getX());
        builder.append(", ");
        builder.append(event.getY());
        String text = builder.toString();
        Log.d("TouchTest", text);
        textView.setText(text);
        return true;
    }
}
