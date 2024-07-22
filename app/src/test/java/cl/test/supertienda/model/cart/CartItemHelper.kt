package cl.test.supertienda.model.cart

import cl.test.supertienda.model.cart.entities.CartItem
import cl.test.supertienda.model.cart.room.entities.RoomCartItem
import cl.test.supertienda.model.cart.room.entities.RoomProduct
import cl.test.supertienda.model.product.ProductHelper.getProduct
import cl.test.supertienda.model.product.entities.Product
import cl.test.supertienda.model.product.entities.Rating
import cl.test.supertienda.tools.Utilities
import org.junit.Assert.assertEquals
import kotlin.random.Random

/**
 * Helper class for testing cart items
 */
internal object CartItemHelper {

    /**
     * Creates a [RoomCartItem] object with random data
     * @return[RoomCartItem] object with random data
     */
    fun getRoomCartItem() = RoomCartItem(
        productId = Random.nextInt(),
        product = RoomProduct(
            title = Utilities.randomString(),
            price = Random.nextDouble(0.0, 1_000.0),
            image = Utilities.randomString()
        ),
        quantity = Random.nextInt(0, 100)
    )

    /**
     * Creates a [CartItem] object with random data
     * @return[CartItem] object with random data
     */
    fun getCartItem() = CartItem(
        product = getProduct(),
        quantity = Random.nextInt(0, 2_000)
    )

    /**
     * Creates a list of [RoomCartItem]s with random data
     * @param length Length of the required list
     * @return [List] of [RoomCartItem]s with random data
     */
    fun geRoomCartItemList(length: Int = 10) = (1..length).map {
        getRoomCartItem()
    }

    /**
     * Execute the assertions required for checking if a [Product] is equivalent to a [RoomProduct]
     * @param product [Product] to check
     * @param roomProduct [RoomProduct] to check
     */
    fun assertProductEqualsRoomProduct(product: Product, roomProduct: RoomProduct) {
        assertEquals(roomProduct.title, product.title)
        assertEquals(roomProduct.price, product.price, 0.0)
        assertEquals(roomProduct.image, product.image)
    }

    /**
     * Execute the assertions required for checking if a [CartItem] is equivalent to a [RoomCartItem]
     * @param cartItem [CartItem] to check
     * @param roomCartItem [RoomCartItem] to check
     */
    fun assertCartItemEqualsRoomCartItem(cartItem: CartItem, roomCartItem: RoomCartItem) {
        assertEquals(cartItem.quantity, roomCartItem.quantity)
        assertEquals(cartItem.product.id, roomCartItem.productId)
        assertProductEqualsRoomProduct(cartItem.product, roomCartItem.product)
    }
}