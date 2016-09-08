package be.ackoujens.androidtesting;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Accelerometer
 * handle accelerometer values (x, y, z)
 */
public class Accelerometer extends Activity implements SensorEventListener {
    StringBuilder builder = new StringBuilder();
    TextView      textView;
    SensorManager manager;
    boolean       useSensorData;
    String        sensorQuality;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);

        // Sensor setup
        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        useSensorData = true;
        sensorQuality = "High Accuracy";

        // Check sensor state
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
            textView.setText("No accelerometer installed");
        } else {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            if (!manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)) {
                textView.setText("Couldn't register sensor listener");
            }
        }
    }

    /**
     * Get sensor values and set to textView
     * - print all debug data here
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        builder.setLength(0);
        builder.append("x: ");
        builder.append(event.values[0]);
        builder.append("y: ");
        builder.append(event.values[1]);
        builder.append("z: ");
        builder.append(event.values[2]);
        builder.append("\n");
        builder.append("useSensorData: ");
        builder.append(useSensorData);
        builder.append("\n");
        builder.append("sensorQuality: ");
        builder.append(sensorQuality);
        textView.setText(builder.toString());
    }

    /**
     * Triggers when sensor accuracy changes
     * - we can choose to use only high quality data by using a global boolean
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor == manager.getDefaultSensor(0)) {
            switch (accuracy) {
                case 0:
                    sensorQuality = "Unreliable";
                    useSensorData = false;
                    break;
                case 1:
                    sensorQuality = "Low Accuracy";
                    useSensorData = false;
                    break;
                case 2:
                    sensorQuality = "Medium Accuracy";
                    useSensorData = false;
                    break;
                case 3:
                    sensorQuality = "High Accuracy";
                    useSensorData = true;
                    break;
            }
        }
    }
}
