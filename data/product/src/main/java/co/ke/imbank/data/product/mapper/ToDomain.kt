package co.ke.imbank.data.product.mapper

import co.ke.imbank.data.product.local.document.ProductDocument
import co.ke.imbank.data.product.network.model.ProductDTO
import co.ke.imbank.domain.product.model.ProductDomain

fun ProductDTO.toProductDomain(): ProductDomain {
    return ProductDomain(
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

fun ProductDocument.toProductDomain(): ProductDomain {
    return ProductDomain(
        currencyCode = this.currencyCode,
        currencySymbol = this.currencySymbol,
        description = this.description,
        id = this.id.toInt(),
        imageLocation = this.imageLocation,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        status = this.status
    )
}
