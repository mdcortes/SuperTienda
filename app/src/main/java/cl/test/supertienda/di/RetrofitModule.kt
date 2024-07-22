package cl.test.supertienda.di

import cl.test.supertienda.model.product.retrofit.FakeStoreApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module for allow injecting [FakeStoreApiService] dependency with Hilt
 */
@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    /**
     * Provides an instance of [FakeStoreApiService] class retrieved from Retrofit
     */
    @Provides
    @Singleton
    fun providesFakeApiStoreService(): FakeStoreApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(FakeStoreApiService::class.java)
    }
}