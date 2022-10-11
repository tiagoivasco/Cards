package com.ivasco.cards.ui.error

import com.ivasco.cards.data.error.Error

sealed class UiError : Error {
    object PersonNotFound : UiError()
}
