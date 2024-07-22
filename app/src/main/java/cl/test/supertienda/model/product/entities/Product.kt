package cl.test.supertienda.model.product.entities

/**
 * Class that represents a store product
 * @param id Product id
 * @param title Product main title
 * @param price Current product price
 * @param image URL for product image
 * @param description Product description
 * @param category Category in which the product can be found
 * @param rating Product rating
 */
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    val description: String? = null,
    val category: String? = null,
    val rating: Rating? = null
)
