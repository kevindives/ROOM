package com.magicworld.room.repository

import androidx.lifecycle.LiveData
import com.magicworld.room.ROOM
import com.magicworld.room.data.UserDao
import com.magicworld.room.model.User

class UserRepository{

    private val userDao: UserDao = ROOM.database.userDao()

    val readAllData:LiveData<List<User>> = userDao.reaAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(updateUser: User) {
        userDao.updateUser(updateUser)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }
}