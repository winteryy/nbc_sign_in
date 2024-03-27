package com.winterry.nbcsignin.presentation.model

import com.winterry.nbcsignin.domain.User

sealed interface UserEvent {
    data object None: UserEvent
    data object Success: UserEvent
    data class Error(val msg: String): UserEvent

    data class RegisterSuccess(val user: User): UserEvent
}