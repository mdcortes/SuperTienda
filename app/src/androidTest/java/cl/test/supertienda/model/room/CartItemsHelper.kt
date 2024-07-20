package cl.test.supertienda.model.room

import cl.test.supertienda.model.room.entities.CartItem
import cl.test.supertienda.model.room.entities.Product
import kotlin.random.Random

/**
 * Helper class for providing mock data for testing
 */
object CartItemsHelper {

    /**
     * List of cart items, based on https://fakestoreapi.com/products response on 07/20/2024
     */
    private val cartItems = listOf(
        CartItem(
            productId = 1,
            product = Product(
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95,
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
            ),
            quantity = Random.nextInt(0, 100)
        ),
        CartItem(
            productId = 2,
            product = Product(
                title = "Mens Casual Premium Slim Fit T-Shirts",
                price = 22.3,
                image = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"
            ),
            quantity = Random.nextInt(0, 100)
        ),
        CartItem(
            productId = 3,
            product = Product(
                title = "Mens Cotton Jacket",
                price = 55.99,
                image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"
            ),
            quantity = Random.nextInt(0, 100)
        ),
        CartItem(
            productId = 4,
            product = Product(
                title = "Mens Casual Slim Fit",
                price = 15.99,
                image = "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg"
            ),
            quantity = Random.nextInt(0, 100)
        ),
        CartItem(
            productId = 5,
            product = Product(
                title = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
                price = 695.0,
                image = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"
            ),
            quantity = Random.nextInt(1, 100)
        )

    )

    /**
     * Obtains a [List] of [CartItem]s, with random quantity from 1 to 100 each
     * @return [List] of mock [CartItem]s
     */
    fun getCartItems() = cartItems

    /**
     * Obtains a [CartItem], with random quantity from 1 to 100
     * @return A mock [CartItem]
     */
    fun getCartItem() = cartItems[Random.nextInt(0, cartItems.size)]

    /**
     * Creates a copy of the given [CartItem], but with a random price between -50% and +50% of
     * current value
     * @param item [CartItem] to duplicate
     * @return[CartItem] with different price
     */
    fun changeCartItemPrice(item: CartItem) = CartItem(
        productId = item.productId,
        product = Product(
            title = item.product.title,
            price = item.product.price
                    + Random.nextDouble(-item.product.price * .5, item.product.price * .5),
            image = item.product.image
        ),
        quantity = item.quantity
    )

    /**
     * Increments the quantity of the given [CartItem] in 1 unit
     * @param item [CartItem] to modify
     * @return Given [CartItem] with incremented quantity
     */
    fun incrementQuantityCartItem(item: CartItem) = CartItem(
        productId = item.productId,
        product = item.product,
        quantity = item.quantity + 1
    )

    /**
     * Decrements the quantity of the given [CartItem] in 1 unit
     * @param item [CartItem] to modify
     * @return Given [CartItem] with decremented quantity
     */
    fun decrementQuantityCartItem(item: CartItem) = CartItem(
        productId = item.productId,
        product = item.product,
        quantity = item.quantity - 1
    )
}