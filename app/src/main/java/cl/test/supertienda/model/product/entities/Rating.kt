package cl.test.supertienda.model.product.entities

/**
 * Class that represents a product rating
 * @param rate Current product rating
 * @param count Current product rating votes
 */
data class Rating(
    val rate: Double,
    val count: Int
) {
    /**
     * Total rating value used for selecting and showing the featured product in UI
     */
    var totalRating: Double? = null
        get() {
            if (field == null) field = rate * count
            return field
        }
        private set
}
