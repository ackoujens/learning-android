package be.ackoujens.androidtesting;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Font
 * writing text with a ttf typeface
 */
public class Font extends Activity {
    class RenderView extends View {
        Paint paint;
        Typeface font;
        Rect bounds = new Rect();

        /**
         * RenderView
         * - init paint instance
         * - load font from asset file
         * @param context
         */
        public RenderView(Context context) {
            super(context);
            paint = new Paint();
            font = Typeface.createFromAsset(context.getAssets(), "font/font.ttf");
        }

        /**
         * onDraw
         * - black background
         * - setup paint options
         * - draw text in the loaded font
         * - invalidate for redraw
         * @param canvas
         */
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(0, 0, 0);
            paint.setColor(Color.YELLOW);
            paint.setTypeface(font);
            paint.setTextSize(72);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("This is a test!", canvas.getWidth()/2, canvas.getHeight()/2, paint);
            invalidate();
        }
    }

    /**
     * onCreate
     * - make application full screen
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
}
