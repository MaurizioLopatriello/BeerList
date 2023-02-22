package com.android.example.beerlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.beerlist.network.BeerListProvider
import com.android.example.beerlist.network.PunkBeers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val beerListProvider: BeerListProvider) : ViewModel() {

    private var _beer = MutableLiveData<List<PunkBeers>>()
    val beer: LiveData<List<PunkBeers>>
        get() = _beer

    private var _error = MutableLiveData<Exception>()
    val error: LiveData<Exception>
        get() = _error

    fun retrieveBeers() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _beer.value = beerListProvider.getBeers()
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}