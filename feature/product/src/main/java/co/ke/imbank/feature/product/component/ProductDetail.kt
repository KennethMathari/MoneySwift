package co.ke.imbank.feature.product.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import co.ke.imbank.domain.cart.model.CartDomain
import co.ke.imbank.feature.product.mapper.toCartDomain
import co.ke.imbank.feature.product.model.ProductPresentation
import co.ke.imbank.feature.product.viewmodel.ProductViewModel
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetail(
    productPresentation: ProductPresentation,
    modifier: Modifier,
    productViewModel: ProductViewModel = koinViewModel(),
    navigateToCartScreen: () -> Unit,
    navigateToCheckoutScreen:(ProductPresentation)-> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = productPresentation.imageLocation,
            contentDescription = "${productPresentation.name} image",
            modifier = modifier
                .size(100.dp)
                .padding(start = 8.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = productPresentation.name)
        Text(text = "${productPresentation.currencyCode} ${productPresentation.price}")
        Text(text = productPresentation.description)

        Button(onClick = {
            productViewModel.addCartItem(productPresentation.toCartDomain())
            navigateToCartScreen()
        }) {
            Text(text = "Add to Cart")
        }

        Button(onClick = {
            navigateToCheckoutScreen(productPresentation)
        }) {
            Text(text = "Buy Now")
        }
    }

}