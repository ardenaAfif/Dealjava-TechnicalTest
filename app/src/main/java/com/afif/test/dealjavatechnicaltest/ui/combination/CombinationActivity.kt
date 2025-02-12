package com.afif.test.dealjavatechnicaltest.ui.combination

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.afif.test.dealjavatechnicaltest.R
import com.afif.test.dealjavatechnicaltest.adapter.IngredientAdapter
import com.afif.test.dealjavatechnicaltest.adapter.IngredientCombineAdapter
import com.afif.test.dealjavatechnicaltest.databinding.ActivityCombinationBinding
import com.afif.test.dealjavatechnicaltest.ui.ingredient.IngredientViewModel
import com.afif.test.dealjavatechnicaltest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CombinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCombinationBinding
    private val viewModel by viewModels<IngredientViewModel>()
    private lateinit var ingredientCombineAdapter: IngredientCombineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCombinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRvIngredient()
        fetchIngredients()
    }

    private fun setupRvIngredient() {
        ingredientCombineAdapter = IngredientCombineAdapter(this, emptyList())
        binding.rvIngredientCombination.apply {
            layoutManager =
                GridLayoutManager(this@CombinationActivity, 3)
            adapter = ingredientCombineAdapter
        }
    }

    private fun fetchIngredients() {
        lifecycleScope.launchWhenStarted {
            viewModel.getIngredient()
            viewModel.addIngredient.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Show loading indicator if needed
                    }
                    is Resource.Success -> {
                        resource.data?.let { ingredients ->
                            // Update the adapter with the new list of ingredients
                            ingredientCombineAdapter = IngredientCombineAdapter(this@CombinationActivity, ingredients)
                            binding.rvIngredientCombination.adapter = ingredientCombineAdapter
                            Log.d(">>Ingredients", ingredients.toString())
                        }
                    }
                    is Resource.Error -> {
                        // Handle error (e.g., show a toast)
                        Toast.makeText(this@CombinationActivity, resource.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Handle unspecified state if needed
                    }
                }
            }
        }
    }
}