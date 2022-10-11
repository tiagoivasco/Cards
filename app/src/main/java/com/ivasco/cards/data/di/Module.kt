package com.ivasco.cards.data.di

import com.ivasco.cards.data.config.provideMyRetrofit
import com.ivasco.cards.data.endpoint.CardEndpoint
import com.ivasco.cards.data.error.NetworkErrorHandler
import com.ivasco.cards.data.service.CardService
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single { CardService(provideCardService(get()), get<NetworkErrorHandler>()) }
    factory { NetworkErrorHandler() }
    single { provideMyRetrofit() }
}

private fun provideCardService(retrofit: Retrofit) = retrofit.create(CardEndpoint::class.java)

