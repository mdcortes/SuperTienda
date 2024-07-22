package cl.test.supertienda.model.cart.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.test.supertienda.model.cart.room.dao.CartDAO
import cl.test.supertienda.model.cart.room.entities.RoomCartItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.random.Random

/**
 * Class for testing [CartDatabase] functionality
 */
@RunWith(AndroidJUnit4::class)
class CartDatabaseTest {
    /**
     * [CartDatabase] instance for making tests
     */
    private lateinit var db: CartDatabase

    /**
     * [CartDAO] instance for testing
     */
    private lateinit var cartDAO: CartDAO

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CartDatabase::class.java).build()
        cartDAO = db.cartDao()
    }

    @Test
    fun insertNewCartItem_checkSuccess() = runTest {
        val item = RoomCartItemsHelper.getCartItem()
        with(cartDAO) {
            addItemToCart(item)

            // DB should have only the inserted element
            val insertedItem = getCartItems().first().first()
            assertEquals(item, insertedItem)
        }
    }

    @Test
    fun insertCartItem_duplicate_overwrite() = runTest {
        val item = RoomCartItemsHelper.getCartItem()
        with(cartDAO) {
            addItemToCart(item)

            val newItem = RoomCartItemsHelper.changeCartItemPrice(item)
            addItemToCart(newItem)

            val insertedItems = getCartItems().first()

            assert(insertedItems.size == 1) // There is only one element in DB
            val insertedItem = insertedItems.first()
            assertNotEquals(insertedItem.product.price, item.product.price)
            assertEquals(insertedItem.product.price, newItem.product.price, 0.0)
        }
    }

    @Test
    fun updateCartItemQuantity_increment() = runTest {
        val item = RoomCartItemsHelper.getCartItem()
        with(cartDAO) {
            addItemToCart(item)

            val newItem = RoomCartItemsHelper.incrementQuantityCartItem(item)
            assertEquals(item.quantity + 1, newItem.quantity)

            updateCartItem(newItem)

            val insertedItems = getCartItems().first()
            assert(insertedItems.size == 1)

            val insertedItem = insertedItems.first()
            assertEquals(insertedItem, newItem)
        }
    }

    @Test
    fun updateCartItemQuantity_decrement() = runTest {
        val item = RoomCartItemsHelper.getCartItem()
        with(cartDAO) {
            addItemToCart(item)

            val newItem = RoomCartItemsHelper.decrementQuantityCartItem(item)
            assertEquals(item.quantity - 1, newItem.quantity)

            updateCartItem(newItem)

            val insertedItems = getCartItems().first()
            assert(insertedItems.size == 1)

            val insertedItem = insertedItems.first()
            assertEquals(insertedItem, newItem)
        }
    }

    /**
     * Helper function for populating the database with several items
     * @return Items the database was populated with
     */
    private suspend fun populateDatabase(): List<RoomCartItem> {
        val items = RoomCartItemsHelper.getCartItems()
        items.forEach {
            cartDAO.addItemToCart(it)
        }
        return items
    }

    @Test
    fun deleteCartItem_itemInDatabase_itemDeleted() = runTest {
        val items = populateDatabase()

        val itemToDelete = items[Random.nextInt(0, items.size)]
        with(cartDAO)
        {
            deleteCartItem(itemToDelete)

            val itemsInDatabase = cartDAO.getCartItems().first()
            assertFalse(itemsInDatabase.contains(itemToDelete))

            val itemsNotDeleted = items.filterNot { it == itemToDelete }
            assert(itemsInDatabase.containsAll(itemsNotDeleted))
        }
    }

    @Test
    fun deleteCart_cartPopulated_cartDeleted() = runTest {
        populateDatabase()
        with(cartDAO) {
            var cartItemsInDatabase = getCartItems().first()
            assert(cartItemsInDatabase.isNotEmpty())

            discardCart()
            cartItemsInDatabase = getCartItems().first()
            assert(cartItemsInDatabase.isEmpty())
        }
    }

    @Test
    fun deleteCart_cartEmpty_cartEmpty() = runTest {
        with(cartDAO) {
            var cartItemsInDatabase = getCartItems().first()
            assert(cartItemsInDatabase.isEmpty())

            discardCart()
            cartItemsInDatabase = getCartItems().first()
            assert(cartItemsInDatabase.isEmpty())
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}