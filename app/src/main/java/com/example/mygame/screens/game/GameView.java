package com.example.mygame.screens.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.mygame.R;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying;

    private int screenX, screenY;
    private Background background1;
    private Player player;
    private Paint paint;
    private MediaPlayer mediaPlayer;

    public GameView(Context context, int screenWidth, int screenHeight) {
        super(context);

        isPlaying = true;

        this.screenX = screenX;
        this.screenY = screenY;
        background1 = new Background(screenWidth, screenHeight, getResources());
        player = new Player(screenWidth, screenHeight, getResources());
        player.setX(screenX / 2);
        player.setY(screenY / 2);

        paint = new Paint();
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.spacesong);
        }
        mediaPlayer.start();
    }

    @Override
    public void run() {
        while(isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        player.update();
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);

            canvas.drawBitmap(player.sprite, player.GetX(), player.GetY(), paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {

    }

    public void resume() {
        thread = new Thread(this);
        thread.start();
        mediaPlayer.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            mediaPlayer.stop();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                player.TouchStart(event.getRawX(), event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                player.TouchMove(event.getRawX(), event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                player.TouchStop();
                break;
        }
        return true;
    }
}
