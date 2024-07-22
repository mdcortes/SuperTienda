package cl.test.supertienda.model.product.retrofit

import cl.test.supertienda.model.product.entities.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service interface for interact with FakeStoreApi service using Retrofit library
 */
interface FakeStoreApiService {
    /**
     * Obtains the product list from FakeStoreApi
     * @return [Response] with [List] of [Product]s from API if success,
     * [Response] with error details otherwise
     */
    @GET("products")
    suspend fun getProductList(): Response<List<Product>>

    /**
     * Obtains the product with the given id from FakeStoreApi
     * @param id Product id for the product to retrieve
     * @return [Response] with [Product] with given [id] from API if success,
     * [Response] with error details otherwise
     */
    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Response<Product>

    /**
     * Obtains the available categories from FakeStoreApi
     * @return [Response] with [List] of categories from API if success,
     * [Response] with error details otherwise
     */
    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    /**
     * Obtains the product list from FakeStoreApi with a given category
     * @param category Category for searching products
     * @return [Response] with [List] of [Product]s with the given [category] from API if success,
     * [Response] with error details otherwise
     */
    @GET("products/category/{category}")
    suspend fun getProductListByCategory(@Path("category") category: String): Response<List<Product>>

}