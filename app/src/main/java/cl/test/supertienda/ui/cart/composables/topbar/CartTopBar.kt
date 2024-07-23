package cl.test.supertienda.ui.cart.composables.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cl.test.supertienda.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar(navigateBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.cart_bar_title)) },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigation_drawer_back_button_content_description)
                )
            }
        }
    )
}