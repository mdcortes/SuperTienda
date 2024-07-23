package cl.test.supertienda.ui.home.composables.ratingbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import cl.test.supertienda.R
import kotlin.math.roundToInt

@Composable
fun RatingStar(
    rating: Float = 5f,
    maxRating: Int = 5
) {
    Row {
        for (i in 1..maxRating) {
            Icon(
                imageVector =
                if (i <= rating)
                    // Filled star
                    Icons.Filled.Star
                else if (i.toFloat() in rating..(rating + 1))
                    // Half star
                    ImageVector.vectorResource(id = R.drawable.ic_star_half)
                else
                    // Outlined star
                    ImageVector.vectorResource(id = R.drawable.ic_star_outline),
                contentDescription = null
            )
        }
    }
}