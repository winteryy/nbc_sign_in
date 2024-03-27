package com.winterry.nbcsignin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.winterry.nbcsignin.domain.Result
import com.winterry.nbcsignin.domain.User
import com.winterry.nbcsignin.domain.UserDataValidator
import com.winterry.nbcsignin.presentation.model.UserEvent
import com.winterry.nbcsignin.presentation.util.asText

class SignUpViewModel(
    private val userDataValidator: UserDataValidator
): ViewModel() {

//    private var _nameString = MutableLiveData<String>()
//    val nameString: LiveData<String> get() = _nameString
//
//    private var _idString = MutableLiveData<String>()
//    val idString: LiveData<String> get() = _idString
//
//    private var _passwordString = MutableLiveData<String>()
//    val passwordString: LiveData<String> get() = _passwordString

    private var user = User()

    private val _userEvent = MutableLiveData<UserEvent>(UserEvent.None)
    val userEvent: LiveData<UserEvent> get() = _userEvent

    fun registerAuth(nameStr: String, email: String, idStr: String, passwordStr: String, passwordConfirmStr: String) {
        _userEvent.value = UserEvent.None

        when(val result = userDataValidator.validateUserData(nameStr, email, idStr, passwordStr, passwordConfirmStr)) {
            is Result.Error -> {
                val errorMessage = result.error.asText()
                _userEvent.value = UserEvent.Error(errorMessage)
            }
            is Result.Success -> {
//                _nameString.value = nameStr
//                _idString.value = idStr
//                _passwordString.value = passwordStr
                // 원래는 서버에 등록
                registerUser(nameStr, idStr, passwordStr)
                _userEvent.value = UserEvent.RegisterSuccess(getAuth())
            }
        }
    }

    private fun registerUser(nameStr: String, idStr: String, passwordStr: String){
        user = User(
            name = nameStr,
            id = idStr,
            password = passwordStr
        )
    }
    private fun getAuth(): User {
//        return "이름: ${_nameString.value}, 아이디: ${_idString.value}\n비밀번호: ${_passwordString.value}"
        return user
    }


}