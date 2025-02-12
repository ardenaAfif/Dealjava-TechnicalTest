package com.afif.test.dealjavatechnicaltest.data.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientEntity (
    val id: Int,
    val name: String,
    val image: String,
    val amount: Int
) : Parcelable {
    constructor() : this(0, "", "", 0)
}