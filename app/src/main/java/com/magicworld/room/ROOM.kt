package com.magicworld.room

import android.app.Application
import androidx.room.Room
import com.magicworld.room.data.UserDatabase

class ROOM: Application() {
    companion object{
        lateinit var database : UserDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            "user_db"
        ).build()
    }
}