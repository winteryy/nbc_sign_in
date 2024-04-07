package com.winterry.nbcsignin.presentation.util

import com.winterry.nbcsignin.domain.EmailError
import com.winterry.nbcsignin.domain.IdError
import com.winterry.nbcsignin.domain.NameError
import com.winterry.nbcsignin.domain.NetworkError
import com.winterry.nbcsignin.domain.PasswordConfirmError
import com.winterry.nbcsignin.domain.PasswordError
import com.winterry.nbcsignin.domain.User

fun NetworkError.asText(): String {
    return when(this) {
        NetworkError.FAILURE -> "네트워크 에러"
    }
}

fun NameError.asText(): String {
    return when(this) {
        NameError.EXIST_BLANK -> "이름을 입력해주세요."
    }
}

fun EmailError.asText(): String {
    return when(this) {
        EmailError.EXIST_BLANK -> "이메일을 입력해주세요."
        EmailError.INVALID_EMAIL -> "잘못된 형식의 이메일입니다."
    }
}

fun IdError.asText(): String {
    return when(this) {
        IdError.EXIST_BLANK -> "아이디를 입력해주세요."
    }
}

fun PasswordError.asText(): String {
    return when(this) {
        PasswordError.EXIST_BLANK -> "숫자, 대/소문자, 특문 포함 8~16자리"
        PasswordError.INVALID_LENGTH -> "8자~16자 사이로 입력해주세요."
        PasswordError.NO_LOWERCASE -> "소문자가 1개 이상 포함되어야 합니다."
        PasswordError.NO_UPPERCASE -> "대문자가 1개 이상 포함되어야 합니다."
        PasswordError.NO_DIGIT -> "숫자가 1개 이상 포함되어야 합니다."
        PasswordError.NO_SPECIAL_SIGN -> "특수문자가 1개 이상 포함되어야 합니다."
    }
}

fun PasswordConfirmError.asText(): String {
    return when(this) {
        PasswordConfirmError.CONFIRM_UNMATCHED -> "비밀번호가 일치하지 않습니다."
    }
}

fun User.asText(): String {
    return "이름: ${this.name}, 아이디: ${this.id}\n비밀번호: ${this.password}";
}