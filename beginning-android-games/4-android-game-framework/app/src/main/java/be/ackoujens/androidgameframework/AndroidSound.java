package be.ackoujens.androidgameframework;

import android.media.SoundPool;

/**
 * AndroidSound
 */
public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    /**
     * Play audio file in the specified volume
     * @param volume
     */
    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    /**
     * Close any system resources related to the Sound instance
     */
    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
