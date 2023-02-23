package com.android.example.beerlist.app

import android.app.Application
import com.android.example.beerlist.network.BeerListProvider
import com.android.example.beerlist.viewmodel.MainViewModelFactory

class BeerApp:Application() {
    private  val beerListProvider= BeerListProvider()
    var mainViewModelFactory = MainViewModelFactory(beerListProvider)

}