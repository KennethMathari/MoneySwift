package co.ke.imbank.data.product.repository

import co.ke.imbank.core.network.utils.NetworkResult
import co.ke.imbank.data.product.TestData.productDomain
import co.ke.imbank.data.product.TestData.throwable
import co.ke.imbank.domain.product.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ProductRepositoryTest {

    private val productRepository: ProductRepository = mockk()

    @Test
    fun getProductListFromServerReturnsSuccessResult() = runTest {
        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(NetworkResult.Success(listOf(productDomain)))

        val result = productRepository.getProductListFromServer().first()

        assertEquals(NetworkResult.Success(listOf(productDomain)), result)
    }

    @Test
    fun getProductListFromServerReturnsClientError() = runTest {
        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(NetworkResult.ClientError(throwable))

        val result = productRepository.getProductListFromServer().first()

        assertEquals(NetworkResult.ClientError(throwable), result)
    }

    @Test
    fun getProductListFromServerReturnsNetworkError() = runTest {
        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(NetworkResult.NetworkError(throwable))

        val result = productRepository.getProductListFromServer().first()

        assertEquals(NetworkResult.NetworkError(throwable), result)
    }

    @Test
    fun getProductListFromServerReturnsServerError() = runTest {
        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(NetworkResult.ServerError(throwable))

        val result = productRepository.getProductListFromServer().first()

        assertEquals(NetworkResult.ServerError(throwable), result)
    }
}