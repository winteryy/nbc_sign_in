package com.winterry.nbcsignin.domain

import android.util.Patterns

class UserDataValidator {

    fun validateName(name: String): Result<Unit, NameError> {
        if(name.isBlank()) {
            return Result.Error(NameError.EXIST_BLANK)
        }
        return Result.Success(Unit)
    }

    fun validateEmail(email: String): Result<Unit, EmailError> {
        if(email.isBlank()) {
            return Result.Error(EmailError.EXIST_BLANK)
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.Error(EmailError.INVALID_EMAIL)
        }
        return Result.Success(Unit)
    }

    fun validateId(id: String): Result<Unit, IdError> {
        if(id.isBlank()) {
            return Result.Error(IdError.EXIST_BLANK)
        }
        return Result.Success(Unit)
    }

    fun validatePassword(password: String): Result<Unit, PasswordError> {
        if(password.isBlank()) {
            return Result.Error(PasswordError.EXIST_BLANK)
        }

        if(password.length !in 8..16) {
            return Result.Error(PasswordError.INVALID_LENGTH)
        }

        val hasDigit = password.any { it.isDigit() }
        if(!hasDigit) {
            return Result.Error(PasswordError.NO_DIGIT)
        }

        val hasLowerCaseChar = password.any { it.isLowerCase() }
        if(!hasLowerCaseChar) {
            return Result.Error(PasswordError.NO_LOWERCASE)
        }

        val hasUppercaseChar = password.any { it.isUpperCase() }
        if(!hasUppercaseChar) {
            return Result.Error(PasswordError.NO_UPPERCASE)
        }

        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        if(!hasSpecialChar) {
            return Result.Error(PasswordError.NO_SPECIAL_SIGN)
        }

        return Result.Success(Unit)
    }

    fun validatePasswordConfirm(password: String, passwordConfirm: String): Result<Unit, PasswordConfirmError> {
        if(password!=passwordConfirm) {
            return Result.Error(PasswordConfirmError.CONFIRM_UNMATCHED)
        }
        return Result.Success(Unit)
    }
}