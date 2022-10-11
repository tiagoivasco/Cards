package com.ivasco.cards.data.service

import com.ivasco.cards.data.endpoint.CardEndpoint
import com.ivasco.cards.data.error.ErrorHandler
import com.ivasco.cards.data.model.*

class CardService(
    private val endpoint: CardEndpoint,
    private val errorHandler: ErrorHandler
) {
    suspend fun getCategories(): Result<List<String>> {
        return wrapInResult { endpoint.getAllCategories().classes }
    }

    suspend fun getCardsByClass(cardName: String): Result<List<Card>> {
        return wrapInResult { endpoint.getCardsByClass(cardName) }
    }

    private suspend fun <T> wrapInResult(block: suspend () -> T): Result<T> {
        return try {
            Result.Success(block())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Fail(errorHandler.getError(e))
        }
    }
}