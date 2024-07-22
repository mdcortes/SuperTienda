package cl.test.supertienda.model.cart

import cl.test.supertienda.model.cart.mapper.toRoomCartItem
import cl.test.supertienda.model.cart.mapper.toRoomProduct
import cl.test.supertienda.model.cart.room.CartDatabase
import cl.test.supertienda.model.cart.room.dao.CartDAO
import cl.test.supertienda.model.cart.room.entities.RoomCartItem
import cl.test.supertienda.model.product.ProductHelper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * Class created for testing [CartRepository]
 */
class CartRepositoryTest {
    /**
     * [CartRepository] instance for testing
     */
    private lateinit var cartRepository: CartRepository

    /**
     * [CartDAO] mocked instance used in tests
     */
    private lateinit var cartDAO: CartDAO

    @Before
    fun setUp() {
        cartDAO = mock(CartDAO::class.java)
        val cartDatabase = mock(CartDatabase::class.java)
        `when`(cartDatabase.cartDao()).thenReturn(cartDAO)
        cartRepository = CartRepository(cartDatabase)
    }

    @Test
    fun getCartItems_returnItemList() = runTest {
        val roomCartItemList = CartItemHelper.geRoomCartItemList(20)
        `when`(cartDAO.getCartItems()).thenReturn(flow {
            emit(roomCartItemList)
        })

        val cartItems = cartRepository.getCartItems().first()
        assertEquals(cartItems.size, roomCartItemList.size)
        cartItems.forEach { cartItem ->
            val roomCartItem = roomCartItemList.find { it.productId == cartItem.product.id }
            assertNotNull(roomCartItem)
            CartItemHelper.assertCartItemEqualsRoomCartItem(cartItem, roomCartItem!!)
        }
    }

    @Test
    fun addProductToCart_oneItemAddedToDatabase() = runTest {
        val product = ProductHelper.getProduct()
        val roomCartItem = with(product) {
            RoomCartItem(
                productId = id,
                product = toRoomProduct(),
                quantity = 1
            )
        }
        cartRepository.addProductToCart(product)

        verify(cartDAO).addItemToCart(roomCartItem)
    }

    @Test
    fun incrementItemInCart_updateItemWithQuantityIncremented() = runTest {
        val cartItem = CartItemHelper.getCartItem()
        val roomCartItem = with(cartItem) {
            with(product) {
                RoomCartItem(
                    productId = id,
                    product = toRoomProduct(),
                    quantity = quantity + 1
                )
            }
        }
        cartRepository.incrementItemInCart(cartItem)

        verify(cartDAO).updateCartItem(roomCartItem)
    }

    @Test
    fun decrementItemInCart_updateItemWithQuantityDecremented() = runTest {
        val cartItem = CartItemHelper.getCartItem()
        val roomCartItem = with(cartItem) {
            with(product) {
                RoomCartItem(
                    productId = id,
                    product = toRoomProduct(),
                    quantity = quantity - 1
                )
            }
        }
        cartRepository.decrementItemInCart(cartItem)

        verify(cartDAO).updateCartItem(roomCartItem)
    }

    @Test
    fun removeCartItem_removeItemFromDatabase() = runTest {
        val cartItem = CartItemHelper.getCartItem()
        val roomCartItem = cartItem.toRoomCartItem()

        cartRepository.removeCartItem(cartItem)

        verify(cartDAO).deleteCartItem(roomCartItem)
    }

    @Test
    fun discardCart_discardCartInDatabase() = runTest {
        cartRepository.discardCart()
        verify(cartDAO).discardCart()
    }
}