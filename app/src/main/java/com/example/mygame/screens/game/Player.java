package com.example.mygame.screens.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.mygame.R;

public class Player {
    private final float MAX_VELOCITY = 10f;
    private final int SCREEEN_MARGIN = 110;

    private float touchStartX;
    private float touchStartY;
    private float touchMoveX;
    private float touchMoveY;

    private boolean isTouching = false;

    private float x;
    private float y;

    private int spriteWidth;
    private int spriteHeight;
    private float xVel = 0f;
    private float yVel = 0f;
    private int screenWidth;
    private int screenHeight;

    Bitmap sprite;

    Player(int screenWidth, int screenHeight, Resources res)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        spriteWidth = 100;
        spriteHeight = 100;

        sprite = BitmapFactory.decodeResource(res, R.drawable.ufo);
        sprite = Bitmap.createScaledBitmap(sprite, spriteWidth, spriteHeight, false);
    }

    public void update()
    {
        if (isTouching) {
            xVel += (touchMoveX - touchStartX) / 2000;
            yVel += (touchMoveY - touchStartY) / 2000;
        }

        xVel = Math.min(xVel, MAX_VELOCITY);
        yVel = Math.min(yVel, MAX_VELOCITY);

        xVel = Math.max(xVel, -MAX_VELOCITY);
        yVel = Math.max(yVel, -MAX_VELOCITY);

        x += xVel;
        y += yVel;

        if (x < -SCREEEN_MARGIN)
            x += screenWidth + SCREEEN_MARGIN;
        if (x > screenWidth)
            x -= screenWidth + SCREEEN_MARGIN;
        if (y < -SCREEEN_MARGIN)
            y += screenHeight + SCREEEN_MARGIN;
        if (y > screenHeight)
            y -= screenHeight + SCREEEN_MARGIN;
    }

    public void setX(int value)
    {
        this.x = value;
    }

    public void setY(int value)
    {
        this.y = value;
    }

    public float GetX()
    {
        return this.x;
    }

    public float GetY()
    {
        return this.y;
    }

    public void TouchStart(float x, float y)
    {
        touchStartX = x;
        touchStartY = y;
        touchMoveX = x;
        touchMoveY = y;
        isTouching = true;
    }

    public void TouchMove(float x, float y)
    {
        touchMoveX = x;
        touchMoveY = y;
    }

    public void TouchStop()
    {
        isTouching = false;
    }
}
