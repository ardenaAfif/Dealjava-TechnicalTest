package com.afif.test.dealjavatechnicaltest.ui.combination

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.afif.test.dealjavatechnicaltest.adapter.IngredientCombineAdapter
import com.afif.test.dealjavatechnicaltest.data.firebase.IngredientEntity
import com.afif.test.dealjavatechnicaltest.data.firebase.RecipeEntity
import com.afif.test.dealjavatechnicaltest.databinding.ActivityCombinationBinding
import com.afif.test.dealjavatechnicaltest.ui.ingredient.IngredientViewModel
import com.afif.test.dealjavatechnicaltest.utils.Resource
import com.bumptech.glide.Glide
import com.dotlottie.dlplayer.Mode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.lottiefiles.dotlottie.core.widget.DotLottieAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.io.IOException
import java.io.InputStream

@AndroidEntryPoint
class CombinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCombinationBinding
    private val viewModel by viewModels<IngredientViewModel>()
    private val combinationViewModel by viewModels<CombinationViewModel>()
    private lateinit var ingredientCombineAdapter: IngredientCombineAdapter

    private var selectedIngredients = mutableListOf<IngredientEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCombinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRvIngredient()
        fetchIngredients()

        binding.btnCombine.setOnClickListener {
            handleCombine()
        }
    }

    private fun setupRvIngredient() {
        ingredientCombineAdapter =
            IngredientCombineAdapter(this, emptyList()) { selectedIngredient ->
                updateSelectedIngredients(selectedIngredient)
            }
        binding.rvIngredientCombination.apply {
            layoutManager =
                GridLayoutManager(this@CombinationActivity, 3)
            adapter = ingredientCombineAdapter
        }
    }

    private fun updateSelectedIngredients(selectedIngredient: IngredientEntity) {
        if (selectedIngredients.size < 2) {
            selectedIngredients.add(selectedIngredient)
        } else {
            selectedIngredients[0] = selectedIngredients[1]
            selectedIngredients[1] = selectedIngredient
        }
        updateUI()
    }

    private fun updateUI() {
        if (selectedIngredients.isNotEmpty()) {
            val ingredient1 = selectedIngredients.getOrNull(0)
            ingredient1?.let {
                binding.tvNameBox1.text = it.name
                Glide.with(this)
                    .load(it.image)
                    .centerCrop()
                    .into(binding.ivIngredient1)

            }
        }

        if (selectedIngredients.size > 1) {
            val ingredient2 = selectedIngredients.getOrNull(1)
            ingredient2?.let {
                binding.tvNameBox2.text = it.name
                Glide.with(this)
                    .load(it.image)
                    .centerCrop()
                    .into(binding.ivIngredient2)
            }
        }
    }

    private fun fetchIngredients() {
        lifecycleScope.launchWhenStarted {
            viewModel.getIngredient()
            viewModel.addIngredient.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        showLoading()
                    }

                    is Resource.Success -> {
                        hideLoading()
                        resource.data?.let { ingredients ->
                            // Update the adapter with the new list of ingredients
                            hideLoading()
                            val ingredientData = resource.data ?: emptyList()

                            if (ingredientData.isEmpty()) {
                                noData()
                            } else {
                                showData(ingredientData)
                            }

                            Log.d(">>Ingredients", ingredients.toString())
                        }
                    }

                    is Resource.Error -> {
                        hideLoading()
                        Toast.makeText(
                            this@CombinationActivity,
                            resource.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun handleCombine() {
        // Periksa apakah kedua slot bahan sudah terisi
        if (selectedIngredients.size < 2) {
            Toast.makeText(this, "Pilih dua bahan terlebih dahulu!", Toast.LENGTH_SHORT).show()
            return
        }

        val ingredient1 = selectedIngredients[0]
        val ingredient2 = selectedIngredients[1]

        // Load recipes.json dari assets
        val recipesJson = loadRecipesFromAssets()
        val matchedRecipe = recipesJson.find { recipe ->
            val recipeIngredientIds = recipe.ingredients.map { it.id }
            recipeIngredientIds.contains(ingredient1.id) && recipeIngredientIds.contains(ingredient2.id)
        }

        if (matchedRecipe != null) {
            // Simpan ke Firebase jika ditemukan resep
            combinationViewModel.saveRecipeToFirebase(matchedRecipe)

            // Kurangi jumlah bahan di Firebase
            combinationViewModel.updateIngredientAmount(listOf(ingredient1, ingredient2))

            val cookingAnimation = DotLottieAnimation(this)
            val giftConfig = Config.Builder()
                .autoplay(true)
                .speed(1f)
                .loop(false) // Play once
                .source(DotLottieSource.Asset("lottie_cooking.lottie")) // Load your gift opening animation
                .playMode(Mode.FORWARD)
                .build()
            cookingAnimation.load(giftConfig)

            // a new dialog to show the animation
            val animationDialog = Dialog(this)
            animationDialog.setContentView(cookingAnimation)
            animationDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            animationDialog.setCancelable(false)
            animationDialog.show()
            cookingAnimation.stop()

            // Use a Handler to delay the transition to IngredientOpenActivity
            Handler(Looper.getMainLooper()).postDelayed({
                animationDialog.dismiss() // Dismiss the animation dialog
                val intent = Intent(this, CombinationDetailActivity::class.java).apply {
                    putExtra("recipe", matchedRecipe)
                }
                startActivity(intent)
                finish()
            }, 5000)

        } else {
            // Jika kombinasi tidak ditemukan
            val cookingAnimation = DotLottieAnimation(this)
            val giftConfig = Config.Builder()
                .autoplay(true)
                .speed(1f)
                .loop(false) // Play once
                .source(DotLottieSource.Asset("lottie_cooking.lottie")) // Load your gift opening animation
                .build()
            cookingAnimation.load(giftConfig)

            // Create a new dialog to show the animation
            val animationDialog = Dialog(this)
            animationDialog.setContentView(cookingAnimation)
            animationDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            animationDialog.setCancelable(false) // Prevent dismissing the dialog
            animationDialog.show()
            cookingAnimation.stop()

            // Use a Handler to delay the transition to IngredientOpenActivity
            Handler(Looper.getMainLooper()).postDelayed({
                animationDialog.dismiss() // Dismiss the animation dialog
                val intent = Intent(this, CombinationDetailActivity::class.java).apply {
                    putExtra("failed", true)
                }
                startActivity(intent)
                finish()
            }, 5000)
        }
    }

    private fun loadRecipesFromAssets(): List<RecipeEntity> {
        val json: String
        try {
            val inputStream: InputStream = assets.open("recipes.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }

        val gson = Gson()
        val listType = object : TypeToken<List<RecipeEntity>>() {}.type
        return gson.fromJson(json, listType)
    }

    private fun showLoading() {
        binding.apply {
            binding.cardCombineIngredient.visibility = View.GONE
            shimmerFrame.visibility = View.VISIBLE
            shimmerFrame.startShimmer()
        }
    }

    private fun hideLoading() {
        binding.apply {
            binding.cardCombineIngredient.visibility = View.VISIBLE
            shimmerFrame.stopShimmer()
            shimmerFrame.visibility = View.GONE
        }
    }

    private fun showData(ingredients: List<IngredientEntity>) {
        ingredientCombineAdapter = IngredientCombineAdapter(
            this@CombinationActivity,
            ingredients
        ) { selectedIngredient ->
            updateSelectedIngredients(selectedIngredient)
        }
        binding.rvIngredientCombination.adapter = ingredientCombineAdapter

        binding.apply {
            rvIngredientCombination.visibility = View.VISIBLE
            lottieNoData.visibility = View.GONE // Sembunyikan animasi "No Data" jika ada data
        }
    }

    private fun noData() {
        binding.apply {
            lottieNoData.visibility = View.VISIBLE
            val config = Config.Builder()
                .autoplay(true)
                .speed(1f)
                .loop(true)
                .source(DotLottieSource.Asset("lottie_no_data.json"))
                .playMode(Mode.FORWARD)
                .build()
            binding.lottieNoData.load(config)
            binding.cardCombineIngredient.visibility = View.GONE
        }
    }

}