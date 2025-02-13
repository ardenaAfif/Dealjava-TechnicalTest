package com.afif.test.dealjavatechnicaltest.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afif.test.dealjavatechnicaltest.data.firebase.RecipeEntity
import com.afif.test.dealjavatechnicaltest.databinding.ItemRecipeLibraryBinding
import com.bumptech.glide.Glide

class RecipeLibraryAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecipeLibraryAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(private val binding: ItemRecipeLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(recipe: RecipeEntity) {
                Log.d(">>RecipeLibraryAdapter", "Binding recipe: ${recipe.name}")
                Glide.with(context)
                    .load(recipe.image)
                    .centerCrop()
                    .into(binding.ivRecipe)

                binding.nameRecipe.text = recipe.name
                binding.tvDetailRecipe.text = recipe.description
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeLibraryAdapter.RecipeViewHolder {
        val binding = ItemRecipeLibraryBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeLibraryAdapter.RecipeViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<RecipeEntity>() {
        override fun areItemsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)
}