package cl.test.supertienda.ui.home.state

import cl.test.supertienda.model.product.entities.Product

/**
 * Class that represents the home screen UI state
 * @param isLoading Whether the home screen is loading or not
 * @param categories Product categories list
 * @param selectedCategory Currently selected category
 * @param featuredProduct Featured [Product] for been displayed in screen
 * @param products [List] of products to show
 * @param itemsInCart Number of items currently in cart
 * @param showProductDetails Whether the details of an specific product must be shown
 * @param productDetails If [showProductDetails] is true and is not loading, product to show details
 * @param isError Whether the home screen should show an error message
 * @param errorMessage Message to show when [isError] is true
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val categories: List<String> = listOf("all"),
    val selectedCategory: String = "all",
    val featuredProduct: Product? = null,
    val products: List<Product> = emptyList(),
    val itemsInCart: Int = 0,
    val showProductDetails: Boolean = false,
    val productDetails: Product? = null,
    val isError: Boolean = false,
    val errorMessage: String? = null
)