package com.afif.test.dealjavatechnicaltest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afif.test.dealjavatechnicaltest.data.Ingredient
import com.afif.test.dealjavatechnicaltest.databinding.ItemIngredientBinding
import com.bumptech.glide.Glide

class IngredientAdapter(private val context: Context, private val ingredient: List<Ingredient>) :
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(ingredient: Ingredient) {
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
    ): IngredientAdapter.IngredientViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(context), parent, false)
        return IngredientViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ingredient.size
    }

    override fun onBindViewHolder(
        holder: IngredientAdapter.IngredientViewHolder,
        position: Int
    ) {
        val ingredient = ingredient[position]
        holder.bind(ingredient)
    }

}
