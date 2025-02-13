package com.afif.test.dealjavatechnicaltest.ui.history

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
class RecipeLibraryViewModel @Inject constructor(
    private val firebaseClient: FirebaseClient
) : ViewModel() {

    private val _recipes = MutableStateFlow<Resource<List<RecipeEntity>>>(Resource.Unspecified())
    val recipes: StateFlow<Resource<List<RecipeEntity>>> = _recipes

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            _recipes.value = Resource.Loading()
            try {
                val result = firebaseClient.getRecipes()
                val recipe = result.toObjects(RecipeEntity::class.java)
                _recipes.value = Resource.Success(recipe)
            } catch (e: Exception) {
                _recipes.value = Resource.Error(e.message.toString())
            }
        }
    }
}