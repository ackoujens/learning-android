package be.ackoujens.androidtesting;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import java.io.IOException;

/**
 * SoundPool
 * playing short sound effects
 */
public class Sound extends Activity implements OnTouchListener {
    SoundPool soundPool;
    int explosionId = -1;

    /**
     * onCreate
     * - setup textview
     * - bind to correct audio stream
     * - create new SoundPool
     * - get and load soundfile
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setOnTouchListener(this);
        setContentView(textView);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);

        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd("sound/meow.ogg");
            explosionId = soundPool.load(descriptor, 1);
        } catch (IOException e) {
            textView.setText("Couldn't load sound effect from asset, " + e.getMessage());
        }
    }

    /**
     * Activity paused
     * - called when activity enters paused state
     * - unloads sound when finishing up
     */
    @Override
    public void onPause() {
        super.onPause();

        /**
         * Activity finished
         */
        if (isFinishing()) {
            soundPool.unload(explosionId);
        }
    }

    /**
     * Play sound
     * - triggered on touch
     * - if sound id is not -1, play sound
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (explosionId != -1) {
                soundPool.play(explosionId, 1, 1, 0, 0, 1);
            }
        }
        return true;
    }
}
