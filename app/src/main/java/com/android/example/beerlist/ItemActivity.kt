package com.android.example.beerlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.example.beerlist.databinding.ActivityItemBinding
import com.bumptech.glide.Glide

class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showItem()
    }

    private fun showItem() {
        val beer = intent.getParcelableExtra<PunkBeers>("PunkBeers")
        if (beer != null) {
            val textView = binding.detailText
            val nameText = binding.nameText
            val imageView = binding.detailImage
            val beerAbv = binding.beerAbv
            val beerIbu = binding.beerIbu

            textView.text = beer.description
            nameText.text = beer.name
            beerAbv.text = beer.abv.toString()
            beerIbu.text = beer.ibu.toString()
            Glide.with(this).load(beer.imageURL).into(imageView)
        }
    }
}