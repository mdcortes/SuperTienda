package cl.test.supertienda.ui.cart.composables.cartitem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cl.test.supertienda.R

@Composable
fun CartItemQuantitySelector(
    quantity: Int,
    incrementQuantity: () -> Unit,
    decrementQuantity: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        SmallFloatingActionButton(shape = CircleShape, onClick = decrementQuantity) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
                contentDescription = stringResource(id = R.string.decrement_button_description)
            )
        }
        Text(
            text = quantity.toString(),
            Modifier.weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        SmallFloatingActionButton(shape = CircleShape, onClick = incrementQuantity) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.increment_button_description)
            )
        }
    }
}