package co.ke.imbank.feature.cart.model

data class MpesaPaymentPresentation(
    val type: String,
    val fullname: String,
    val phoneNumber: String,
    val mpesaImage: Int
)
