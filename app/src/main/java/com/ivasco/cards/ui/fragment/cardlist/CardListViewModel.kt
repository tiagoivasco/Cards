package com.ivasco.cards.ui.fragment.cardlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivasco.cards.data.model.Card
import com.ivasco.cards.data.model.Result
import com.ivasco.cards.data.service.CardService
import com.ivasco.cards.ui.extensions.getMessageResource
import kotlinx.coroutines.launch

sealed class UiState {
    data class Success(val cards: List<Card>) : UiState()
    data class Error(val messageResource: Int) : UiState()
    object Loading : UiState()
}

class CardListViewModel(
    private val service: CardService
) : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState>
        get() = _state

    fun fetchCardsByCategory(category: String) {
        _state.value = UiState.Loading
        viewModelScope.launch {
            when (val result = service.getCardsByClass(category)) {
                is Result.Success -> _state.value = UiState.Success(result.data)
                is Result.Fail -> _state.value = UiState.Error(result.error.getMessageResource())
            }
        }
    }
}