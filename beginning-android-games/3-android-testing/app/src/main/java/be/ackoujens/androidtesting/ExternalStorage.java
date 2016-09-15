package be.ackoujens.androidtesting;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * External Storage
 * from SD cards to USB drives
 */
public class ExternalStorage extends Activity {
    TextView textView;

    /**
     * Triggered on creation of activity
     * - create and set textview
     * - check for the presence of SD card
     * - if present, get the directory
     * - set path for new file
     * - write a simple text file
     * - read the text file
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);
        if (isStoragePermissionGranted()) {
            ExternalStorageTest();
        }
    }

    /**
     * Write a text file
     * - setup buffered writer according to the file writer
     * - write some text to the file
     * - close file
     * @param file
     * @param text
     * @throws IOException
     */
    private void writeTextFile(File file, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.close();
    }

    /**
     * Read a text file
     * - setup buffered reader according to the file reader
     * - use StringBuilder for the end result
     * - use line String variable to input 1 line at a time into the builder
     * - close the reader
     * - return the end result string
     * @param file
     * @return
     * @throws IOException
     */
    private String readTextFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            text.append(line);
            text.append("\n");
        }
        reader.close();
        return text.toString();
    }

    private void ExternalStorageTest() {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            textView.setText("No external storage mounted");
        } else {
            File externalDir = Environment.getExternalStorageDirectory();
            File textFile = new File(externalDir.getAbsolutePath() + File.separator + "text.txt");
            try {
                writeTextFile(textFile, "This is a test. Written to external storage.");
                String text = readTextFile(textFile);
                textView.setText(text);
                if (!textFile.delete()) {
                    textView.setText("Couldn't remove temporary file.");
                }
            } catch (IOException e) {
                textView.setText("Something went wrong! " + e.getMessage());
            }
        }
    }

    /**
     * isStoragePermissionGranted
     * - checks for storage permission and asks user if it needs to be granted
     * Note: needed since Marshmellow
     * @return success or failure
     */
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
                return true;
            } else {

                Toast.makeText(this, "Permission is revoked", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
