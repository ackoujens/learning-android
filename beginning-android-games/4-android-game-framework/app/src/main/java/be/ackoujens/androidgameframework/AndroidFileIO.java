package be.ackoujens.androidgameframework;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * AndroidFileIO
 * reading and writing data
 */
public class AndroidFileIO implements FileIO {
    Context context;
    AssetManager assets;
    String externalStoragePath;

    /**
     * AndroidFileIO
     * - storing context: context is the gateway to almost everything in Android
     * - storing asset manager: pulled from context
     * - store external storage path: retreiving and writing files on lacation
     * @param context
     */
    public AndroidFileIO(Context context) {
        this.context = context;
        this.assets = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
    }

    /**
     * Assets will be read from the applications APK file
     * @param filename
     * @return InputStream
     * @throws IOException
     */
    @Override
    public InputStream readAsset(String filename) throws IOException {
        return assets.open(filename);
    }

    /**
     * Files will be read from the SD card
     * @param filename
     * @return InputStream
     * @throws IOException
     */
    @Override
    public InputStream readFile(String filename) throws IOException {
        return new FileInputStream(externalStoragePath + filename);
    }

    /**
     * Files will br written on the SD card
     * @param filename
     * @return OutputStream
     * @throws IOException
     */
    @Override
    public OutputStream writeFile(String filename) throws IOException {
        return new FileOutputStream(externalStoragePath + filename);
    }

    /**
     * Fetch shared preferences
     * @return SharedPreferences
     */
    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
