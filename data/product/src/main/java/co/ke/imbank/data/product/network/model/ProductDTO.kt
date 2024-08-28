package co.ke.imbank.data.product.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    @SerialName("currencyCode")
    val currencyCode: String,
    @SerialName("currencySymbol")
    val currencySymbol: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("imageLocation")
    val imageLocation: String,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Int,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("status")
    val status: String
)
