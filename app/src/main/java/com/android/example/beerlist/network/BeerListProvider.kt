package com.android.example.beerlist.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BeerListProvider {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val beerListService: BeersInterface =
        retrofit.create(BeersInterface::class.java)

    suspend fun getBeers() = beerListService.getBeers()
}