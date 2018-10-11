package com.example.alessandro.theflyingfish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class FlyingFishView extends View {

    private Bitmap fish[] = new Bitmap[2] ;
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;

    private int canvasWidth;
    private int canvasHeight;

    private int yellowX;
    private int yellowY;
    private int yellowSpeed = 16;
    private Paint yellowPaint = new Paint();

    private int greenX;
    private int greenY;
    private int greenSpeed = 20;
    private Paint greenPaint = new Paint();

    private int redX;
    private int redY;
    private int redSpeed = 25;
    private Paint redPaint = new Paint();

    private int score;

    private boolean touch = false;

    private Bitmap backgroundImg;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    public FlyingFishView(Context context) {

        super(context);
        // Fish Image
        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

        // Background Image
        backgroundImg = BitmapFactory.decodeResource(getResources(),R.drawable.background);

        // Yellow ball
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        // Green ball
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        // Red ball
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        // Score Text
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        // Life Images
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        // Fish Position on Start
        fishY = 550;

        // Starting Score
        score = 0;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImg,0,0,null);

        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;

        if (fishY < minFishY){

            fishY = minFishY;

        }

        if (fishY > maxFishY){

            fishY = maxFishY;

        }

        fishSpeed = fishSpeed + 2;

        if (touch){

            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;

        }else{

            canvas.drawBitmap(fish[0], fishX, fishY,null);

        }

        // Yellow Ball Stats
        yellowX = yellowX - yellowSpeed;

        if (hitBallChecker(yellowX, yellowY)){

            // increase score
            score = score + 10;
            yellowX = - 100;

        }

        if (yellowX < 0){

            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;

        }

        // Green Ball Stats
        greenX = greenX - greenSpeed;

        if (hitBallChecker(greenX, greenY)){

            // increase score
            score = score + 20;
            greenX = - 100;

        }

        if (greenX < 0){

            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;

        }

        // Red Ball Stats
        redX = redX - redSpeed;

        if (hitBallChecker(redX, redY)){

            redX = - 100;

            // TODO add a method to decrease 1 Life

            // TODO add a method to End the Game if player loose 3 Life

        }

        if (redX < 0){

            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;

        }


        // Draw Yellow Ball
        canvas.drawCircle(yellowX, yellowY, 25, yellowPaint);

        // Draw Green Ball
        canvas.drawCircle(greenX, greenY, 25, greenPaint);

        // Draw Red Ball
        canvas.drawCircle(redX, redY, 30, redPaint);

        // Draw Score
        canvas.drawText(getContext().getString(R.string.score_label) + score, 20,60,scorePaint);

        // Draw Life
        canvas.drawBitmap(life[0],580, 10,null);
        canvas.drawBitmap(life[0],680, 10,null);
        canvas.drawBitmap(life[0],780, 10,null);

    }

    // Method to catch Balls
    public boolean hitBallChecker(int x, int y){

        if (fishX < x
                && x < (fishX + fish[0].getWidth())
                && fishY < y
                && y < (fishY + fish[0].getHeight())){

            return true;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){

            touch = true;
            fishSpeed = -22;

        }

        return true;

    }
}
