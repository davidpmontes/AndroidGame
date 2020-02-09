package com.example.mygame.screens.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mygame.R;

public class Background {
    int x = 0;
    int y = 0;
    Bitmap background;

    Background(int screenWidth, int screenHeight, Resources res)
    {
        background = BitmapFactory.decodeResource(res, R.drawable.space_background);
        background = Bitmap.createScaledBitmap(background, screenWidth, screenHeight, false);
    }
}
