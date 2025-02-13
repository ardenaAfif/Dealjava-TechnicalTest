package com.afif.test.dealjavatechnicaltest.ui.combination

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.afif.test.dealjavatechnicaltest.data.firebase.RecipeEntity
import com.afif.test.dealjavatechnicaltest.databinding.ActivityCombinationDetailBinding
import com.bumptech.glide.Glide
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieSource

class CombinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCombinationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCombinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe: RecipeEntity? = intent.getParcelableExtra("recipe")
        val failed = intent.getBooleanExtra("failed", false)

        if (failed) {
            // Jika gagal, tampilkan animasi gagal dan sembunyikan detail resep
            val config = Config.Builder()
                .autoplay(true)
                .speed(1f)
                .loop(true)
                .source(DotLottieSource.Asset("lottie_cooking_failed.json"))
                .playMode(Mode.BOUNCE)
                .build()
            binding.lottieStatus.load(config)
            binding.tvStatus.text = "Oh no! You failed to make the recipe"
            binding.tvNameRecipe.visibility = View.GONE
            binding.ivRecipe.visibility = View.GONE
            binding.tvDetailRecipe.visibility = View.GONE
        } else if (recipe != null) {
            // Jika berhasil, tampilkan animasi berhasil dan detail resep
            val config = Config.Builder()
                .autoplay(true)
                .speed(1f)
                .loop(true)
                .source(DotLottieSource.Asset("lottie_cooking_success.json")) // asset from the asset folder .json or .lottie
                .playMode(Mode.BOUNCE)
                .build()
            binding.lottieStatus.load(config)
            binding.tvStatus.text = "Yeayy! You successfully made the recipe"
            binding.tvNameRecipe.text = recipe.name
            binding.tvDetailRecipe.text = recipe.description

            Glide.with(this)
                .load(recipe.image)
                .centerCrop()
                .into(binding.ivRecipe)

        }

        binding.btnOk.setOnClickListener {
            finish()
        }

    }
}