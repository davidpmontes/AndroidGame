package com.example.mygame.screens

import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewDebug
import android.view.WindowManager
import android.widget.Toast
import com.example.mygame.R
import com.example.mygame.screens.game.GameView

class SecondActivity : AppCompatActivity() {

    lateinit var gameView : GameView
    lateinit var point: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_second)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        point = Point()
        windowManager.defaultDisplay.getSize(point)
        gameView = GameView(this, point.x, point.y)

        intent = getIntent()
        Toast.makeText(applicationContext, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show()

        setContentView(gameView)
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }
}
