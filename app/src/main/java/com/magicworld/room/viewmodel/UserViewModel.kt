package com.magicworld.room.viewmodel

import androidx.lifecycle.*
import com.magicworld.room.model.User
import com.magicworld.room.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    private val userRepository = UserRepository()
    val readAllData : LiveData<List<User>> = userRepository.readAllData


    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun updateUser(updateUser: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(updateUser)
        }
    }
    fun deleteUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteAllUser()
        }
    }

}