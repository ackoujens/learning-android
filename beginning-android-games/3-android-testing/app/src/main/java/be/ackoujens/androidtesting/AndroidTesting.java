package be.ackoujens.androidtesting;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * AndroidTesting
 * an extensive list of small android features
 */
public class AndroidTesting extends ListActivity {
    /**
     * Listing of all test sub-applications
     */
    String tests[] = { "LifeCycle",
                       "SingleTouch",
                       "MultiTouch",
                       "Key",
                       "Accelerometer",
                       "Assets",
                       "ExternalStorage",
                       "SharedPreference",
                       "SoundPool",
                       "MediaPlayer",
                       "FullScreen",
                       "RenderView",
                       "Shape",
                       "Bitmap",
                       "Font",
                       "SurfaceView" };

    /**
     * Init application and populate it with tests
     * - Specify list items we want ListActivity to display
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, tests));
    }

    /**
     * When ListItem is clicked
     * - get item name from tests array
     * - target class to be executed
     * - create new intent based on target
     * - start activity
     * @param list
     * @param view
     * @param position
     * @param id
     */
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        String testName = tests[position];
        try {
            Class clazz = Class.forName("be.ackoujens.androidtesting." +testName);
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
