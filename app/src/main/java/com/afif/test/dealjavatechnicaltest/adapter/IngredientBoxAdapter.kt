package com.afif.test.dealjavatechnicaltest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.afif.test.dealjavatechnicaltest.R
import com.afif.test.dealjavatechnicaltest.data.IngredientBox
import com.afif.test.dealjavatechnicaltest.databinding.ItemIngredientBinding
import com.dotlottie.dlplayer.Mode
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.lottiefiles.dotlottie.core.widget.DotLottieAnimation

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
            Toast.makeText(context, "${ingredientBox.name} opened", Toast.LENGTH_SHORT).show()
        }

        bottomSheetDialog.show()
    }
}