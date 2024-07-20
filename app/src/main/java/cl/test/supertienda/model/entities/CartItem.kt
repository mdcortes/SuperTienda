package cl.test.supertienda.model.entities

/**
 * Class that represent an item in the user cart
 * @param product Product in the cart
 * @param quantity Quantity for this product in the cart
 */
data class CartItem(val product: Product, val quantity: Int)
