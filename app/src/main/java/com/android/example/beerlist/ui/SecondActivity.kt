package com.android.example.beerlist.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.example.beerlist.adapter.BeerListAdapter
import com.android.example.beerlist.databinding.ActivitySecondBinding
import com.android.example.beerlist.network.BeersInterface
import com.android.example.beerlist.network.PunkBeers
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder()

    .baseUrl("https://api.punkapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private val beerService = retrofit.create(BeersInterface::class.java)


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showName()
        swipe()
        giveBeers()

    }

    private fun giveBeers() {
        val progress = binding.progressBar
        lifecycleScope.launch {
            try {
                progress.visibility = VISIBLE
                val beers = beerService.getBeers()
                showBeers(beers)
                progress.visibility = INVISIBLE
            } catch (e: Error) {
                progress.visibility = INVISIBLE
                Log.d("Second Activity ", "Error giving beers $e")
                Snackbar.make(
                    binding.root,
                    "Error giving beers ",
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Retry")
                    { giveBeers() }
                    .show()
            }
        }
    }


    private fun showBeers(beers: List<PunkBeers>) {
        val list = binding.listView
        val adapter = BeerListAdapter(beers)
        list.adapter = adapter
        adapter.notifyDataSetChanged()
        adapter.onItemClick = {
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("PunkBeers", it)
            startActivity(intent)
        }
    }

    private fun showName() {
        val userName = intent.getStringExtra("User")
        val message = "Here you are the beers $userName"
        binding.secondMessage.text = message
    }

    private fun swipe(){
        val swipeContainer = binding.swipeRefresh
        swipeContainer.setOnRefreshListener {
            swipeContainer.isRefreshing = false
            Toast.makeText(this, "Beers Refreshed", Toast.LENGTH_LONG).show()
            giveBeers()
        }
    }
}