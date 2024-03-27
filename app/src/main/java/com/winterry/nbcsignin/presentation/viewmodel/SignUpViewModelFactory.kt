package com.winterry.nbcsignin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.winterry.nbcsignin.domain.UserDataValidator

class SignUpViewModelFactory(private val userDataValidator: UserDataValidator): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(userDataValidator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}