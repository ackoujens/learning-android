package be.ackoujens.androidtesting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * LifeCycle
 * testing android application states
 */
public class LifeCycle extends Activity {
    StringBuilder builder = new StringBuilder();
    TextView textView;

    /**
     * Log text to the textView
     * @param text
     */
    private void log(String text) {
        Log.d("LifeCycle", text);
        builder.append(text);
        builder.append("\n");
        textView.setText(builder.toString());
    }

    /**
     * Activity created
     * - when activity is started for first time
     * - setting up UI components
     * - hook into input system
     * - called once in life cycle
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText(builder.toString());
        setContentView(textView);
        log("created");
    }

    /**
     * Activity restarted
     * - called when activity is resumed from stopped state
     * - preceded by a call to onStop()
     */
    @Override
    public void onRestart() {
        super.onRestart();
        log("restarted");
    }

    /**
     * Activity resumed
     * - called after onStart
     * - when activity is resumed from a paused state
     * ex: screen unlocked
     */
    @Override
    public void onResume() {
        super.onResume();
        log("resumed");
    }

    /**
     * Activity paused
     * - called when activity enters paused state
     * - might be last notification -> android might want to kill app silently
     * - we should save our states here
     */
    @Override
    public void onPause() {
        super.onPause();
        log("paused");

        /**
         * Activity finished
         */
        if (isFinishing()) {
            log("finishing");
        }
    }

    /**
     * Activity stopped
     * - called when activity enters stopped state
     * - preceded by a call to onPause()
     * - onPause() will always be called before onStop()
     * ! this method might never be calledif activity was silently destroyed after onPause()
     */
    @Override
    public void onStop() {
        super.onStop();
        log("stopped");
    }

    /**
     * Activity destroyed
     * - called at end of activity life cycle
     * - last time we can persist any information
     * ! this method might never be called if activity was silently destroyed after onPause() or onStop() !
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        log("destroyed");
    }
}
