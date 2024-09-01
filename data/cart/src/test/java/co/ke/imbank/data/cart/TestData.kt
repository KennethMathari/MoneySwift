package co.ke.imbank.data.cart

import co.ke.imbank.core.database.entity.CartEntity
import co.ke.imbank.domain.cart.model.CartDomain
import com.hannesdorfmann.instantiator.instance
import io.mockk.mockk

object TestData {

    val cartDomain: CartDomain = instance()

    val cartEntity: CartEntity = instance()

    val throwable: Throwable = mockk()
}