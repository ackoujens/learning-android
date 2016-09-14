package be.ackoujens.androidgameframework;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

/**
 * AndroidAudio
 */
public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    /**
     * AndroidAudio
     * - receives activity argument for volume control stream
     * - gets the assets path
     * - creates and configure new sound pool
     */
    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    /**
     * Takes a filename and returns a Music instance
     * - throws IOException if file loading fails
     * @param filename
     * @return Music
     */
    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetFileDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    /**
     * Takes a filename and returns a Sound instance
     * - throws IOException if file loading fails
     * @param filename
     * @return Sound
     */
    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetFileDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}
