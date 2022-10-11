package com.ivasco.cards.ui.di

import com.ivasco.cards.ui.fragment.carddetails.CardDetailsViewModel
import com.ivasco.cards.ui.fragment.cardlist.CardListViewModel
import com.ivasco.cards.ui.fragment.categorylist.CategoryListViewModel
import com.ivasco.cards.ui.fragment.entry.EntryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { EntryViewModel() }
    viewModel { CategoryListViewModel(get()) }
    viewModel { CardListViewModel(get()) }
    viewModel { CardDetailsViewModel(get()) }
}