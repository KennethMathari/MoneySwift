package co.ke.imbank.domain.product.model

data class ProductDomain(
    val currencyCode: String,
    val currencySymbol: String,
    val description: String,
    val id: Int,
    val imageLocation: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: String
)
