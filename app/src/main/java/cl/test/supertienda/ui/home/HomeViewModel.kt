package cl.test.supertienda.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.test.supertienda.model.Response
import cl.test.supertienda.model.cart.CartRepository
import cl.test.supertienda.model.product.ProductRepository
import cl.test.supertienda.model.product.entities.Product
import cl.test.supertienda.ui.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for home screen
 * @param productRepository [ProductRepository] for obtaining data for products
 * @param cartRepository [CartRepository] for obtaining and modifying the cart
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
): ViewModel() {

    /**
     * [StateFlow] with the data representation for the UI state for home screen.
     * Allows to modify the data shown in the home screen.
     * Is accessible from the screen itself through the [uiState] readonly variable
     */
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))

    /**
     * [StateFlow] with the data for representing in home screen
     */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadAllElements()
    }

    /**
     * Loads all the elements in the home screen, including the product categories and
     * all the products
     */
    private fun loadAllElements() {
        viewModelScope.launch {
            val categoriesResponse = productRepository.getProductCategories()

            _uiState.update {
                if (categoriesResponse.isSuccessful)
                    HomeUiState(
                        isLoading = true,
                        categories = listOf("all") + categoriesResponse.body!!
                    )
                else
                    HomeUiState(
                        isLoading = true,
                        isError = true,
                        errorMessage = categoriesResponse.error!!
                    )
            }

            updateProductsForResponse(productRepository.getAllProducts())

            launch {
                cartRepository.getCartItems()
                    .map {
                        it.size
                    }.collect { cartItemsNumber ->
                        _uiState.update {
                            it.copy(
                                isError = false,
                                itemsInCart = cartItemsNumber
                            )
                        }
                    }
            }
        }
    }

    /**
     * Loads the products for the given category.
     * Loads all the products if the given category is "all"
     * @param category Category for the products to load
     */
    fun loadProductsForCategory(category: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    isError = false,
                    products = emptyList(),
                    showProductDetails = false,
                    productDetails = null
                )
            }
            val response = if(category == "all")
                productRepository.getAllProducts()
            else
                productRepository.getProductsByCategory(category)

            updateProductsForResponse(response)
        }
    }

    /**
     * Adds a product to the cart
     * @param product Product to add to the cart
     */
    fun addProductToCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addProductToCart(product)
        }
    }

    /**
     * Loads the details for a given product
     * @param product Product for loading details
     */
    fun loadProductDetails(product: Product) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    isError = false,
                    showProductDetails = true,
                    productDetails = null
                )
            }

            val detailedProductResponse = productRepository.getProduct(product.id)
            _uiState.update {
                if (detailedProductResponse.isSuccessful)
                    it.copy(
                        isLoading = false,
                        productDetails = detailedProductResponse.body!!
                    )

                else
                    it.copy(
                        isLoading = false,
                        showProductDetails = false,
                        isError = true,
                        errorMessage = detailedProductResponse.error
                    )
            }
        }
    }

    /**
     * Remove the product details data
     */
    fun closeProductDetails() {
        _uiState.update {
            it.copy(
                isError = false,
                showProductDetails = false,
                productDetails = null
            )
        }
    }

    /**
     * Generates the adequate [HomeUiState] for the [Product] list [Response], including
     * finding the featured product
     * @param productsResponse [ProductRepository] response with the product list
     */
    private fun updateProductsForResponse(productsResponse: Response<List<Product>>) {
        _uiState.update {
            if(productsResponse.isSuccessful) {
                val products = productsResponse.body!!
                val featuredProduct = findFeaturedProduct(products)
                it.copy(
                    isLoading = false,
                    featuredProduct = featuredProduct,
                    products = products - featuredProduct
                )
            }
            else
                it.copy(
                    isLoading = false,
                    featuredProduct = null,
                    products = emptyList(),
                    isError = true,
                    errorMessage = productsResponse.error!!
                )
        }
    }

    /**
     * Find the featured product in the given list
     * @param products [List] of products for finding the featured one
     * @return Featured [Product]
     */
    private fun findFeaturedProduct(products: List<Product>): Product {
        if (products.isEmpty()) throw Exception("Product list is empty")
        var featuredProduct = products.first()
        (products - products.first()).forEach {
            if (it.rating!!.totalRating!! > featuredProduct.rating!!.totalRating!!)
                featuredProduct = it
        }
        return featuredProduct
    }
}