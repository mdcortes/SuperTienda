package cl.test.supertienda.ui.home.composables.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.test.supertienda.R
import cl.test.supertienda.model.product.entities.Product
import cl.test.supertienda.model.product.entities.Rating
import cl.test.supertienda.ui.home.composables.ratingbar.RatingStar
import cl.test.supertienda.ui.theme.SuperTiendaTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetails(
    product: Product,
    addProductToCart:  (Product) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.0F),
            model = product.image,
            contentDescription = product.title
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(.8f),
                text = product.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.weight(.2f),
                text = stringResource(id = R.string.price_field, product.price),
                textAlign = TextAlign.End,
                fontSize = 14.sp
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = product.description!!,
            maxLines = 10
        )
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingStar(rating = product.rating!!.rate.toFloat())
            Spacer(modifier = Modifier.weight(1f))
            AddToCartButton {
                addProductToCart(product)
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailsPreview() {
    val product = Product(
        id = 5,
        title = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
        price = 19.95,
        image = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg",
        description = "From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.",
        rating = Rating(
            rate = 4.5,
            count = 400
        )
    )

    SuperTiendaTheme {
        Surface {
            ProductDetails(product = product) {

            }
        }
    }
}