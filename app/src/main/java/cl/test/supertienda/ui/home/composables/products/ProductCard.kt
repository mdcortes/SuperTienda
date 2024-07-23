package cl.test.supertienda.ui.home.composables.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    showDetails: (Product) -> Unit,
    addToCart: (Product) -> Unit
) {
    ElevatedCard(modifier = modifier
        .fillMaxWidth()
        .padding(24.dp),
        onClick = {
            showDetails(product)
        }) {
        Column {
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .aspectRatio(1.0F),
                model = product.image,
                contentDescription = product.title
            )
            Text(
                text = product.title,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
            Row {
                Text(
                    text = stringResource(id = R.string.price_field, product.price),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .weight(1F)
                )
                AddToCartButton {
                    addToCart(product)
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    val product = Product(
        id = 5,
        title = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
        price = 19.95,
        image = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"
    )

    SuperTiendaTheme {
        Surface {
            ProductCard(product = product, showDetails = {}) {

            }
        }
    }
}