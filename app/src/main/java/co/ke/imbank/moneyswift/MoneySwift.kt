package co.ke.imbank.moneyswift

import android.app.Application
import co.ke.imbank.core.database.di.databaseModule
import co.ke.imbank.core.network.di.networkModule
import co.ke.imbank.data.cart.di.cartDataModule
import co.ke.imbank.data.product.di.productDataModule
import co.ke.imbank.feature.cart.viewmodel.di.cartViewModelModule
import co.ke.imbank.feature.product.viewmodel.di.productViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoneySwift: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoneySwift)
            modules(
                networkModule,
                productDataModule,
                productViewModelModule,
                databaseModule,
                cartDataModule,
                cartViewModelModule,
                appModule
            )
        }
    }
}