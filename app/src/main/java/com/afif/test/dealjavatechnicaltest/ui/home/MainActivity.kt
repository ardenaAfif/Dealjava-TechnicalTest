package com.afif.test.dealjavatechnicaltest.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.afif.test.dealjavatechnicaltest.databinding.ActivityMainBinding
import com.afif.test.dealjavatechnicaltest.ui.history.RecipeLibraryActivity
import com.afif.test.dealjavatechnicaltest.ui.ingredient.IngredientBoxActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            cardIngredient.setOnClickListener {
                val intent = Intent(this@MainActivity, IngredientBoxActivity::class.java)
                startActivity(intent)
            }
//            cardCombine.setOnClickListener {
//                val intent = Intent(this@MainActivity, CombineRecipeActivity::class.java)
//            }
            cardHistory.setOnClickListener {
                val intent = Intent(this@MainActivity, RecipeLibraryActivity::class.java)
                startActivity(intent)
            }
        }
    }
}