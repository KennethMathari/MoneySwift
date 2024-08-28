package co.ke.imbank.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey val id: Int,
    val currencyCode: String,
    val currencySymbol: String,
    val description: String,
    val imageLocation: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: String
)
