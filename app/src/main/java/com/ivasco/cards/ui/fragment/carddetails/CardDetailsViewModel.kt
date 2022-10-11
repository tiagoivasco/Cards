package com.ivasco.cards.ui.fragment.carddetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivasco.cards.data.service.CardService

sealed class UiState {
    object Success : UiState()
    data class Error(val messageResource: Int) : UiState()
}

class CardDetailsViewModel(
    private val service: CardService
) : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState>
        get() = _state
}