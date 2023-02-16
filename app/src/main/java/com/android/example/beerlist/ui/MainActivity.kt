package com.android.example.beerlist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.example.beerlist.SecondActivity
import com.android.example.beerlist.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var insertName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener {
            write()
        }
        binding.nextButton.setOnClickListener {
            secondAct()
        }
    }

    private  fun write() {
        insertName = binding.insertName.text.toString()
        if (insertName == "") {
            Snackbar.make(
                binding.root,
                "Please insert your name ",
                Snackbar.LENGTH_LONG
            )
                .show()
        } else {
            val message = "Let' s go $insertName"
            binding.nameText.text = message
            binding.insertName.text.clear()
        }
    }

    private fun secondAct() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("User", insertName)
        startActivity(intent)
    }
}