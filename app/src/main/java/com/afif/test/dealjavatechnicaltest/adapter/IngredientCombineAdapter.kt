package com.afif.test.dealjavatechnicaltest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afif.test.dealjavatechnicaltest.data.firebase.IngredientEntity
import com.afif.test.dealjavatechnicaltest.databinding.ItemIngredientBinding
import com.afif.test.dealjavatechnicaltest.databinding.ItemIngredientBoxBinding
import com.bumptech.glide.Glide

class IngredientCombineAdapter(
    private val context: Context,
    private val ingredients: List<IngredientEntity>
) : RecyclerView.Adapter<IngredientCombineAdapter.IngredientCombineViewHolder>() {

    inner class IngredientCombineViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: IngredientEntity) {
            binding.apply {
                tvNameBox.text = ingredient.name
                Glide.with(context)
                    .load(ingredient.image)
                    .centerCrop()
                    .into(ivIngredient)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientCombineAdapter.IngredientCombineViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(context), parent, false)
        return IngredientCombineViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: IngredientCombineAdapter.IngredientCombineViewHolder,
        position: Int
    ) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}