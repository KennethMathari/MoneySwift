package co.ke.imbank.feature.product.viewmodel.di

import co.ke.imbank.feature.product.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val productViewModelModule = module {
    viewModelOf(::ProductViewModel)
}
