package com.android.example.beerlist.network

import retrofit2.http.GET

interface BeersInterface {

    @GET("v2/beers")
    suspend fun getBeers(): List<PunkBeers>
}
