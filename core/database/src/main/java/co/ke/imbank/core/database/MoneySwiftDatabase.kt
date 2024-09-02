package co.ke.imbank.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.ke.imbank.core.database.dao.CartDao
import co.ke.imbank.core.database.entity.CartEntity

@Database(
    entities = [CartEntity::class],
    version = 1,
    exportSchema = false
)

abstract class MoneySwiftDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
