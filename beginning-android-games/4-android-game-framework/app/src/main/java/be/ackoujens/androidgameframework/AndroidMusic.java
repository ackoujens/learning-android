package be.ackoujens.androidgameframework;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import java.io.IOException;

/**
 * AndroidMusic
 * - we can only call start / stop / pause when MediaPlayer is prepared
 * - OnCompletionListener will inform you when MediaPlayer has stopped playing file
 */
public class AndroidMusic implements Music, OnCompletionListener {
    MediaPlayer mediaPlayer;
    boolean isPrepared = false;

    public AndroidMusic(AssetFileDescriptor assetFileDescriptor) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    /**
     * Start playing music stream
     * - synchronized: isPrepared might be set by another thread
     */
    @Override
    public void play() {
        if (mediaPlayer.isPlaying()) return;
        try {
            synchronized (this) {
                if (isPrepared) mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop playing music stream
     */
    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized (this) {
            isPrepared = false;
        }
    }

    /**
     * Pause music playback
     */
    public void pause() {
        if (mediaPlayer.isPlaying()) mediaPlayer.pause();
    }

    /**
     * Start from the beginning when music stream reaches the end of the audio file
     *
     * @param looping
     */
    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(isLooping());
    }

    /**
     * Set volume in float values
     *
     * @param volume
     */
    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    /**
     * Check if audio is playing
     *
     * @return boolean
     */
    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    /**
     * Check if audio is stopped
     * @return boolean
     */
    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    /**
     * Check if audio is looping
     * @return boolean
     */
    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    /**
     * Close any system resources related to the Music instance
     */
    @Override
    public void dispose() {
        if (mediaPlayer.isPlaying()) stop();
        mediaPlayer.release();
    }

    /**
     * Called when the end of a media source is reached during playback.
     * @param mediaPlayer the MediaPlayer that reached the end of the file
     */
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        synchronized (this) {
            isPrepared = false;
        }
    }
}
