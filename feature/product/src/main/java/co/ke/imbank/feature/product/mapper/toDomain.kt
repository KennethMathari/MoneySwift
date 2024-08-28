package co.ke.imbank.feature.product.mapper

import co.ke.imbank.domain.cart.model.CartDomain
import co.ke.imbank.feature.product.model.ProductPresentation

fun ProductPresentation.toCartDomain(): CartDomain {
    return CartDomain(
        currencyCode = this.currencyCode,
        currencySymbol = this.currencySymbol,
        description = this.description,
        id = this.id,
        imageLocation = this.imageLocation,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        status = this.status
    )
}