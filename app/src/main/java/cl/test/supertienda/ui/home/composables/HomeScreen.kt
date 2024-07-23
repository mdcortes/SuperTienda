package cl.test.supertienda.ui.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.test.supertienda.model.product.entities.Product
import cl.test.supertienda.ui.home.HomeViewModel
import cl.test.supertienda.ui.home.composables.navigation.CategoriesDrawerSheet
import cl.test.supertienda.ui.home.composables.products.ProductDetails
import cl.test.supertienda.ui.home.composables.products.ProductsGrid
import cl.test.supertienda.ui.home.composables.topbar.HomeTopBar
import cl.test.supertienda.ui.home.state.HomeUiState
import cl.test.supertienda.ui.theme.SuperTiendaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    goToCart: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val snackbarHostState = remember { SnackbarHostState() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CategoriesDrawerSheet(
                drawerState = drawerState,
                categories = uiState.categories,
                selectedCategory = uiState.selectedCategory,
                setSelectedCategory = { category ->
                    viewModel.loadProductsForCategory(category)
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HomeTopBar(
                    drawerState = drawerState,
                    itemsInCart = uiState.itemsInCart,
                    goToCart = goToCart)
            }
        ) { padding ->
            if (uiState.isLoading && !uiState.showProductDetails)
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant)

            if (uiState.products.isNotEmpty()) {
                ProductsGrid(
                    contentPadding = padding,
                    featuredProduct = uiState.featuredProduct!!,
                    products = uiState.products,
                    showProductDetails = { product ->
                        viewModel.loadProductDetails(product)
                    },
                    addProductToCart = { product: Product ->
                        viewModel.addProductToCart(product)
                    })
            }

            if (uiState.showProductDetails)  {
                ModalBottomSheet(onDismissRequest = { viewModel.closeProductDetails() }) {
                    if (uiState.isLoading) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant)
                    }
                    else {
                        ProductDetails(
                            product = uiState.productDetails!!,
                            addProductToCart = { viewModel.addProductToCart(uiState.productDetails!!)}
                        )
                    }
                }
            }
            
            if(uiState.isError) {
                LaunchedEffect(uiState) {
                    snackbarHostState.showSnackbar(uiState.errorMessage!!)
                }
            }
        }
    }
}
