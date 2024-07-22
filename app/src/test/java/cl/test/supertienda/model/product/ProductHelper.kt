package cl.test.supertienda.model.product

import cl.test.supertienda.model.product.entities.Product
import cl.test.supertienda.model.product.entities.Rating
import cl.test.supertienda.tools.Utilities
import kotlin.random.Random

/**
 * Helper class for testing products
 */
object ProductHelper {
    /**
     * Obtains a [Product] with random data
     * @param category Product category. If null, a random category will be generated
     * @return [Product] object with random data
     */
    fun getProduct(category: String? = null) = Product(
        id = Random.nextInt(),
        title = Utilities.randomString(),
        price = Random.nextDouble(0.0, 1_000.0),
        image = Utilities.randomString(),
        description = Utilities.randomString(10000),
        category = category ?: Utilities.randomString(30),
        rating = Rating(
            rate = Random.nextDouble(0.0, 5.0),
            count = Random.nextInt(0, 2_000)
        )
    )

    /**
     * Obtains a [Product] list with random data
     * @param length Required list length
     * @param category
     * @return [List] of [Product]s with random data
     */
    fun getProductList(length: Int = 10, category: String? = null) = (1..length).map {
        getProduct(category)
    }
}