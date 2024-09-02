package co.ke.imbank.moneyswift.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.ke.imbank.feature.cart.screen.CartScreen
import co.ke.imbank.feature.cart.screen.CheckoutScreen
import co.ke.imbank.feature.product.screen.ProductListDetailScreen
import co.ke.imbank.moneyswift.ui.navigation.destination.Cart
import co.ke.imbank.moneyswift.ui.navigation.destination.Checkout
import co.ke.imbank.moneyswift.ui.navigation.destination.ProductListDetail

@Composable
fun Navigation(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    activity: ComponentActivity
) {
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navHostController,
        startDestination = ProductListDetail
    ) {
        composable<ProductListDetail> {
            ProductListDetailScreen(
                snackbarHostState = snackbarHostState,
                scope = scope,
                navigateToCartScreen = {
                    navHostController.navigate(Cart)
                }
            )
        }

        composable<Cart> {
            CartScreen(
                navigateToCheckoutScreen = {
                    navHostController.navigate(Checkout)
                },
                activity = activity
            )
        }

        composable<Checkout> {
            CheckoutScreen()
        }
    }
}
