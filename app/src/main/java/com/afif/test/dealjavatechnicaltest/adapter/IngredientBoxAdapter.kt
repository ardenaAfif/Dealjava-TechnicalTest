package com.afif.test.dealjavatechnicaltest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afif.test.dealjavatechnicaltest.data.IngredientBox
import com.afif.test.dealjavatechnicaltest.databinding.ItemIngredientBinding
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieSource

class IngredientBoxAdapter(private val context: Context, private val ingredientBox: List<IngredientBox>) :
    RecyclerView.Adapter<IngredientBoxAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(private val binding: ItemIngredientBinding) :
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
    ): IngredientBoxAdapter.IngredientViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(context), parent, false)
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredientBox[position])
    }

    override fun getItemCount(): Int {
        return ingredientBox.size
    }
}