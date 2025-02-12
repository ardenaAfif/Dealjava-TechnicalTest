package com.afif.test.dealjavatechnicaltest.ui.ingredient

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.afif.test.dealjavatechnicaltest.adapter.IngredientBoxAdapter
import com.afif.test.dealjavatechnicaltest.data.Ingredient
import com.afif.test.dealjavatechnicaltest.data.IngredientBox
import com.afif.test.dealjavatechnicaltest.databinding.ActivityIngredientBoxBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientBoxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientBoxBinding
    private lateinit var ingredientAdapter: IngredientBoxAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIngredientBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvSetup()
    }

    private fun rvSetup() {
        val ingredientsBox = loadIngredientsBoxFromJson(this)
        ingredientAdapter = IngredientBoxAdapter(this, ingredientsBox)
        binding.rvBoxIngredient.apply {
            layoutManager =
                GridLayoutManager(this@IngredientBoxActivity, 2)
            adapter = ingredientAdapter
        }
    }

    private fun loadIngredientsBoxFromJson(context: Context): List<IngredientBox> {
        val jsonString = context.assets.open("ingredients_box.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val ingredientType = object : TypeToken<List<IngredientBox>>() {}.type
        return gson.fromJson(jsonString, ingredientType)
    }
}