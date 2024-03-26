package com.winterry.nbcsignin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {

    private var _nameString = MutableLiveData<String>()
    val nameString: LiveData<String> get() = _nameString

    private var _idString = MutableLiveData<String>()
    val idString: LiveData<String> get() = _idString

    private var _passwordString = MutableLiveData<String>()
    val passwordString: LiveData<String> get() = _passwordString

    fun registerAuth(nameStr: String, idStr: String, passwordStr: String): Boolean {
        _nameString.value = nameStr
        _idString.value = idStr
        _passwordString.value = passwordStr

        return true
    }

    fun getAuthString(): String {
        return "이름: ${_nameString.value}, 아이디: ${_idString.value}\n비밀번호: ${_passwordString.value}"
    }
}