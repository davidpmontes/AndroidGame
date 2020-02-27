package com.example.mygame.screens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mygame.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        var intent = getIntent()
        var finalTime = intent.getFloatExtra("FinalTime", -1f)
        var db = DataBaseHandler(applicationContext)

        if (finalTime > 0)
        {
            Toast.makeText(this, "WooHoo!  Your time was " + finalTime + " seconds!", Toast.LENGTH_LONG).show()
                var newGameScore = GameScores(finalTime.toString())
                db.insertData(newGameScore)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
