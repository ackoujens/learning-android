package be.ackoujens.androidtesting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

/**
 * Key
 * processing buttons and keyboard keys
 */
public class Key extends Activity implements OnKeyListener{
    StringBuilder builder = new StringBuilder();
    TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Press keys (if you have some)!");
        textView.setOnKeyListener(this);
        textView.setFocusable(true);
        textView.requestFocus();
        setContentView(textView);
    }

    /**
     * Key handling
     * - check if key is up or down
     * - check what key was pressed (keycode & unicodechar)
     * @param v
     * @param keyCode
     * @param event
     * @return boolean
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        builder.setLength(0);
        switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:
                builder.append("down, ");
                break;
            case KeyEvent.ACTION_UP:
                builder.append("up, ");
                break;
        }
        builder.append(event.getKeyCode());
        builder.append(", ");
        builder.append((char)event.getUnicodeChar());
        String text = builder.toString();
        Log.d("KeyTest", text);
        textView.setText(text);

        // if key was not the back key, return 1
        // if key was the back key,     return 0
        return event.getKeyCode() != KeyEvent.KEYCODE_BACK;
    }
}
