package cl.test.supertienda.ui.cart.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.test.supertienda.R

@Composable
fun TotalCartCard(
    modifier: Modifier,
    totalAmount: Double,
    onPurchase: () -> Unit
) {
    Card(modifier = modifier) {
        Column(Modifier.fillMaxSize()) {
            Text(
                text = stringResource(
                    id = R.string.total_amount_field,
                    totalAmount
                ),
                Modifier.fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier.fillMaxSize()
                    .padding(
                        horizontal = 32.dp,
                        vertical = 16.dp
                    ),
                onClick = onPurchase
            ) {
                Text(
                    text = stringResource(id = R.string.purchase_button),
                    fontSize = 20.sp
                )
            }
        }
    }
}