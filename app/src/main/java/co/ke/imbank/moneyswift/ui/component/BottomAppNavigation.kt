package co.ke.imbank.moneyswift.ui.component

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import co.ke.imbank.feature.cart.viewmodel.CartViewModel
import co.ke.imbank.moneyswift.R
import co.ke.imbank.moneyswift.ui.navigation.BottomNavigationItem
import co.ke.imbank.moneyswift.ui.navigation.destination.Cart
import co.ke.imbank.moneyswift.ui.navigation.destination.ProductListDetail
import co.ke.imbank.moneyswift.ui.navigation.destination.Settings
import org.koin.androidx.compose.koinViewModel

@Composable
fun BottomAppNavigation(
    navHostController: NavHostController,
    cartViewModel: CartViewModel = koinViewModel()
) {
    val cartState by cartViewModel.cartState.collectAsStateWithLifecycle()

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val bottomNavItems = listOf(
        BottomNavigationItem(
            title = "Products",
            selectedIcon = R.drawable.filled_list,
            unselectedIcon = R.drawable.outlined_list,
            hasUpdate = false,
            badgeCount = null,
            route = ProductListDetail
        ),
        BottomNavigationItem(
            title = "Cart",
            selectedIcon = R.drawable.filled_cart,
            unselectedIcon = R.drawable.outlined_cart,
            hasUpdate = false,
            badgeCount = cartState.cartList?.size,
            route = Cart
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = R.drawable.filled_settings,
            unselectedIcon = R.drawable.outlined_settings,
            hasUpdate = true,
            badgeCount = null,
            route = Settings
        )
    )

    NavigationBar {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(label = {
                Text(text = item.title)
            }, selected = selectedItemIndex == index, onClick = {
                    selectedItemIndex = index
                    navHostController.navigate(item.route)
                }, icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount != null && item.badgeCount != 0) {
                            Badge {
                                Text(text = item.badgeCount.toString())
                            }
                        } else if (item.hasUpdate) {
                            Badge()
                        }
                    }) {
                        Icon(
                            painter = if (index == selectedItemIndex) {
                                painterResource(id = item.selectedIcon)
                            } else {
                                painterResource(id = item.unselectedIcon)
                            },
                            contentDescription = item.title
                        )
                    }
                })
        }
    }
}
