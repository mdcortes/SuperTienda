package cl.test.supertienda.model.product

import cl.test.supertienda.model.product.retrofit.FakeStoreApiService
import cl.test.supertienda.tools.Utilities
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response
import kotlin.random.Random

/**
 * Class for testing of [ProductRepository]
 */
class ProductRepositoryTest {
    /**
     * [ProductRepository] instance for testing
     */
    private lateinit var productRepository: ProductRepository

    /**
     * [FakeStoreApiService] mock for testing
     */
    private lateinit var apiService: FakeStoreApiService

    @Before
    fun setUp() {
        apiService = mock(FakeStoreApiService::class.java)
        productRepository = ProductRepository(apiService)
    }

    @Test
    fun getAllProducts_responseSuccessful_returnProductList() = runTest {
        val productList = ProductHelper.getProductList()
        `when`(apiService.getProductList())
            .thenReturn(Response.success(productList))

        val response = productRepository.getAllProducts()
        assert(response.isSuccessful)
        assertEquals(response.body, productList)
    }

    @Test
    fun getAllProducts_responseUnsuccessful_returnErrorMessage() = runTest {
        `when`(apiService.getProductList())
            .thenReturn(Response.error(404, ResponseBody.create(null, "Error")))

        val response = productRepository.getAllProducts()
        assertFalse(response.isSuccessful)
        assertEquals(response.error, "Error 404")
    }

    @Test
    fun getProduct_responseSuccessful_returnProduct() = runTest {
        val product = ProductHelper.getProduct()
        `when`(apiService.getProduct(product.id))
            .thenReturn(Response.success(product))

        val response = productRepository.getProduct(product.id)
        assert(response.isSuccessful)
        assertEquals(response.body, product)
    }

    @Test
    fun getProduct_responseUnsuccessful_returnErrorMessage() = runTest {
        val productId = Random.nextInt(0, 2500)
        `when`(apiService.getProduct(productId))
            .thenReturn(Response.error(404, ResponseBody.create(null, "Error")))

        val response = productRepository.getProduct(productId)
        assertFalse(response.isSuccessful)
        assertEquals(response.error, "Error 404")
    }

    @Test
    fun getProductCategories_responseSuccessful_returnCategories() = runTest {
        val categories = (1..5).map { Utilities.randomString(20) }
        `when`(apiService.getCategories())
            .thenReturn(Response.success(categories))

        val response = productRepository.getProductCategories()
        assert(response.isSuccessful)
        assertEquals(categories, response.body)
    }

    @Test
    fun getProductCategories_responseUnsuccessful_returnErrorMessage() = runTest {
        `when`(apiService.getCategories())
            .thenReturn(Response.error(404, ResponseBody.create(null, "Error")))

        val response = productRepository.getProductCategories()
        assertFalse(response.isSuccessful)
        assertEquals(response.error, "Error 404")
    }

    @Test
    fun getProductsByCategory_ResponseSuccessful_returnProducts() = runTest {
        val category = Utilities.randomString(25)
        val productList = ProductHelper.getProductList(category = category)
        productList.forEach {
            assertEquals(it.category, category)
        }
        `when`(apiService.getProductListByCategory(category))
            .thenReturn(Response.success(productList))

        val response = productRepository.getProductsByCategory(category)
        assert(response.isSuccessful)
        assertEquals(response.body, productList)
    }

    @Test
    fun getProductsByCategory_ResponseUnsuccessful_returnErrorMessage() = runTest {
        val category = Utilities.randomString(25)
        `when`(apiService.getProductListByCategory(category))
            .thenReturn(Response.error(404, ResponseBody.create(null, "Error")))

        val response = productRepository.getProductsByCategory(category)
        assertFalse(response.isSuccessful)
        assertEquals(response.error, "Error 404")
    }
}