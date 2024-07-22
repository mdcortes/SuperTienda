package cl.test.supertienda.model.cart.room.entities


/**
 * Represents a product, cached in database for been displayed in cart
 * @param id Product Id, as defined in FakeStoreApi service
 * @param title Product title, as defined in FakeStoreApi service
 * @param price Product price
 * @param image Product image URL
 */
data class RoomProduct(
    val title: String,
    val price: Double,
    val image: String
)
