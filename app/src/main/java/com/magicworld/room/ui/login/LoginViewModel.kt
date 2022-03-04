package com.magicworld.room.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.room.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginRepository = LoginRepository()

    private var userLogin: MutableLiveData<String> = MutableLiveData()
    val onUserLoggedIn: LiveData<String> = userLogin

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userLogin.postValue(loginRepository.login(email, password))
        }
    }

}