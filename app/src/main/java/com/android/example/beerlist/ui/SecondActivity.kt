package com.android.example.beerlist.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.example.beerlist.app.BeerApp
import com.android.example.beerlist.adapter.BeerListAdapter
import com.android.example.beerlist.databinding.ActivitySecondBinding
import com.android.example.beerlist.network.PunkBeers
import com.android.example.beerlist.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        viewModel =
            (application as BeerApp).mainViewModelFactory.create(MainViewModel::class.java)
        observeBeers()
        viewModel.retrieveBeers()
        setContentView(binding.root)
        showName()
        swipe()
        observeBeers()
    }

    private fun observeBeers() {
        val progression = binding.progressBar
        progression.visibility = VISIBLE
        viewModel.beer.observe(this) {
            showBeers(it)
            progression.visibility = INVISIBLE
        }
        viewModel.error.observe(this) {
            progression.visibility = INVISIBLE
            Log.d("Second Activity ", "Error giving beers $it")
            Snackbar.make(
                binding.root,
                "Error giving beers ",
                Snackbar.LENGTH_LONG
            )
                .setAction("Retry")
                { viewModel.retrieveBeers() }
                .show()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
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

    private fun swipe() {
        val swipeContainer = binding.swipeRefresh
        swipeContainer.setOnRefreshListener {
            swipeContainer.isRefreshing = false
            Toast.makeText(this, "Beers Refreshed", Toast.LENGTH_LONG).show()
            observeBeers()
        }
    }
}