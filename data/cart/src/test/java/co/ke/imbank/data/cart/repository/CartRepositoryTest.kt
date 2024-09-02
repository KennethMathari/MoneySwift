package co.ke.imbank.data.cart.repository

import co.ke.imbank.data.cart.TestData.cartDomain
import co.ke.imbank.data.cart.TestData.throwable
import co.ke.imbank.domain.cart.repository.CartRepository
import co.ke.imbank.domain.cart.utils.DatabaseResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CartRepositoryTest {

    private val cartRepository: CartRepository = mockk()

    @Test
    fun getCartItemsReturnsSuccess() = runTest {
        coEvery {
            cartRepository.getCartItems()
        } returns flowOf(DatabaseResult.Success(listOf(cartDomain)))

        val result = cartRepository.getCartItems().first()

        assertEquals(DatabaseResult.Success(listOf(cartDomain)), result)
    }

    @Test
    fun getCartItemsReturnsError() = runTest {
        coEvery {
            cartRepository.getCartItems()
        } returns flowOf(DatabaseResult.Error(throwable))

        val result = cartRepository.getCartItems().first()

        assertEquals(DatabaseResult.Error(throwable), result)
    }

    @Test
    fun addCartItemReturnsUnit() = runTest {
        coEvery {
            cartRepository.addCartItem(cartDomain)
        } returns Unit

        val result = cartRepository.addCartItem(cartDomain)

        assertEquals(Unit, result)
    }

    @Test
    fun deleteCartItemReturnsUnit() = runTest {
        coEvery {
            cartRepository.deleteCartItem(cartDomain)
        } returns Unit

        val result = cartRepository.deleteCartItem(cartDomain)

        assertEquals(Unit, result)
    }
}
