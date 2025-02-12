package com.afif.test.dealjavatechnicaltest.ui.ingredient

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afif.test.dealjavatechnicaltest.R
import com.afif.test.dealjavatechnicaltest.adapter.IngredientAdapter
import com.afif.test.dealjavatechnicaltest.data.Ingredient
import com.afif.test.dealjavatechnicaltest.databinding.ActivityIngredientsOpenBinding

class IngredientsOpenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientsOpenBinding
    private lateinit var ingredientAdapter: IngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIngredientsOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameBox = intent.getStringExtra("nameBox")
        binding.tvNameBox.text = nameBox

        rvSetup(intent.getParcelableArrayListExtra("ingredients")!!)
    }

    private fun rvSetup(ingredients: List<Ingredient>) {
        ingredientAdapter = IngredientAdapter(this, ingredients)
        binding.rvIngredient.apply {
            layoutManager =
                GridLayoutManager(this@IngredientsOpenActivity, 3)
            adapter = ingredientAdapter
        }
    }
}