package com.winterry.nbcsignin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.winterry.nbcsignin.domain.EmailError
import com.winterry.nbcsignin.domain.IdError
import com.winterry.nbcsignin.domain.NameError
import com.winterry.nbcsignin.domain.NetworkError
import com.winterry.nbcsignin.domain.PasswordError
import com.winterry.nbcsignin.domain.Result
import com.winterry.nbcsignin.domain.User
import com.winterry.nbcsignin.domain.UserDataValidator
import com.winterry.nbcsignin.presentation.model.UserEvent
import com.winterry.nbcsignin.presentation.util.asText

class SignUpViewModel(
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    private var user = User()

    private var curName = ""
    private var curId = ""
    private var curPassword = ""

    private val _userEvent = MutableLiveData<UserEvent>(UserEvent.None())
    val userEvent: LiveData<UserEvent> get() = _userEvent

    private val _nameValid = MutableLiveData<UserEvent>(UserEvent.Error(NameError.EXIST_BLANK.asText()))
    val nameValid: LiveData<UserEvent> get() = _nameValid

    private val _emailValid = MutableLiveData<UserEvent>(UserEvent.Error(EmailError.EXIST_BLANK.asText()))
    val emailValid: LiveData<UserEvent> get() = _emailValid

    private val _idValid = MutableLiveData<UserEvent>(UserEvent.Error(IdError.EXIST_BLANK.asText()))
    val idValid: LiveData<UserEvent> get() = _idValid

    private val _passwordValid = MutableLiveData<UserEvent>(UserEvent.None(PasswordError.EXIST_BLANK.asText()))
    val passwordValid: LiveData<UserEvent> get() = _passwordValid

    private val _passwordConfirmValid = MutableLiveData<UserEvent>(UserEvent.Success)
    val passwordConfirmValid: LiveData<UserEvent> get() = _passwordConfirmValid

    private val _allInfoValid = MutableLiveData(false)
    val allInfoValid: LiveData<Boolean> get() = _allInfoValid

    fun validateName(nameStr: String) {
        _nameValid.value = when (val result = userDataValidator.validateName(nameStr)) {
            is Result.Error -> UserEvent.Error(result.error.asText())
            is Result.Success -> {
                curName = nameStr
                UserEvent.Success
            }
        }
        _allInfoValid.value = checkAllValidation()
    }

    fun validateEmail(emailStr: String) {
        _emailValid.value = when (val result = userDataValidator.validateEmail(emailStr)) {
            is Result.Error -> UserEvent.Error(result.error.asText())
            is Result.Success -> UserEvent.Success
        }
        _allInfoValid.value = checkAllValidation()
    }

    fun validateId(idStr: String) {
        _idValid.value = when (val result = userDataValidator.validateId(idStr)) {
            is Result.Error -> UserEvent.Error(result.error.asText())
            is Result.Success -> {
                curId = idStr
                UserEvent.Success
            }
        }
        _allInfoValid.value = checkAllValidation()
    }

    fun validatePassword(passwordStr: String) {
        _passwordValid.value = when (val result = userDataValidator.validatePassword(passwordStr)) {
            is Result.Error -> {
                when (result.error) {
                    PasswordError.EXIST_BLANK -> UserEvent.None(result.error.asText())
                    else -> UserEvent.Error(result.error.asText())
                }
            }

            is Result.Success -> {
                curPassword = passwordStr
                UserEvent.Success
            }
        }
        _allInfoValid.value = checkAllValidation()
    }

    fun validatePasswordConfirm(passwordStr: String, passwordConfirmStr: String) {
        _passwordConfirmValid.value = when (val result =
            userDataValidator.validatePasswordConfirm(passwordStr, passwordConfirmStr)) {
            is Result.Error -> UserEvent.Error(result.error.asText())
            is Result.Success -> UserEvent.Success
        }
        _allInfoValid.value = checkAllValidation()
    }

    fun registerAuth() {
        _userEvent.value = if (_allInfoValid.value!!) {
            registerUserToServer(curName, curId, curPassword)
            UserEvent.RegisterSuccess(getUserFromServer())
        } else {
            UserEvent.Error(NetworkError.FAILURE.asText())
        }
    }

    private fun checkAllValidation(): Boolean {
        return _nameValid.value is UserEvent.Success &&
                _emailValid.value is UserEvent.Success &&
                _idValid.value is UserEvent.Success &&
                _passwordValid.value is UserEvent.Success &&
                _passwordConfirmValid.value is UserEvent.Success
    }

    private fun registerUserToServer(nameStr: String, idStr: String, passwordStr: String) {
        user = User(
            name = nameStr,
            id = idStr,
            password = passwordStr
        )
    }

    private fun getUserFromServer(): User {
        return user
    }


}