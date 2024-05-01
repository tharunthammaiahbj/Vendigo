package com.example.vendigo.di

import com.example.vendigo.firebase.repository.AuthRepo
import com.example.vendigo.firebase.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
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
        fun provideAuthRepo():AuthRepo {
            return AuthRepositoryImpl(auth = FirebaseAuth.getInstance())
        }

}