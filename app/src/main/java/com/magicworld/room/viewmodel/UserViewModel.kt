package com.magicworld.room.viewmodel

import androidx.lifecycle.*
import com.magicworld.room.model.User
import com.magicworld.room.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    private val userRepository = UserRepository()
    val readAllData : LiveData<List<User>> = userRepository.readAllData

    private var getDataFromFirebase: MutableLiveData<ArrayList<User>> = MutableLiveData()
    val onGetDataFromFirebase: LiveData<ArrayList<User>> = getDataFromFirebase

    private var checkUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val onCheckUserLooggedIn: LiveData<Boolean> = checkUserLoggedIn

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

    fun checkUserLoggedIn() {
        viewModelScope.launch(Dispatchers.IO) {
            checkUserLoggedIn.postValue(userRepository.checkUserLoggedIn())
        }
    }
    fun getDataFromFirebase() {
        viewModelScope.launch(Dispatchers.IO) {
            getDataFromFirebase.postValue(userRepository.getDataFromFirebase())
        }
    }

}