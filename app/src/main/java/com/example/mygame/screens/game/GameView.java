package com.example.mygame.screens.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.mygame.R;
import com.example.mygame.screens.DataBaseHandler;
import com.example.mygame.screens.SecondActivity;

import java.util.ArrayList;
import java.util.Date;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying;
    private int score;
    private SharedPreferences prefs;
    private SoundPool soundPool;

    private DataBaseHandler dbh;
    private int screenX, screenY, screenWidth, screenHeight;
    private Background background1;
    private Player player;
    private ArrayList<Crystal> crystals;
    private Paint paint;
    private MediaPlayer mediaPlayer;
    SecondActivity activity;
    private long startTime;
    private int sound;

    public GameView(SecondActivity activity, int screenWidth, int screenHeight) {
        super(activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else
        {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        sound = soundPool.load(activity, R.raw.coin, 1);

        this.activity = activity;
        startTime = System.currentTimeMillis();
        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);
        this.dbh = new DataBaseHandler(activity);
        isPlaying = true;
        score = 0;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        background1 = new Background(screenWidth, screenHeight, getResources());
        player = new Player(screenWidth, screenHeight, getResources());
        crystals = new ArrayList<>();

        for (int i = 0; i < 5; i++)
        {
            crystals.add(new Crystal(screenWidth, screenHeight, getResources()));
        }

        player.setX(screenX / 2);
        player.setY(screenY / 2);

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(activity, R.raw.spacesong);
        }
        mediaPlayer.start();

        Toast.makeText(activity, "Collect all the crystals!", Toast.LENGTH_SHORT).show();
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

        Crystal temp = null;
        for (Crystal crystal: crystals)
        {
            if (crystal.update(player.GetX(), player.GetY()) == -1)
            {
                temp = crystal;
            }
        }

        if (temp != null)
        {
            soundPool.play(sound, 1, 1, 0, 0, 1);
            score++;
            SharedPreferences.Editor editor = prefs.edit();
            editor.apply();
            crystals.remove(temp);
        }

        if (crystals.isEmpty())
        {
            float finalTime = (new Date().getTime() - startTime) / 1000f;
            Intent intent = new Intent(activity, SecondActivity.class);
            intent.putExtra("GameFinished", "true");
            intent.putExtra("FinalTime", finalTime);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);

            for (Crystal crystal: crystals)
            {
                canvas.drawBitmap(crystal.sprite, crystal.GetX(), crystal.GetY(), paint);
            }

            canvas.drawBitmap(player.sprite, player.GetX(), player.GetY(), paint);

            canvas.drawText((new Date().getTime() - startTime) / 1000f + "", screenWidth / 2f, screenHeight * .1f, paint);

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
