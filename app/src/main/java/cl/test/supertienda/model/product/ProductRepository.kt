package cl.test.supertienda.model.product

import cl.test.supertienda.model.Response
import cl.test.supertienda.model.product.entities.Product
import cl.test.supertienda.model.product.retrofit.FakeStoreApiService
import javax.inject.Inject

/**
 * Repository class for obtaining products data
 * @param apiService [FakeStoreApiService] for load data from FakeStoreAPI
 */
class ProductRepository @Inject constructor(private val apiService: FakeStoreApiService) {

    /**
     * Obtains a list with all the store products
     * @return [Response] with a [List] with all the store [Product]s, if request is successful.
     */
    suspend fun getAllProducts(): Response<List<Product>> {
        val apiResponse = apiService.getProductList()
        return if (apiResponse.isSuccessful)
            Response(
                isSuccessful = true,
                body = apiResponse.body()!!
            )
        else
            Response(
                isSuccessful = false,
                error = "Error ${apiResponse.code()}"
            )
    }

    /**
     * Obtains product data for a given product id
     * @param id Id for the product been requested
     * @return [Response] with the required [Product], if request is successful
     */
    suspend fun getProduct(id: Int): Response<Product> {
        val apiResponse = apiService.getProduct(id)

        return if (apiResponse.isSuccessful)
            Response(
                isSuccessful = true,
                body = apiResponse.body()!!
            )
        else
            Response(
                isSuccessful = false,
                error = "Error ${apiResponse.code()}"
            )
    }

    /**
     * Obtains a list with available product categories in the store
     * @return [Response] with the categories [List], if request is successful
     */
    suspend fun getProductCategories(): Response<List<String>> {
        val apiResponse = apiService.getCategories()
        return if (apiResponse.isSuccessful)
            Response(
                isSuccessful = true,
                body = apiResponse.body()!!
            )
        else
            Response(
                isSuccessful = false,
                error = "Error ${apiResponse.code()}"
            )
    }

    /**
     * Obtains a list with all the store products for a given category
     * @param category Product category to be requested
     * @return [Response] with a [List] with all the store [Product]s for the given category,
     * if request is successful.
     */
    suspend fun getProductsByCategory(category: String): Response<List<Product>> {
        val apiResponse = apiService.getProductListByCategory(category)
        return if (apiResponse.isSuccessful)
            Response(
                isSuccessful = true,
                body = apiResponse.body()!!
            )
        else
            Response(
                isSuccessful = false,
                error = "Error ${apiResponse.code()}"
            )
    }
}