package cl.test.supertienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.test.supertienda.ui.cart.CartViewModel
import cl.test.supertienda.ui.cart.composables.CartScreen
import cl.test.supertienda.ui.home.HomeViewModel
import cl.test.supertienda.ui.home.composables.HomeScreen
import cl.test.supertienda.ui.theme.SuperTiendaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val homeRoute = "home"
            val cartRoute = "cart"

            SuperTiendaTheme {
                NavHost(
                    navController = navController,
                    startDestination = homeRoute
                ) {
                    composable(homeRoute) {
                        HomeScreen(
                            viewModel = homeViewModel,
                            goToCart = {
                                navController.navigate(cartRoute)
                            })
                    }
                    composable(cartRoute) {
                        CartScreen(
                            viewModel = cartViewModel,
                            navigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}