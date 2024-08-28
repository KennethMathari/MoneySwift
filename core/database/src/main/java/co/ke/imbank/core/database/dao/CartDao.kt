package co.ke.imbank.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import co.ke.imbank.core.database.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getCartList(): Flow<List<CartEntity>>

    @Upsert
    fun addCartItem(cartItem: CartEntity)

    @Delete
    fun deleteCartItem(cartItem: CartEntity)
}