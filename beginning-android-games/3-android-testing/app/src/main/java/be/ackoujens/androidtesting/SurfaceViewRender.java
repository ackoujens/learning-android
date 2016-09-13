package be.ackoujens.androidtesting;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

/**
 * SurfaceViewRender
 * rendering in a seperate thread
 */
public class SurfaceViewRender extends Activity {
    FastRenderView renderView;

    /**
     * onCreate
     * - full screen
     * - set content view to RenderView
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        renderView = new FastRenderView(this);
        setContentView(renderView);
    }

    /**
     * onResume
     * - resume RenderView
     */
    protected void onResume() {
        super.onResume();
        renderView.resume();
    }

    /**
     * onPause
     * - pause RenderView
     */
    protected void onPause() {
        super.onPause();
        renderView.pause();
    }

    class FastRenderView extends SurfaceView implements Runnable {
        Thread renderThread = null;
        SurfaceHolder holder;
        volatile boolean running = false;

        /**
         * FastRenderView
         * - get SurfaceHolder wrapper for bookkeeping
         * @param context
         */
        public FastRenderView(Context context) {
            super(context);
            holder = getHolder();
        }

        /**
         * resume
         * - toggle running bool
         * - create and start rendering thread
         */
        public void resume() {
            running = true;
            renderThread = new Thread(this);
            renderThread.start();
        }

        /**
         * run
         * - if the Surface is valid
         * - lock it
         * - render to it
         * - unlock it
         */
        public void run() {
            while (running) {
                if (!holder.getSurface().isValid()) continue;
                Canvas canvas = holder.lockCanvas();
                canvas.drawRGB(255, 0, 0);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        /**
         * pause
         * - toggle running flag
         * - join will wait for the thread to die
         * - toggling the running flag to false will eventually make the while loop
         *   inside the run method break out and terminate the application
         */
        public void pause() {
            running = false;
            while (true) {
                try {
                    renderThread.join();
                    return;
                } catch (InterruptedException e) {
                    // retry
                }
            }
        }
    }
}
