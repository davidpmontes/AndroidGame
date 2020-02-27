package com.example.mygame.screens

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "MyScores"
val COL_NAME = "myName"
val COL_SCORE = "myScore"

class DataBaseHandler(var context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_SCORE + " VARCHAR(256)" + ") "

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(gameScores: GameScores)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_SCORE, gameScores.score)

        db.insert(TABLE_NAME, null, cv)
        //if (result == -1.toLong())
        //    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        //else
        //    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readData(): MutableList<GameScores> {
        var list: MutableList<GameScores> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst())
        {
            do {
                var gameScores = GameScores()
                gameScores.score = result.getString(result.getColumnIndex(COL_SCORE))
                list.add(gameScores)
            } while (result.moveToNext())
        }
        Toast.makeText(context, "Refreshed!", Toast.LENGTH_SHORT).show()

        result.close()
        db.close()
        return list
    }

    fun deleteData() {
        var db = this.writableDatabase

        db.execSQL("DELETE FROM " + TABLE_NAME)
        db.close()
        Toast.makeText(context, "Scores deleted!", Toast.LENGTH_SHORT).show()
    }
}