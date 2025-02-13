package com.afif.test.dealjavatechnicaltest.ui.combination

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afif.test.dealjavatechnicaltest.data.firebase.FirebaseClient
import com.afif.test.dealjavatechnicaltest.data.firebase.IngredientEntity
import com.afif.test.dealjavatechnicaltest.data.firebase.RecipeEntity
import com.afif.test.dealjavatechnicaltest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CombinationViewModel @Inject constructor(
    private val firebaseClient: FirebaseClient
) : ViewModel() {

    private val _addCombinationIngredient = MutableStateFlow<Resource<List<RecipeEntity>>>(Resource.Unspecified())
    val addCombinationIngredient: StateFlow<Resource<List<RecipeEntity>>> = _addCombinationIngredient

    private val _updateIngredient = MutableStateFlow<Resource<List<IngredientEntity>>>(Resource.Unspecified())
    val updateIngredient: StateFlow<Resource<List<IngredientEntity>>> = _updateIngredient

    fun saveRecipeToFirebase(recipe: RecipeEntity) {
        viewModelScope.launch {
            try {
                firebaseClient.addRecipe(recipe)
                Log.d("CombinationViewModel", "Resep berhasil disimpan!")
            } catch (e: Exception) {
                Log.e("CombinationViewModel", "Gagal menyimpan resep", e)
            }
        }
    }

    fun updateIngredientAmount(ingredients: List<IngredientEntity>) {
        val updatedIngredients = mutableListOf<IngredientEntity>()

        viewModelScope.launch {
            for (ingredient in ingredients) {
                val existingIngredient = firebaseClient.getIngredientById(ingredient.id)

                if (existingIngredient != null) {
                    if (existingIngredient.amount > 1) {
                        // Kurangi amount sebanyak 1
                        val updatedIngredient =
                            existingIngredient.copy(amount = existingIngredient.amount - 1)
                        firebaseClient.updateIngredient(updatedIngredient)
                        updatedIngredients.add(updatedIngredient)
                    } else if (existingIngredient.amount == 1) {
                        // Hapus ingredient jika amount = 1
                        firebaseClient.deleteIngredient(existingIngredient)
                    }
                }
            }
        }
        _updateIngredient.value = Resource.Success(updatedIngredients)
    }

}