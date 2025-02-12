package com.afif.test.dealjavatechnicaltest.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afif.test.dealjavatechnicaltest.R
import com.afif.test.dealjavatechnicaltest.data.json.Ingredient
import com.afif.test.dealjavatechnicaltest.data.json.IngredientBox
import com.afif.test.dealjavatechnicaltest.databinding.ItemIngredientBoxBinding
import com.afif.test.dealjavatechnicaltest.ui.ingredient.IngredientsOpenActivity
import com.dotlottie.dlplayer.Mode
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.lottiefiles.dotlottie.core.widget.DotLottieAnimation

class IngredientBoxAdapter(private val context: Context, private val ingredientBox: List<IngredientBox>) :
    RecyclerView.Adapter<IngredientBoxAdapter.IngredientBoxViewHolder>() {

    inner class IngredientBoxViewHolder(private val binding: ItemIngredientBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(ingredientBox: IngredientBox) {
                binding.apply {
                    tvNameBox.text = ingredientBox.name
                    val config = Config.Builder()
                        .autoplay(true)
                        .speed(1f)
                        .loop(true)
                        .source(DotLottieSource.Asset(ingredientBox.lottieFile))
                        .playMode(Mode.BOUNCE)
                        .build()
                    lottieGift.load(config)
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientBoxAdapter.IngredientBoxViewHolder {
        val binding = ItemIngredientBoxBinding.inflate(LayoutInflater.from(context), parent, false)
        return IngredientBoxViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientBoxViewHolder, position: Int) {
        val ingredientBox = ingredientBox[position]
        holder.bind(ingredientBox)
        holder.itemView.setOnClickListener {
            showBottomSheetDialog(ingredientBox)
        }
    }

    override fun getItemCount(): Int {
        return ingredientBox.size
    }

    private fun showBottomSheetDialog(ingredientBox: IngredientBox) {
        val bottomSheetDialog = BottomSheetDialog(context)
        val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_box, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        val dotLottieAnimation = bottomSheetView.findViewById<DotLottieAnimation>(R.id.lottieGiftSheet)
        val ingredientName = bottomSheetView.findViewById<TextView>(R.id.nameBox)
        val btnOpenBox = bottomSheetView.findViewById<Button>(R.id.btnOpenBox)

        val config = Config.Builder()
            .autoplay(true)
            .speed(1f)
            .loop(true)
            .source(DotLottieSource.Asset(ingredientBox.lottieFile))
            .playMode(Mode.BOUNCE)
            .build()
        dotLottieAnimation.load(config)
        ingredientName.text = ingredientBox.name

        btnOpenBox.setOnClickListener {
            bottomSheetDialog.dismiss()
            // Show the gift opening animation
            val giftAnimation = DotLottieAnimation(context)
            val giftConfig = Config.Builder()
                .autoplay(true)
                .speed(1f)
                .loop(false) // Play once
                .source(DotLottieSource.Asset("gift_open.lottie")) // Load your gift opening animation
                .build()
            giftAnimation.load(giftConfig)

            // Create a new dialog to show the animation
            val animationDialog = Dialog(context)
            animationDialog.setContentView(giftAnimation)
            animationDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            animationDialog.setCancelable(false) // Prevent dismissing the dialog
            animationDialog.show()

            // Use a Handler to delay the transition to IngredientOpenActivity
            Handler(Looper.getMainLooper()).postDelayed({
                animationDialog.dismiss() // Dismiss the animation dialog
                openIngredientOpenActivity(ingredientBox.name) // Open the new activity
            }, 4000)
        }

        bottomSheetDialog.show()
    }

    private fun openIngredientOpenActivity(nameBox: String) {
        val ingredients = loadIngredientsFromJson(context)
        val randomIngredients = getRandomIngredients(ingredients)

        val intent = Intent(context, IngredientsOpenActivity::class.java).apply {
            putExtra("nameBox", nameBox)
            putParcelableArrayListExtra("ingredients", ArrayList(randomIngredients))
        }
        context.startActivity(intent)
    }


    private fun loadIngredientsFromJson(context: Context): List<Ingredient> {
        val jsonString = context.assets.open("ingredients.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val ingredientType = object : TypeToken<List<Ingredient>>() {}.type
        return gson.fromJson(jsonString, ingredientType)
    }

    private fun getRandomIngredients(ingredients: List<Ingredient>): List<Ingredient> {
        val randomIngredients = ingredients.shuffled().take(5)
        return randomIngredients
    }
}