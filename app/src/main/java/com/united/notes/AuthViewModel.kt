package com.united.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.united.notes.models.UserRequest
import com.united.notes.models.UserResponse
import com.united.notes.repository.UserRepository
import com.united.notes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
    get() = userRepository.userResponseLiveData

    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.registerUser(userRequest)
        }
    }

    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }
}