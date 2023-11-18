package com.example.cryptoapp.apis

import com.example.cryptoapp.models.MarketModel
import retrofit2.Response
import retrofit2.http.GET

interface Apiinterface {

    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=500")
    suspend fun getMarketData() : Response<MarketModel>
}