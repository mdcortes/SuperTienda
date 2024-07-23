package cl.test.supertienda.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.test.supertienda.model.cart.CartRepository
import cl.test.supertienda.model.cart.entities.CartItem
import cl.test.supertienda.ui.cart.state.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository): ViewModel() {
    private val _uiState = MutableStateFlow(CartUiState(items = emptyList(), totalAmount = 0.0))
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        loadCart()
    }

    private fun loadCart() {
        viewModelScope.launch {
            cartRepository.getCartItems()
                .collect { items ->
                    _uiState.update {
                        CartUiState(
                            items = items,
                            totalAmount = calculateTotalAmount(items)
                        )
                    }
                }
        }
    }

    private fun calculateTotalAmount(items: List<CartItem>): Double  {
        var totalAmount = 0.0
        items.forEach {
            totalAmount += it.product.price * it.quantity
        }
        return totalAmount
    }

    fun incrementCartItem(item: CartItem) {
        viewModelScope.launch {
            cartRepository.incrementItemInCart(item)
        }
    }

    fun decrementCartItem(item: CartItem) {
        viewModelScope.launch {
            if (item.quantity > 0) cartRepository.decrementItemInCart(item)
        }
    }

    fun removeCartItem(item: CartItem) {
        viewModelScope.launch {
            cartRepository.removeCartItem(item)
        }
    }

    fun discardCart() {
        viewModelScope.launch {
            cartRepository.discardCart()
        }
    }
}