package be.ackoujens.androidtesting;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Shared Preferences
 * storing key-value pairs for your application
 */
public class SharedPreference extends Activity {
    public static final String MyPREFERENCES = "MyPrefsFile";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StringBuilder builder = new StringBuilder();
        TextView textView = new TextView(this);
        setContentView(textView);

        SharedPreferences sharedPref = getSharedPreferences("my-preference-file", Context.MODE_PRIVATE);

        Editor editor = sharedPref.edit();
        editor.putString("key1", "String preference value");
        editor.putInt("key2", 9090);

        editor.commit();

        String value1 = sharedPref.getString("key1", null);
        int value2 = sharedPref.getInt("key2", 0);

        builder.append("Current key/value pairs stored in Shared Preferences file ...");
        builder.append("\n");
        builder.append("key1: ");
        builder.append(value1);
        builder.append("\n");
        builder.append("key2: ");
        builder.append(value2);

        textView.setText(builder.toString());
    }

}
