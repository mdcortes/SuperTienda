package cl.test.supertienda.model.cart

import cl.test.supertienda.model.cart.entities.CartItem
import cl.test.supertienda.model.cart.mapper.toCartItem
import cl.test.supertienda.model.cart.mapper.toRoomCartItem
import cl.test.supertienda.model.cart.mapper.toRoomProduct
import cl.test.supertienda.model.cart.room.CartDatabase
import cl.test.supertienda.model.cart.room.entities.RoomCartItem
import cl.test.supertienda.model.product.entities.Product
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Class for handle the shopping cart data
 * @param cartDatabase [CartDatabase] object for accessing data
 */
class CartRepository @Inject constructor(cartDatabase: CartDatabase) {

    /**
     * DAO object for interacting with database
     */
    private val cartDAO = cartDatabase.cartDao()

    /**
     * Obtains the items currently in cart
     * @return [List] with [CartItem]s currently in cart
     */
    fun getCartItems() =
        cartDAO.getCartItems()
            .map {
                it.map { dbProduct ->
                    dbProduct.toCartItem()
                }
            }

    /**
     * Adds one unit of a given [Product] to the cart
     * @param product [Product] to add to the cart
     */
    suspend fun addProductToCart(product: Product) {
        cartDAO.addItemToCart(
            RoomCartItem(
                productId = product.id,
                product = product.toRoomProduct(),
                quantity = 1
            )
        )
    }

    /**
     * Increments in one the quantity of a given item in cart
     * @param cartItem [CartItem] to increment quantity
     */
    suspend fun incrementItemInCart(cartItem: CartItem) {
        cartDAO.updateCartItem(
            cartItem.toRoomCartItem().apply {
                quantity++
            }
        )
    }

    /**
     * Decrements in one the quantity of a given item in cart
     * @param cartItem [CartItem] to increment quantity
     */
    suspend fun decrementItemInCart(cartItem: CartItem) {
        cartDAO.updateCartItem(
            cartItem.toRoomCartItem().apply {
                quantity--
            }
        )
    }

    /**
     * Removes a [CartItem] from current shopping cart
     * @param cartItem [CartItem] to remove from cart
     */
    suspend fun removeCartItem(cartItem: CartItem) {
        cartDAO.deleteCartItem(cartItem.toRoomCartItem())
    }

    /**
     * Removes all the items currently in cart
     */
    suspend fun discardCart() {
        cartDAO.discardCart()
    }
}