package cl.test.supertienda.model.cart.mapper

import cl.test.supertienda.model.cart.CartItemHelper
import cl.test.supertienda.model.cart.CartItemHelper.assertCartItemEqualsRoomCartItem
import cl.test.supertienda.model.cart.CartItemHelper.assertProductEqualsRoomProduct
import cl.test.supertienda.model.product.ProductHelper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Class for testing the mapping function defined in CartItemMapper.kt file
 */
class CartItemMapperTest {

    @Test
    fun roomCartItem_toCartItem() {
        val roomCartItem = CartItemHelper.getRoomCartItem()
        val cartItem = roomCartItem.toCartItem()
        assertEquals(cartItem.product.id, roomCartItem.productId)
        assertProductEqualsRoomProduct(cartItem.product, roomCartItem.product)
    }

    @Test
    fun product_toRoomProduct() {
        val product = ProductHelper.getProduct()
        val roomProduct = product.toRoomProduct()
        assertProductEqualsRoomProduct(product, roomProduct)
    }

    @Test
    fun cartItem_toRoomCartItem() {
        val cartItem = CartItemHelper.getCartItem()
        val roomCartItem = cartItem.toRoomCartItem()

        assertCartItemEqualsRoomCartItem(cartItem, roomCartItem)
    }
}