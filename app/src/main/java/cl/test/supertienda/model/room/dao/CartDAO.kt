package cl.test.supertienda.model.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.test.supertienda.model.room.entities.CartItem
import kotlinx.coroutines.flow.Flow

/**
 * [Dao] interface for store cart in Room database
 */
@Dao
interface CartDAO {

    /**
     * Add a new item to the current cart
     * @param item Item to add to the cart
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemToCart(item: CartItem)

    /**
     * Get the current cart items
     * @return Flow with list of [CartItem]s currently on the cart
     */
    @Query("SELECT * FROM cartItem")
    fun getCartItems(): Flow<List<CartItem>>

    /**
     * Update a current cart item
     * @param item [CartItem] to update
     */
    @Update
    suspend fun updateCartItem(item: CartItem)

    /**
     * Deletes a current cart item
     * @param item [CartItem] to delete from cart
     */
    @Delete
    suspend fun deleteCartItem(item: CartItem)

    /**
     * Remove all elements from current cart
     */
    @Query("DELETE FROM cartItem")
    suspend fun discardCart()
}