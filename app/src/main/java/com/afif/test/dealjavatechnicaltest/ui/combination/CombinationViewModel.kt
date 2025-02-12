package com.afif.test.dealjavatechnicaltest.ui.combination

import androidx.lifecycle.ViewModel
import com.afif.test.dealjavatechnicaltest.data.firebase.FirebaseClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CombinationViewModel @Inject constructor(
    private val firebaseClient: FirebaseClient
) : ViewModel() {

}