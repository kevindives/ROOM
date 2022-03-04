package com.magicworld.room.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.magicworld.room.ROOM
import com.magicworld.room.data.UserDao
import com.magicworld.room.model.User
import kotlinx.coroutines.tasks.await

class UserRepository{

    private val userDao: UserDao = ROOM.database.userDao()
    private lateinit var auth : FirebaseAuth

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

    fun checkUserLoggedIn():Boolean {
        auth= Firebase.auth
        val currentUser =auth.currentUser
        return currentUser != null
    }

    suspend fun getDataFromFirebase():ArrayList<User> {
        val db = Firebase.firestore
        val result = db.collection("user").get().await()
        val listUser: ArrayList<User> = arrayListOf()
        for (user in result)
            listUser.add(user.toObject())

        return listUser
    }
}