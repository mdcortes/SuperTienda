package cl.test.supertienda.di

import android.content.Context
import androidx.room.Room
import cl.test.supertienda.model.cart.room.CartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for allow injecting [CartDatabase] dependency with Hilt
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Provides an instance of [CartDatabase] class retrieved from Room
     */
    @Provides
    @Singleton
    fun providesCartDatabase(@ApplicationContext context: Context): CartDatabase = Room
        .databaseBuilder(context, CartDatabase::class.java, "cart_db")
        .build()
}