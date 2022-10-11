package com.ivasco.cards.ui.extensions

import com.ivasco.cards.R
import com.ivasco.cards.data.error.Error
import com.ivasco.cards.data.error.NetworkError
import com.ivasco.cards.ui.error.UiError

fun Error.getMessageResource(): Int {
    return when (this) {
        is NetworkError -> when (this) {
            is NetworkError.NotFound -> R.string.not_found_error
            is NetworkError.AccessDenied -> R.string.access_denied_error
            is NetworkError.ServiceUnavailable -> R.string.service_unavailable_error
            else -> R.string.unknown_network_error
        }
        is UiError -> when (this) {
            is UiError.PersonNotFound -> R.string.person_not_found_error
        }
        else -> R.string.unknown_error
    }
}