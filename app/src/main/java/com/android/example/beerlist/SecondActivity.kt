package com.android.example.beerlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.beerlist.databinding.ActivitySecondBinding
import com.android.example.beerlist.ui.main.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel :MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        viewModel =(application as BeerOrders).beerViewModelFactory.create(MainViewModel::class.java)
        setContentView(binding.root)
        val userName = intent.getStringExtra("User")
        val message = "Here you are the beers $userName"
        val swipeContainer = binding.swipeRefresh
        binding.secondMessage.text = message
        observeBeers()
        viewModel.retrieveBeers()
        swipeContainer.setOnRefreshListener {
            swipeContainer.isRefreshing = false
            Toast.makeText(this, "Beers Refreshed", Toast.LENGTH_LONG).show()
            viewModel.retrieveBeers()
            observeBeers()
        }
    }

    private fun observeBeers() {
            viewModel.beerList.observe(this) {
                showBeers(it)
            }
            viewModel.error.observe(this) {
                Log.d("Second Activity ", "Error giving beers ")
                Snackbar.make(
                    binding.root,
                    "Error giving beers ",
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Retry")
                    { viewModel.beerList }
                    .show()
            }
        }


    private fun showBeers(beers: List<PunkBeers>) {
        val adapter = BeerListAdapter(beers)
            binding.listView.adapter = BeerListAdapter(beers)
        adapter.notifyDataSetChanged()
        adapter.onItemClick = {
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("PunkBeers", it)
            startActivity(intent)
        }
    }
}