package com.afif.test.dealjavatechnicaltest.data.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class FirebaseClient {

    private val firestore = FirebaseFirestore.getInstance()

    fun addIngredient(ingredient: IngredientEntity) {
        firestore.collection("ingredientEntity").document(ingredient.id.toString()).set(ingredient)
    }

    fun updateIngredient(ingredient: IngredientEntity) {
        firestore.collection("ingredientEntity").document(ingredient.id.toString())
            .update("amount", ingredient.amount)
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

    suspend fun getIngredients(): QuerySnapshot {
        return firestore.collection("ingredientEntity")
            .get().await()
    }

    fun addRecipe(recipe: RecipeEntity) {
        firestore.collection("recipeEntity").document(recipe.id.toString()).set(recipe)
            .addOnSuccessListener {
                Log.d("FirebaseClient", "Recipe added successfully")
            }
            .addOnFailureListener { e ->
                Log.e("FirebaseClient", "Error adding recipe", e)
            }
    }
}