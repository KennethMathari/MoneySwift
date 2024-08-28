package co.ke.imbank.core.database.di

import androidx.room.Room
import co.ke.imbank.core.database.MoneySwiftDatabase
import co.ke.imbank.core.database.dao.CartDao
import org.koin.dsl.module

val databaseModule = module {

    single<MoneySwiftDatabase> {
        Room.databaseBuilder(
            get(), MoneySwiftDatabase::class.java,"moneyswift_db"
        ).build()
    }

    single<CartDao> {
        val database = get<MoneySwiftDatabase>()
        database.cartDao()
    }
}