package co.ke.imbank.data.cart.mapper

import co.ke.imbank.core.database.entity.CartEntity
import co.ke.imbank.domain.cart.model.CartDomain

fun CartEntity.toCartDomain(): CartDomain {
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