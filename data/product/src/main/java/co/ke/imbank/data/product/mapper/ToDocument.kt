package co.ke.imbank.data.product.mapper

import co.ke.imbank.data.product.local.document.ProductDocument
import co.ke.imbank.domain.product.model.ProductDomain

fun ProductDomain.toProductDocument(): ProductDocument {
    return ProductDocument(
        namespace = "my_products",
        id = this.id.toString(),
        score = 1,
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
