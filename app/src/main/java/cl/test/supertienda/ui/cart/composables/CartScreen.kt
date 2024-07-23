package cl.test.supertienda.ui.cart.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.test.supertienda.R
import cl.test.supertienda.ui.cart.CartViewModel
import cl.test.supertienda.ui.cart.composables.cartitem.CartItemCard
import cl.test.supertienda.ui.cart.composables.topbar.CartTopBar

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    navigateBack: () -> Unit
    ) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CartTopBar(navigateBack = navigateBack) }
        ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)) {
            LazyColumn(Modifier.weight(.8f)) {
                items(uiState.items) { item ->
                    CartItemCard(
                        item = item,
                        incrementQuantity = {
                            viewModel.incrementCartItem(it)
                        },
                        decrementQuantity = {
                            viewModel.decrementCartItem(it)
                        },
                        removeItem = {
                            viewModel.removeCartItem(it)
                        }
                    )
                }
            }
            TotalCartCard(
                modifier = Modifier.weight(.2f),
                totalAmount = uiState.totalAmount,
                onPurchase = { /* Do  nothing */ }
            )
        }
    }
}