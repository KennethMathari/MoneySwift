package co.ke.imbank.feature.cart.model

data class CardPaymentPresentation(
    val type: String,
    val cardNumber: String,
    val cardExpiry: String,
    val cardCVC: String,
    val cardHolderName: String,
    val paymentImage: Int
)
