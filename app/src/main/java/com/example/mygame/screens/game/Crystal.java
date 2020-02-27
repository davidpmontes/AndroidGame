package com.example.mygame.screens.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.mygame.R;

public class Crystal {
    private float x;
    private float y;

    Bitmap sprite;

    Crystal(int screenWidth, int screenHeight, Resources res)
    {
        x = (float)(50 + Math.random() * screenWidth * .9f);
        y = (float)(50 + Math.random() * screenHeight * .9f);
        sprite = BitmapFactory.decodeResource(res, R.drawable.crystal);
        sprite = Bitmap.createScaledBitmap(sprite, 150, 150, false);
    }

    public int update(float playerX, float playerY)
    {
        float xCenter = x;
        float yCenter = y;

        double distance =  Math.sqrt((playerY - yCenter) * (playerY - yCenter) + (playerX - xCenter) * (playerX - xCenter));
        if (distance < 100)
        {
            return -1;
        }
        return 0;
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
}
