package com.winterry.nbcsignin.domain

import android.util.Patterns

class UserDataValidator {

    fun validateUserData(name: String, email: String, id: String, password: String, passwordConfirm: String): Result<Unit, AuthError> {
        if(name.isBlank() || email.isBlank() || id.isBlank() || password.isBlank() || passwordConfirm.isBlank()) {
            return Result.Error(AuthError.EXIST_BLANK)
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.Error(AuthError.INVALID_EMAIL)
        }

        val pwValidResult = validatePassword(password, passwordConfirm)
        if(pwValidResult is Result.Error) {
            return pwValidResult
        }

        return Result.Success(Unit)
    }

    private fun validatePassword(password: String, passwordConfirm: String): Result<Unit, AuthError> {
        if(password!=passwordConfirm) {
            return Result.Error(AuthError.CONFIRM_UNMATCHED)
        }

        if(password.length !in 8..16) {
            return Result.Error(AuthError.INVALID_LENGTH)
        }

        val hasDigit = password.any { it.isDigit() }
        if(!hasDigit) {
            return Result.Error(AuthError.NO_DIGIT)
        }

        val hasLowerCaseChar = password.any { it.isLowerCase() }
        if(!hasLowerCaseChar) {
            return Result.Error(AuthError.NO_LOWERCASE)
        }

        val hasUppercaseChar = password.any { it.isUpperCase() }
        if(!hasUppercaseChar) {
            return Result.Error(AuthError.NO_UPPERCASE)
        }

        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        if(!hasSpecialChar) {
            return Result.Error(AuthError.NO_SPECIAL_SIGN)
        }

        return Result.Success(Unit)
    }

}