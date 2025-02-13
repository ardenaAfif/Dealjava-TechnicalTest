package com.afif.test.dealjavatechnicaltest.ui.history

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.afif.test.dealjavatechnicaltest.adapter.RecipeLibraryAdapter
import com.afif.test.dealjavatechnicaltest.databinding.ActivityRecipeLibraryBinding
import com.afif.test.dealjavatechnicaltest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RecipeLibraryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeLibraryBinding
    private lateinit var recipeLibraryAdapter: RecipeLibraryAdapter

    private val viewModel by viewModels<RecipeLibraryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRv()
        fetchLibraryRecipes()
    }

    private fun fetchLibraryRecipes() {
        lifecycleScope.launchWhenStarted {
            viewModel.recipes.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Show loading indicator if needed
                    }
                    is Resource.Success -> {
                        val recipes = resource.data ?: emptyList()
                        recipeLibraryAdapter.differ.submitList(recipes)
                        Log.d(">>Recipe", "Recipes: $recipes")
                    }
                    is Resource.Error -> {
                        // Handle error (e.g., show a toast)
                        Toast.makeText(this@RecipeLibraryActivity, resource.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupRv() {
        recipeLibraryAdapter = RecipeLibraryAdapter(this)
        binding.rvRecipeLibrary.apply {
            layoutManager = LinearLayoutManager(this@RecipeLibraryActivity)
            adapter = recipeLibraryAdapter
        }
    }
}