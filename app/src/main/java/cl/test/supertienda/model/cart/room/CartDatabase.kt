package cl.test.supertienda.model.cart.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.test.supertienda.model.cart.room.dao.CartDAO
import cl.test.supertienda.model.cart.room.entities.RoomCartItem

/**
 * Class for working with cart in Room/SQLite database
 */
@Database(entities = [RoomCartItem::class], version = 1)
abstract class CartDatabase: RoomDatabase() {
    /**
     * DAO for interact with cart items in database
     */
    abstract fun cartDao(): CartDAO
}