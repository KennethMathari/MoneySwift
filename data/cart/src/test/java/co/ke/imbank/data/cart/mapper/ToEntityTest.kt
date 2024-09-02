package co.ke.imbank.data.cart.mapper

import co.ke.imbank.data.cart.TestData.cartDomain
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ToEntityTest {

    @Test
    fun toCartEntityReturnsCartEntity() {
        val cartEntity = cartDomain.toCartEntity()

        assertEquals(cartDomain.id, cartEntity.id)
    }
}
