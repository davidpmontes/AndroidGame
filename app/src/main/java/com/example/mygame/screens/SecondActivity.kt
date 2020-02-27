package com.example.mygame.screens

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mygame.screens.game.GameView

class SecondActivity : AppCompatActivity() {

    lateinit var gameView : GameView
    lateinit var point: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent = getIntent()
        var message = intent.getStringExtra("GameFinished")
        var finalTime = intent.getFloatExtra("FinalTime", -1f)

        if (message == "true")
        {
            val intent = Intent(this, MainActivity::class.java)
            if (finalTime > 0)
            {
                intent.putExtra("FinalTime", finalTime)
            }
            startActivity(intent)
            finish()
        }
        else
        {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            point = Point()
            windowManager.defaultDisplay.getSize(point)
            gameView = GameView(this, point.x, point.y)

            setContentView(gameView)
        }
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
