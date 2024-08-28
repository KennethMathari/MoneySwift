package co.ke.imbank.domain.cart.model

data class CartDomain(
    val id: Int,
    val currencyCode: String,
    val currencySymbol: String,
    val description: String,
    val imageLocation: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: String
)
