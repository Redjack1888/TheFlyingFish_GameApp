package com.example.alessandro.theflyingfish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class FlyingFishView extends View {

    private Bitmap fish;
    private Bitmap backgroundImg;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    public FlyingFishView(Context context) {

        super(context);
        // Fish Image
        fish = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);

        // Background Image
        backgroundImg = BitmapFactory.decodeResource(getResources(),R.drawable.background);

        // Score Text
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        // Life Images
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        canvas.drawBitmap(backgroundImg,0,0,null);

        canvas.drawBitmap(fish,0,0,null);

        canvas.drawText(getContext().getString(R.string.score_label), 20,60,scorePaint);

        canvas.drawBitmap(life[0],580, 10,null);
        canvas.drawBitmap(life[0],680, 10,null);
        canvas.drawBitmap(life[1],780, 10,null);

    }
}
