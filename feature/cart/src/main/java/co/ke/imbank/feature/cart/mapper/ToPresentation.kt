package co.ke.imbank.feature.cart.mapper

import co.ke.imbank.domain.cart.model.CartDomain
import co.ke.imbank.feature.cart.model.CartPresentation

fun CartDomain.toCartPresentation(): CartPresentation {
    return CartPresentation(
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
