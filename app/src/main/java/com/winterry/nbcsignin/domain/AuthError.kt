package com.winterry.nbcsignin.domain

enum class NetworkError: Error {
    FAILURE
}

enum class NameError: Error {
    EXIST_BLANK,
}

enum class EmailError: Error {
    EXIST_BLANK,
    INVALID_EMAIL,
}

enum class IdError: Error {
    EXIST_BLANK,
}

enum class PasswordError: Error {
    EXIST_BLANK,
    INVALID_LENGTH,
    NO_LOWERCASE,
    NO_UPPERCASE,
    NO_DIGIT,
    NO_SPECIAL_SIGN
}

enum class PasswordConfirmError: Error {
    CONFIRM_UNMATCHED,
}

