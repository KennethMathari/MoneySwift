package co.ke.imbank.feature.cart.state

import co.ke.imbank.feature.cart.model.CartPresentation


data class CartState(
    val cartList: List<CartPresentation>? = emptyList(),
    val errorMessage: String? = null
)