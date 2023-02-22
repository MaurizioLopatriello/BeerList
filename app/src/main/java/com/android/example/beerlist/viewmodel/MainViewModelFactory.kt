package com.android.example.beerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.beerlist.network.BeerListProvider

class MainViewModelFactory(
    private val beerListProvider: BeerListProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(beerListProvider) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}