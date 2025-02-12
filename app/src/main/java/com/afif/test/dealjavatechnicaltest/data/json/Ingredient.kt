package com.afif.test.dealjavatechnicaltest.data.json

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val id: Int,
    val name: String,
    val image: String,
    val amount: Int
) : Parcelable