package com.afif.test.dealjavatechnicaltest.ui.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afif.test.dealjavatechnicaltest.data.firebase.FirebaseClient
import com.afif.test.dealjavatechnicaltest.data.firebase.IngredientEntity
import com.afif.test.dealjavatechnicaltest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val firebaseClient: FirebaseClient
) : ViewModel() {

    private val _addIngredient = MutableStateFlow<Resource<List<IngredientEntity>>>(Resource.Unspecified())
    val addIngredient: StateFlow<Resource<List<IngredientEntity>>> = _addIngredient

    fun addOrUpdateIngredients(ingredients: List<IngredientEntity>) {
        viewModelScope.launch {
            _addIngredient.value = Resource.Loading()
            val updatedIngredients = mutableListOf<IngredientEntity>()

            for (ingredient in ingredients) {
                val existingIngredient = firebaseClient.getIngredientById(ingredient.id)

                if (existingIngredient != null) {
                    // Update amount
                    val updatedIngredient = existingIngredient.copy(amount = existingIngredient.amount + 1)
                    firebaseClient.updateIngredient(updatedIngredient)
                    updatedIngredients.add(updatedIngredient)
                } else {
                    // Add new ingredient
                    val newIngredient = ingredient.copy(amount = 1)
                    firebaseClient.addIngredient(newIngredient)
                    updatedIngredients.add(newIngredient)
                }
            }
            _addIngredient.value = Resource.Success(updatedIngredients)
        }
    }

    fun getIngredient() {
        viewModelScope.launch {
            _addIngredient.value = Resource.Loading()
            try {
                val result = firebaseClient.getIngredients()
                val ingredients = result.toObjects(IngredientEntity::class.java)
                _addIngredient.value = Resource.Success(ingredients)
            } catch (e: Exception) {
                _addIngredient.value = Resource.Error(e.message.toString())
            }
        }
    }
}