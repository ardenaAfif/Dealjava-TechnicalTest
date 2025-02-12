package com.afif.test.dealjavatechnicaltest.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable