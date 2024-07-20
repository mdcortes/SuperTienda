package cl.test.supertienda.model.entities

/**
 * Class that represents a store product
 * @param id Product id
 * @param title Product main title
 * @param price Current product price
 * @param description Product description
 * @param category Category in which the product can be found
 * @param image URL for product image
 * @param rating Product rating
 */
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)
