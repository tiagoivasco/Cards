package com.ivasco.cards.ui.fragment.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivasco.cards.data.model.Result
import com.ivasco.cards.data.service.CardService
import com.ivasco.cards.ui.extensions.getMessageResource
import kotlinx.coroutines.launch

sealed class UiState {
    data class Success(val categories: List<String>) : UiState()
    data class Error(val messageResource: Int) : UiState()
    object Loading : UiState()
}

class CategoryListViewModel(
    private val service: CardService
) : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState>
        get() = _state

    fun fetchCategories() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            when (val result = service.getCategories()) {
                is Result.Success -> _state.value = UiState.Success(result.data)
                is Result.Fail -> _state.value = UiState.Error(result.error.getMessageResource())
            }
        }
    }
}