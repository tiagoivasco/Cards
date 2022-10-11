package com.ivasco.cards.data.endpoint

import com.ivasco.cards.data.model.*
import io.reactivex.Single
import retrofit2.http.*

interface CardEndpoint {
    @GET("info")
    @Headers(
        "x-rapidapi-host:omgvamp-hearthstone-v1.p.rapidapi.com",
        "x-rapidapi-key:da3f0c9d65msh477e32c5d75d05bp192c28jsn4bcd4af357dc"
    )
    suspend fun getAllCategories(): Return


    @GET("cards/classes/{text}")
    @Headers(
        "x-rapidapi-host:omgvamp-hearthstone-v1.p.rapidapi.com",
        "x-rapidapi-key:da3f0c9d65msh477e32c5d75d05bp192c28jsn4bcd4af357dc"
    )
    suspend fun getCardsByClass(@Path("text") text: String): List<Card>
}