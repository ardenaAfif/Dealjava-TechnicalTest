package com.afif.test.dealjavatechnicaltest.ui.ingredient

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.afif.test.dealjavatechnicaltest.adapter.IngredientBoxAdapter
import com.afif.test.dealjavatechnicaltest.data.IngredientBox
import com.afif.test.dealjavatechnicaltest.databinding.ActivityIngredientBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientBinding
    private lateinit var ingredientAdapter: IngredientBoxAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvSetup()
    }

    private fun rvSetup() {
        val ingredients = loadIngredientsFromJson(this)
        ingredientAdapter = IngredientBoxAdapter(this, ingredients)
        binding.rvBoxIngredient.apply {
            layoutManager =
                GridLayoutManager(this@IngredientActivity, 2)
            adapter = ingredientAdapter
        }
    }

    private fun loadIngredientsFromJson(context: Context): List<IngredientBox> {
        val jsonString = context.assets.open("ingredients_box.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val ingredientType = object : TypeToken<List<IngredientBox>>() {}.type
        return gson.fromJson(jsonString, ingredientType)
    }

}