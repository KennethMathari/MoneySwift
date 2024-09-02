package co.ke.imbank.data.cart.mapper

import co.ke.imbank.core.database.entity.CartEntity
import co.ke.imbank.domain.cart.model.CartDomain

fun CartDomain.toCartEntity(): CartEntity {
    return CartEntity(
        id = this.id,
        currencyCode = this.currencyCode,
        currencySymbol = this.currencySymbol,
        description = this.description,
        imageLocation = this.imageLocation,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        status = this.status
    )
}
