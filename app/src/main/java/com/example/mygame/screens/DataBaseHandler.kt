package com.example.mygame.screens

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Aircraft"
val COL_GUID = "guid"
val COL_ALTITUDE = "altitude"
val COL_LONGITUDE = "longitude"
val COL_LATITUDE = "latitude"

class DataBaseHandler(var context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_GUID + " VARCHAR(256) PRIMARY KEY," +
                COL_ALTITUDE + " VARCHAR(256)," +
                COL_LONGITUDE + " VARCHAR(256)," +
                COL_LATITUDE + " VARCHAR(256)" + ") "

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(aircraft: Aircraft)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_GUID, aircraft.guid)
        cv.put(COL_ALTITUDE, aircraft.altitude)
        cv.put(COL_LONGITUDE, aircraft.longitude)
        cv.put(COL_LATITUDE, aircraft.latitude)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}