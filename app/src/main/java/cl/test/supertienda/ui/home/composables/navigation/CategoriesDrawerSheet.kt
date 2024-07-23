package cl.test.supertienda.ui.home.composables.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import cl.test.supertienda.R
import kotlinx.coroutines.launch

@Composable
fun CategoriesDrawerSheet(
    drawerState: DrawerState,
    categories: List<String>,
    selectedCategory: String,
    setSelectedCategory: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    var selectedItem by remember {
        mutableStateOf(selectedCategory)
    }

    ModalDrawerSheet {
        Text(text = stringResource(R.string.app_name))
        Text(text = stringResource(R.string.navigation_drawer_categories))
        HorizontalDivider()

        categories.forEach { category ->
            val selected = selectedItem == category

            NavigationDrawerItem(
                label = { Text(text = category) },
                selected = selected,
                onClick = {
                    if (selectedItem != category) {
                        setSelectedCategory(category)
                        selectedItem = category
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    }
}