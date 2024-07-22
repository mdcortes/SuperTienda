package cl.test.supertienda.model.product.retrofit

import cl.test.supertienda.model.product.retrofit.FakeStoreApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

/**
 * Class that tests the [FakeStoreApiService] interface against FakeStoreApi service
 */
class FakeStoreApiServiceTest {

    /**
     * Service instance for been used in tests
     */
    private lateinit var service: FakeStoreApiService

    /**
     * Setup the tests, creating the [service] instance connected to FakeStoreApi service
     */
    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FakeStoreApiService::class.java)
    }

    @Test
    fun getProducts_listIsNotEmpty() = runTest {
        val response = service.getProductList()
        assert(response.isSuccessful)
        assertTrue((response.body()!!.isNotEmpty()))
    }

    @Test
    fun getProduct_bodyIsNotNull() = runTest {
        // On 07/20/2024, product ids in FakeStoreAPI are from 1 to 20
        val response = service.getProduct(Random.nextInt(1, 21))
        assert(response.isSuccessful)
        assertNotNull(response.body())
    }

    @Test
    fun getCategories_listIsNotEmpty() = runTest {
        val response = service.getCategories()
        assert(response.isSuccessful)
        assert(response.body()!!.isNotEmpty())
    }

    @Test
    fun getProductsByCategory_listIsNotEmptyAndProductsAreFromSelectedCategory() = runTest {
        // These are the categories in FakeStoreApi on 07/20/2024
        val category = listOf(
            "electronics",
            "jewelery",
            "men's clothing",
            "women's clothing"
        )[Random.nextInt(0, 4)]
        val response = service.getProductListByCategory(category)

        assert(response.isSuccessful)
        assert(response.body()!!.isNotEmpty())
        response.body()!!.forEach {
            assert(it.category == category)
        }
    }
}