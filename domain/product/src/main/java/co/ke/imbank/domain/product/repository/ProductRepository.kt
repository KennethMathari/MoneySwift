package co.ke.imbank.domain.product.repository

import co.ke.imbank.core.network.utils.NetworkResult
import co.ke.imbank.domain.product.model.ProductDomain
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductListFromServer(): Flow<NetworkResult<List<ProductDomain>>>
}
