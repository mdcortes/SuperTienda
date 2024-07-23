package cl.test.supertienda.ui.cart.state

import cl.test.supertienda.model.cart.entities.CartItem

data class CartUiState(val items: List<CartItem>, val totalAmount: Double)
