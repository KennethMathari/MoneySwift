package co.ke.imbank.data.product.di

import co.ke.imbank.data.product.network.service.ProductService
import co.ke.imbank.data.product.repository.ProductRepositoryImpl
import co.ke.imbank.domain.product.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val productDataModule = module {
    single<ProductService> {
        val retrofit = get<Retrofit>()
        retrofit.create()
    }

    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            productService = get(), ioDispatcher = get()
        )
    }

    single<CoroutineScope> {
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

}