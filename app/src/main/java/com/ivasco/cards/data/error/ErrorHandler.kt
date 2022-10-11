package com.ivasco.cards.data.error

interface ErrorHandler {
    fun getError(throwable: Throwable): Error
}