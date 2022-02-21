package com.magicworld.room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.room.model.User
import com.magicworld.room.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    private val userRepository = UserRepository()

    private var userLoad: MutableLiveData<MutableList<User>> = MutableLiveData()
    val onUserLoaded : LiveData<MutableList<User>> = userLoad

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }
    fun readAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            userLoad.postValue(userRepository.readAllData())
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