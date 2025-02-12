package com.afif.test.dealjavatechnicaltest.ui.ingredient

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.afif.test.dealjavatechnicaltest.adapter.IngredientAdapter
import com.afif.test.dealjavatechnicaltest.data.firebase.IngredientEntity
import com.afif.test.dealjavatechnicaltest.data.json.Ingredient
import com.afif.test.dealjavatechnicaltest.databinding.ActivityIngredientsOpenBinding
import com.afif.test.dealjavatechnicaltest.ui.home.MainActivity
import com.afif.test.dealjavatechnicaltest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class IngredientsOpenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientsOpenBinding
    private lateinit var ingredientAdapter: IngredientAdapter
    private val viewModel by viewModels<IngredientViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIngredientsOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameBox = intent.getStringExtra("nameBox")
        binding.tvNameBox.text = nameBox

        rvSetup(intent.getParcelableArrayListExtra("ingredients")!!)

        observeAddIngredient()

        btnClaimAllListener()
    }

    private fun observeAddIngredient() {
        lifecycleScope.launchWhenStarted {
            viewModel.addIngredient.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        // Show loading indicator
                    }
                    is Resource.Success -> {
                        // Handle success
                        val intent = Intent(this@IngredientsOpenActivity, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        Toast.makeText(this@IngredientsOpenActivity, "Ingredients added successfully", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        // Handle error
                        Toast.makeText(this@IngredientsOpenActivity, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun btnClaimAllListener() {
        binding.btnClaimAll.setOnClickListener {
            val ingredientsList = ingredientAdapter.ingredient.map { ingredient ->
                IngredientEntity(ingredient.id, ingredient.name, ingredient.image, ingredient.amount)
            }
            viewModel.addOrUpdateIngredients(ingredientsList)
        }

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