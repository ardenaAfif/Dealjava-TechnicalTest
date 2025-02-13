package com.afif.test.dealjavatechnicaltest.data.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeEntity (
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val ingredients: List<IngredientEntity>
) : Parcelable {
    constructor() : this(0, "", "", "", emptyList())
}