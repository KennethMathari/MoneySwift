package co.ke.imbank.data.cart.di

import co.ke.imbank.data.cart.repository.CartRepositoryImpl
import co.ke.imbank.domain.cart.repository.CartRepository
import org.koin.dsl.module

val cartDataModule = module {

    single<CartRepository> {
        CartRepositoryImpl(
            cartDao = get(),
            ioDispatcher = get()
        )
    }
}
