package com.afif.test.dealjavatechnicaltest.di

import com.afif.test.dealjavatechnicaltest.data.firebase.FirebaseClient
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseClient(): FirebaseClient {
        return FirebaseClient()
    }
}