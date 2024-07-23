package cl.test.supertienda.ui.home.composables.products

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.test.supertienda.model.product.entities.Product

@Composable
fun ProductsGrid(
    contentPadding: PaddingValues,
    featuredProduct: Product,
    products: List<Product>,
    showProductDetails: (Product) -> Unit,
    addProductToCart: (Product) -> Unit
) {
    LazyVerticalGrid(
        contentPadding = contentPadding,
        columns = GridCells.Fixed(2)
    ) {
        item(span = { GridItemSpan(2) }) {
            FeaturedProductCard(
                Modifier.fillMaxWidth(),
                product = featuredProduct,
                showDetails = showProductDetails,
                addToCart = addProductToCart
            )
        }

        items(products) { product ->
            ProductCard(
                product = product,
                showDetails = showProductDetails,
                addToCart = addProductToCart
            )
        }
    }
}