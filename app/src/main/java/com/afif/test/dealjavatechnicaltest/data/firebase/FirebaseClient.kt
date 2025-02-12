package com.afif.test.dealjavatechnicaltest.data.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseClient {

    private val firestore = FirebaseFirestore.getInstance()

    fun addIngredient(ingredient: IngredientEntity) {
        firestore.collection("ingredientEntity").document(ingredient.id.toString()).set(ingredient)
    }

    fun updateIngredient(ingredient: IngredientEntity) {
        firestore.collection("ingredientEntity").document(ingredient.id.toString()).update("amount", ingredient.amount)
    }

    fun getIngredientById(id: Int, callback: (IngredientEntity?) -> Unit) {
        firestore.collection("ingredientEntity").document(id.toString()).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val ingredient = document.toObject(IngredientEntity::class.java)
                    callback(ingredient)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FirebaseClient", "Error getting document", exception)
                callback(null)
            }
    }
}