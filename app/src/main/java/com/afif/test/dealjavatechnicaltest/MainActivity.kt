package com.afif.test.dealjavatechnicaltest

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.afif.test.dealjavatechnicaltest.databinding.ActivityMainBinding
import com.afif.test.dealjavatechnicaltest.ui.ingredient.RecipeLibraryActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menuSetup()
    }

    private fun menuSetup() {
        binding.apply {
            cardHistory.setOnClickListener {
                val intent = Intent(this@MainActivity, RecipeLibraryActivity::class.java)
                startActivity(intent)
            }
        }
    }
}