package cl.test.supertienda.model.cart.mapper

import cl.test.supertienda.model.cart.entities.CartItem
import cl.test.supertienda.model.cart.room.entities.RoomCartItem
import cl.test.supertienda.model.cart.room.entities.RoomProduct
import cl.test.supertienda.model.product.entities.Product

/**
 * Obtains a [CartItem] with the data of this object
 * @return [CartItem] object with this data
 */
internal fun RoomCartItem.toCartItem() = CartItem(
    product = Product(
        id = productId,
        title = product.title,
        price = product.price,
        image = product.image
    ),
    quantity = quantity
)

/**
 * Obtains a [RoomProduct] object for store the data in a Room database
 * @return [RoomProduct] object with data to store
 */
internal fun Product.toRoomProduct() = RoomProduct(
    title = title,
    price = price,
    image = image
)

/**
 * Obtains a [RoomCartItem] object for store the data in a Room database
 * @return [RoomCartItem] object with the data to store
 */
internal fun CartItem.toRoomCartItem() = RoomCartItem(
    productId = product.id,
    product = product.toRoomProduct(),
    quantity = quantity
)