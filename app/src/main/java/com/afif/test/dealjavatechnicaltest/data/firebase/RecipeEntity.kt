package com.afif.test.dealjavatechnicaltest.data.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeEntity (
    val id: Int
) : Parcelable {
    constructor() : this(0)
}