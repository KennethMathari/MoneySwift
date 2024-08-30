package co.ke.imbank.moneyswift


import co.ke.imbank.feature.cart.StripeManager
import org.koin.dsl.module

val appModule = module {

    single<StripeManager> {
        MainActivity()
    }

}