package co.ke.imbank.feature.product.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.ke.imbank.feature.product.component.LoadingScreen
import co.ke.imbank.feature.product.component.ProductDetail
import co.ke.imbank.feature.product.component.ProductList
import co.ke.imbank.feature.product.model.ProductPresentation
import co.ke.imbank.feature.product.viewmodel.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ProductListDetailScreen(
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    navigateToCartScreen: () -> Unit
) {

    val productState by productViewModel.productListState.collectAsStateWithLifecycle()
    val navigator = rememberListDetailPaneScaffoldNavigator<ProductPresentation>()

    Box {
        if (productState.isLoading) {
            LoadingScreen()
        } else {

            BackHandler(navigator.canNavigateBack()) {
                navigator.navigateBack()
            }

            ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                listPane = {
                    AnimatedPane {
                        ProductList(
                            modifier = modifier,
                            productListState = productState,
                            onProductClicked = { productPresentation ->
                                navigator.navigateTo(
                                    ListDetailPaneScaffoldRole.Detail, productPresentation
                                )
                            },
                            productListViewModel = productViewModel
                        )
                    }
                },
                detailPane = {
                    AnimatedPane {
                        navigator.currentDestination?.content?.let { productPresentation ->
                            ProductDetail(modifier = modifier,
                                productPresentation = productPresentation,
                                navigateToCartScreen = navigateToCartScreen,
                                navigateToCheckoutScreen = { product ->
                                    navigator.navigateTo(
                                        ListDetailPaneScaffoldRole.Extra, product
                                    )
                                })
                        }
                    }
                },
                extraPane = {
                    AnimatedPane {
                        navigator.currentDestination?.content?.let { product ->
                            //BuyNow(product = product, modifier = modifier)

                        }
                    }
                })

            LaunchedEffect(productState.errorMessage) {
                scope.launch {
                    productState.errorMessage?.let { errorMessage ->
                        snackbarHostState.showSnackbar(errorMessage)
                    }
                }

            }

        }
    }
}