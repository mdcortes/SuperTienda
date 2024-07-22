package cl.test.supertienda.model.cart.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.test.supertienda.model.cart.room.entities.RoomCartItem
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
    suspend fun addItemToCart(item: RoomCartItem)

    /**
     * Get the current cart items
     * @return Flow with list of [RoomCartItem]s currently on the cart
     */
    @Query("SELECT * FROM roomCartItem")
    fun getCartItems(): Flow<List<RoomCartItem>>

    /**
     * Update a current cart item
     * @param item [RoomCartItem] to update
     */
    @Update
    suspend fun updateCartItem(item: RoomCartItem)

    /**
     * Deletes a current cart item
     * @param item [RoomCartItem] to delete from cart
     */
    @Delete
    suspend fun deleteCartItem(item: RoomCartItem)

    /**
     * Remove all elements from current cart
     */
    @Query("DELETE FROM roomCartItem")
    suspend fun discardCart()
}