package cl.test.supertienda.model.product.entities

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

/**
 * Class for testing that [Rating.totalRating] is [Rating.rate] * [Rating.count]
 */
class RatingTest {
    @Test
    fun totalRatingIsRateTimesCount() {
        val rate = Random.nextDouble(1.0, 5.0)
        val count = Random.nextInt(0, 5000)

        val rating = Rating(rate = rate, count = count)

        assertEquals(rating.totalRating!!, rate * count, 0.0)
    }
}