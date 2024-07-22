package cl.test.supertienda.model.cart.room.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represent a cart item
 * @param productId Product id, which acts as primary key
 * @param product Product information for cart item
 * @param quantity Quantity for the product in the cart
 */
@Entity
data class RoomCartItem(
    @PrimaryKey val productId: Int,
    @Embedded val product: RoomProduct,
    var quantity: Int
)
