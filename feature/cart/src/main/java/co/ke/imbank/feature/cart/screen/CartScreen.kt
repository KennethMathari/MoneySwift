package co.ke.imbank.feature.cart.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.ke.imbank.feature.cart.viewmodel.CartViewModel
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel


@Composable
fun CartScreen(
    modifier: Modifier = Modifier, cartViewModel: CartViewModel = koinViewModel()
) {
    val cartState by cartViewModel.cartState.collectAsStateWithLifecycle()

    Box {
        if (cartState.cartList?.isEmpty() == true) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Cart is Empty!")
            }
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
            ) {

                Text(
                    text = "Cart Items",
                    modifier = modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                LazyColumn {
                    items(cartState.cartList ?: emptyList()) { cartItem ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            ), modifier = modifier.fillMaxWidth().padding(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    AsyncImage(
                                        model = cartItem.imageLocation,
                                        contentDescription = "${cartItem.name} image",
                                        modifier = Modifier.size(70.dp).padding(start = 8.dp),
                                        contentScale = ContentScale.Fit
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                                    ) {
                                        Text(
                                            text = cartItem.name,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Row {
                                            Text(
                                                text = cartItem.currencyCode,
                                                modifier = Modifier.padding(end = 3.dp),
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                            Text(
                                                text = cartItem.price.toString(),
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                        }
                                    }
                                }


                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete item",
                                    modifier = Modifier.size(35.dp).clickable(onClick = {
                                            cartViewModel.deleteCartItem(cartItem)
                                        }),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }

                Text(
                    text = "Total: USD ${cartState.cartList?.sumOf { it.price }}",
                    modifier = modifier.align(alignment = Alignment.End).padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )


            }
        }
    }
}