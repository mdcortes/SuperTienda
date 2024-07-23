package cl.test.supertienda.ui.cart.composables.cartitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.test.supertienda.R
import cl.test.supertienda.model.cart.entities.CartItem
import cl.test.supertienda.model.product.entities.Product
import cl.test.supertienda.ui.theme.SuperTiendaTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartItemCard(
    item: CartItem,
    incrementQuantity: (CartItem) -> Unit,
    decrementQuantity: (CartItem) -> Unit,
    removeItem: (CartItem) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)) {
        Row {
            Column(Modifier.weight(0.5f)
                .align(Alignment.CenterVertically)
            ) {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .aspectRatio(1.0F),
                    model = item.product.image,
                    contentDescription = item.product.title
                )
            }
            Column(Modifier.weight(0.5f)) {
                Text(
                    text = item.product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = stringResource(id = R.string.price_field, item.product.price),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                )
                Button(modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                    onClick = { removeItem(item) }) {
                    Text(text = stringResource(id = R.string.remove_item_button_label))
                }
                CartItemQuantitySelector(
                    quantity = item.quantity,
                    incrementQuantity = { incrementQuantity(item) },
                    decrementQuantity = { decrementQuantity(item) }
                )
            }
        }
    }
}

@Preview
@Composable
fun CartItemCardPreview() {
    val item = CartItem(
        product = Product(
            id = 5,
            title = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
            price = 19.95,
            image = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"
        ),
        quantity = 3
    )

    SuperTiendaTheme {
        Surface {
            CartItemCard(
                item = item,
                incrementQuantity = {},
                decrementQuantity = {},
                removeItem = {}
            )
        }
    }
}