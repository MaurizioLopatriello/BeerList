package com.android.example.beerlist

import android.app.Application
import android.util.Log

class BeerOrders : Application() {
    private val beerProvider = PunkBeerProvider()
    lateinit var beerViewModelFactory: MainViewModelFactory
    override fun onCreate() {
        super.onCreate()
        Log.d("BeerOrders", "Started")

        beerViewModelFactory = MainViewModelFactory(beerProvider)
    }
}