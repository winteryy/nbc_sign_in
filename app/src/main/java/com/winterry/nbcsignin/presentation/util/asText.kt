package com.winterry.nbcsignin.presentation.util

import com.winterry.nbcsignin.domain.AuthError
import com.winterry.nbcsignin.domain.User

//fun Result.Error<*, PasswordError>.asErrorText(): String {
//    return error.asText()
//}

fun AuthError.asText(): String {
    return when(this) {
        AuthError.EXIST_BLANK -> "모든 정보를 입력해주세요."
        AuthError.INVALID_EMAIL -> "잘못된 형식의 이메일입니다."
        AuthError.CONFIRM_UNMATCHED -> "재확인 비밀번호가 일치하지 않습니다."
        AuthError.INVALID_LENGTH -> "비밀번호는 8자~16자 사이로 입력해주세요."
        AuthError.NO_LOWERCASE -> "비밀번호에는 소문자가 1개 이상 포함되어야 합니다."
        AuthError.NO_UPPERCASE -> "비밀번호에는 대문자가 1개 이상 포함되어야 합니다."
        AuthError.NO_DIGIT -> "비밀번호에는 숫자가 1개 이상 포함되어야 합니다."
        AuthError.NO_SPECIAL_SIGN -> "비밀번호에는 특수문자가 1개 이상 포함되어야 합니다."
    }
}

fun User.asText(): String {
    return "이름: ${this.name}, 아이디: ${this.id}\n비밀번호: ${this.password}";
}