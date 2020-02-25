package com.example.mygame.screens

class Aircraft
{
    var guid : String = ""
    var latitude : String = ""
    var altitude : String = ""
    var longitude : String = ""

    constructor (guid:String, latitude:String, longitude:String, altitude:String)
    {
        this.guid = guid
        this.latitude = latitude
        this.longitude = longitude
        this.altitude = altitude
    }
}