package com.android.example.beerlist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.example.beerlist.SecondActivity
import com.android.example.beerlist.databinding.ActivityItemBinding
import com.android.example.beerlist.network.PunkBeers
import com.bumptech.glide.Glide

class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showItem()
       binding.imageButton.setOnClickListener{
            back()
        }
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

    private fun back(){
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}