package be.ackoujens.androidtesting;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.widget.TextView;

/**
 * WakeLock
 * keeping the screen awake at all times
 */
public class WakeLocks extends Activity {
    WakeLock wakeLock;

    /**
     * onCreate
     * - setup text view
     * - access power manager
     * - create new wake lock
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("This screen will never sleep.");
        setContentView(textView);

        PowerManager powerManager = (PowerManager)this.getSystemService(this.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
    }

    /**
     * OnResume
     * - if wake lock present, acquire it
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    /**
     * onPause
     * - if wake lock present, release it
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (wakeLock != null) {
            if (isFinishing()) {
                wakeLock.release();
            }
        }
    }
}
