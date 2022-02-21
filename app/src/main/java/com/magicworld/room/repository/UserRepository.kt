package com.magicworld.room.repository

import com.magicworld.room.ROOM
import com.magicworld.room.data.UserDao
import com.magicworld.room.model.User

class UserRepository{

    private val userDao: UserDao = ROOM.database.userDao()

    suspend fun addUser(user: User){

        userDao.addUser(user)
    }

    fun readAllData():MutableList<User> {

        return userDao.reaAllData()
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