package com.example.cryptoapp.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apiutilities {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}