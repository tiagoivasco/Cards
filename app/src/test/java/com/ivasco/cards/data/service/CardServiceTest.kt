package com.ivasco.cards.data.service

import com.ivasco.cards.data.endpoint.CardEndpoint
import com.ivasco.cards.data.error.ErrorHandler
import com.ivasco.cards.data.model.Result
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue

internal class CardServiceTest {
    private val errorHandler: ErrorHandler = mockk()
    private val endpoint: CardEndpoint = mockk()
    private val repository = CardService(endpoint, errorHandler)
}