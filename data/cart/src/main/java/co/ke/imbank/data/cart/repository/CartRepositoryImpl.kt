package co.ke.imbank.data.cart.repository

import co.ke.imbank.core.database.dao.CartDao
import co.ke.imbank.data.cart.mapper.toCartDomain
import co.ke.imbank.data.cart.mapper.toCartEntity
import co.ke.imbank.domain.cart.model.CartDomain
import co.ke.imbank.domain.cart.repository.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val cartDao: CartDao, private val ioDispatcher: CoroutineDispatcher
) : CartRepository {
    override suspend fun getCartItems(): Flow<Result<List<CartDomain>>> {
        return flow {
            runCatching {
                cartDao.getCartList().map { cartEntityList ->
                    cartEntityList.map { cartEntity ->
                        cartEntity.toCartDomain()
                    }
                }
            }.onSuccess { listFlow ->
                listFlow.collect { cartDomainList ->
                    emit(Result.success(cartDomainList))
                }
            }.onFailure { throwable ->
                throwable.printStackTrace()
                //emit(Result.failure<Throwable>(throwable.cause))
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun addCartItem(cartItem: CartDomain) {
        return withContext(ioDispatcher) {
            runCatching {
                val cartItemEntity = cartItem.toCartEntity()
                cartDao.addCartItem(cartItemEntity)
            }.onFailure { throwable ->
                throwable.printStackTrace()
            }
        }
    }

    override suspend fun deleteCartItem(cartItem: CartDomain) {
        return withContext(ioDispatcher) {
            runCatching {
                val cartItemEntity = cartItem.toCartEntity()
                cartDao.deleteCartItem(cartItemEntity)
            }.onFailure { throwable ->
                throwable.printStackTrace()
            }
        }
    }
}