package cl.test.supertienda.ui.home.composables.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.test.supertienda.R
import cl.test.supertienda.model.product.entities.Product
import cl.test.supertienda.ui.theme.SuperTiendaTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FeaturedProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    showDetails: (Product) -> Unit,
    addToCart: (Product) -> Unit
) {
    ElevatedCard(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
        onClick = {
            showDetails(product)
        }) {
        Box(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 32.dp)
            ) {
                Column(Modifier.weight(0.5F)) {
                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.0F),
                        model = product.image,
                        contentDescription = product.title
                    )
                }
                Column(Modifier.weight(0.5F)) {
                    Text(
                        text = stringResource(id = R.string.featured_product_card_title),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Text(
                        text = product.title,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.price_field, product.price),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            AddToCartButton(modifier = Modifier.align(Alignment.BottomEnd)) {
                addToCart(product)
            }
        }
    }
}

@Composable
@Preview
fun FeaturedProductCardPreview() {
    val product = Product(
        id = 5,
        title = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
        price = 19.95,
        image = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"
    )

    SuperTiendaTheme {
        Surface {
            FeaturedProductCard(product = product, showDetails = {}) {

            }
        }
    }
}