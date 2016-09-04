// Standard Java package declaration
package be.ackoujens.helloworldactivity;

// Required imports for use below
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Extend base class with Activity
 * - Activity is provided by the Android framework
 * - Activity is like a window in the classic desktop UI's
 * - An Activity always fills the complete screen
 */
public class MainActivity extends Activity
                          implements View.OnClickListener {
    Button button;
    int touchCount;

    /**
     * Every Activity subclass must implement the abstract method Activity.onCreate()
     * - Gets called once by the Android system when the Activity is first started (replaces a constructor)
     * - Mandatory to call onCreate as the first statement in the method body
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create button and set it's initial text
        // (UI widgets are called "views" on Android
        button = new Button(this);
        button.setText("Touch Me !");

        // Sets the OnClickListener of the button
        // - callback interface with a single method
        // - gets called when button is clicked
        button.setOnClickListener(this);

        // Set the button as the content view of our Activity
        // - views can be nested
        // - content view = root of this hierarchy
        setContentView(button);
    }

    /**
     * Implementation of the OnClickListener
     * - gets called when button is clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        // increase touchCount counter and apply new button text
        touchCount++;
        button.setText("Touched me " + touchCount + " time(s)");
    }
}
