package com.android.example.beerlist

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PunkBeerProvider {
        private val logging = HttpLoggingInterceptor()
        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        private  val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.punkapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val beerService = retrofit.create(BeersInterface::class.java)

        suspend fun getBeers()=beerService.getBeers()
    }
