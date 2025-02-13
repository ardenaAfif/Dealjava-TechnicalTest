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

    fun deleteIngredient(ingredient: IngredientEntity) {
        firestore.collection("ingredientEntity").document(ingredient.id.toString()).delete()
    }

    suspend fun getIngredientById(id: Int): IngredientEntity? {
        return try {
            val document = firestore.collection("ingredientEntity").document(id.toString()).get().await()
            if (document.exists()) {
                document.toObject(IngredientEntity::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("FirebaseClient", "Error getting ingredient", e)
            null
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

    suspend fun getRecipes(): QuerySnapshot {
        return firestore.collection("recipeEntity")
            .get().await()
    }
}