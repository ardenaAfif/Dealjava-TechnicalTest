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

    fun updateIngredientAmount(ingredient: IngredientEntity) {
        viewModelScope.launch {
            firebaseClient.getIngredientById(ingredient.id) { existingIngredient ->
                if (existingIngredient != null) {
                    val newAmount = existingIngredient.amount - 1
                    if (newAmount >= 0) {
                        firebaseClient.updateIngredient(existingIngredient.copy(amount = newAmount))
                    }
                }
            }
        }
    }

}