package co.ke.imbank.domain.cart.repository

import co.ke.imbank.domain.cart.model.CartDomain
import co.ke.imbank.domain.cart.utils.DatabaseResult
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartItems(): Flow<DatabaseResult<List<CartDomain>>>

    suspend fun addCartItem(cartItem: CartDomain)

    suspend fun deleteCartItem(cartItem: CartDomain)
}