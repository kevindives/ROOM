package com.magicworld.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val firstName : String,
    val lastName : String,
    val age : Int
        ):Serializable