package co.ke.imbank.feature.cart.viewmodel.di

import co.ke.imbank.feature.cart.viewmodel.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val cartViewModelModule = module {
    viewModelOf(::CartViewModel)
}
