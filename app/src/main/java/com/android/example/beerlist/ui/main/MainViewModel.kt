package com.android.example.beerlist.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.beerlist.PunkBeerProvider
import com.android.example.beerlist.PunkBeers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val beersProvider: PunkBeerProvider) : ViewModel() {

    private var _beerList = MutableLiveData<List<PunkBeers>>()
    val beerList: LiveData<List<PunkBeers>>
        get() = _beerList

    private var _error = MutableLiveData<Exception>()
    val error: LiveData<Exception>
        get() = _error

    fun retrieveBeers() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _beerList.value = beersProvider.getBeers()

            } catch (error: Exception) {
                Log.d("Second Activity ", "Error retrieving beers $error")
                _error.value = error
            }
        }
    }
}