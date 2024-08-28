package co.ke.imbank.feature.cart.viewmodel

import androidx.lifecycle.ViewModel
import co.ke.imbank.domain.cart.repository.CartRepository
import co.ke.imbank.feature.cart.model.CartPresentation
import co.ke.imbank.feature.cart.state.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import co.ke.imbank.domain.cart.utils.DatabaseResult
import co.ke.imbank.feature.cart.mapper.toCartDomain
import co.ke.imbank.feature.cart.mapper.toCartPresentation
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
): ViewModel() {

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> get() = _cartState.asStateFlow()

    init {
        getCartItems()
    }

    private fun getCartItems() {
        viewModelScope.launch {
            cartRepository.getCartItems().collect { result ->

                when (result) {
                    is DatabaseResult.Error -> {
                        _cartState.value = CartState(
                            cartList = null, errorMessage = "Unable to Fetch Cart Items!"
                        )
                    }

                    is DatabaseResult.Success -> {
                        _cartState.value = CartState(
                            cartList = result.data.map { it.toCartPresentation() },
                            errorMessage = null
                        )
                    }
                }

            }
        }
    }

    fun deleteCartItem(cartItem: CartPresentation) {
        viewModelScope.launch {
            cartRepository.deleteCartItem(cartItem.toCartDomain())
        }
    }

}