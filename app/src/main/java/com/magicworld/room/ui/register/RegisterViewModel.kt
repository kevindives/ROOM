package com.magicworld.room.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.room.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val registerRepository = RegisterRepository()

    private var userRegistered: MutableLiveData<String> = MutableLiveData()
    val onUserRegistered: LiveData<String> = userRegistered

    private var createUserInDatabase: MutableLiveData<String> = MutableLiveData()
    val onCreateUserInDatabase: LiveData<String> = createUserInDatabase

    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRegistered.postValue(registerRepository.registerUser(email,password))
        }
    }

    fun createUserInDataBase(firstname: String, lastname: String, age: String) {
        viewModelScope.launch(Dispatchers.IO) {
            createUserInDatabase.postValue(registerRepository.createUserInDataBase(firstname,lastname,age))
        }
    }

}