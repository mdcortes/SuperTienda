package cl.test.supertienda.ui.home.composables.products

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cl.test.supertienda.R

@Composable
fun AddToCartButton(modifier: Modifier = Modifier, onCLick: () -> Unit) {
    SmallFloatingActionButton(modifier = modifier, shape = CircleShape, onClick = { onCLick() }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_to_cart_button_description)
        )
    }
}