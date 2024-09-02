package co.ke.imbank.data.product

import co.ke.imbank.data.product.network.model.ProductDTO
import co.ke.imbank.domain.product.model.ProductDomain
import com.hannesdorfmann.instantiator.instance
import io.mockk.mockk

object TestData {

    val productDomain: ProductDomain = instance()

    val randomString: String = instance()

    val productDTO: ProductDTO = instance()

    val throwable: Throwable = mockk()
}
