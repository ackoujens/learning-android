package be.ackoujens.androidtesting;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * BitmapDraw
 */
public class BitmapDraw extends Activity {
    Bitmap image565;
    Bitmap image4444;

    class RenderView extends View {
        Rect dst = new Rect();

        /**
         * RenderView
         * - fetch assets root
         * - open an image from the image folder
         * - put the loaded image into a Bitmap instance
         * - close inputstream
         * @param context
         */
        public RenderView(Context context) {
            super(context);
            try {
                AssetManager assetManager = context.getAssets();
                InputStream inputStream = assetManager.open("image/imagergb888.png");
                image565 = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                Log.d("BitmapText", "bobrgb888.png format: " + image565.getConfig());

                inputStream = assetManager.open("image/imageargb8888.png");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                image4444 = BitmapFactory.decodeStream(inputStream, null, options);
                Log.d("BitmapText", "imageargb8888.png format: " + image4444.getConfig());
                inputStream.close();
            } catch (IOException e) {
                Log.d("Log: ", "Something went wrong.");
            } finally {
                Log.d("Log: ", "Finally, this code is executed.");
            }
        }

        /**
         * onDraw
         * - black background
         * - configure destination rectangle
         * - draw bitmaps into destination rectangle
         * - invalidate for redraw
         * @param canvas
         */
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(0, 0, 0);
            dst.set(50, 50, getWidth(), getWidth());
            canvas.drawBitmap(image565,  null, dst, null);
            canvas.drawBitmap(image4444, null, dst, null);
            invalidate();
        }
    }

    /**
     * onCreate
     * - make activity full screen
     * - engage RenderView content view
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new RenderView(this));
    }

    /**
     * onPause
     * - release resources
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (image565  != null)  image565.recycle();
        if (image4444 != null) image4444.recycle();
    }
}
