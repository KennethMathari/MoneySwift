package co.ke.imbank.data.product.repository

import co.ke.imbank.core.network.utils.NetworkResult
import co.ke.imbank.core.network.utils.safeApiCall
import co.ke.imbank.data.product.mapper.toProductDomain
import co.ke.imbank.data.product.network.service.ProductService
import co.ke.imbank.domain.product.model.ProductDomain
import co.ke.imbank.domain.product.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductRepositoryImpl(
    private val productService: ProductService,
    private val ioDispatcher: CoroutineDispatcher
): ProductRepository {

    override suspend fun getProductListFromServer(): Flow<NetworkResult<List<ProductDomain>>> {
        return flow {
            val result = safeApiCall {
                productService.getProductList().map { productDTO ->
                    productDTO.toProductDomain()
                }
            }
            emit(result)
        }.flowOn(ioDispatcher)
    }
}