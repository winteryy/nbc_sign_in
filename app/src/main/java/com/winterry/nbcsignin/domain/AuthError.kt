package com.winterry.nbcsignin.domain

enum class AuthError: Error {
    EXIST_BLANK,
    INVALID_EMAIL,
    CONFIRM_UNMATCHED,
    INVALID_LENGTH,
    NO_LOWERCASE,
    NO_UPPERCASE,
    NO_DIGIT,
    NO_SPECIAL_SIGN
}