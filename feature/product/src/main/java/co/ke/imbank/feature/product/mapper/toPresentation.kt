package co.ke.imbank.feature.product.mapper

import co.ke.imbank.domain.product.model.ProductDomain
import co.ke.imbank.feature.product.model.ProductPresentation

fun ProductDomain.toProductPresentation(): ProductPresentation {
    return ProductPresentation(
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