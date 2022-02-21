package com.magicworld.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.magicworld.room.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao() :UserDao

}