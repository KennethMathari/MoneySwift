package co.ke.imbank.data.cart.mapper

import co.ke.imbank.data.cart.TestData.cartEntity
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ToDomainTest {

    @Test
    fun toCartDomainReturnsCartDomain(){

        val cartDomain = cartEntity.toCartDomain()

        assertEquals(cartEntity.id, cartDomain.id)
    }
}