package com.android.example.beerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.beerlist.ui.main.MainViewModel

class MainViewModelFactory(
private val beerProvider: PunkBeerProvider ):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(beerProvider) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
        return super.create(modelClass)
    }
}