package co.ke.imbank.moneyswift

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.ke.imbank.feature.cart.StripeManager
import co.ke.imbank.moneyswift.ui.App
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

class MainActivity : ComponentActivity(), StripeManager {

    private val paymentSheet by lazy {
        PaymentSheet(
            activity = this,
            createIntentCallback = { _, _ ->
                TODO() // Implement this later
            },
            paymentResultCallback = ::onPaymentSheetResult
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // WindowCompat.setDecorFitsSystemWindows(window, false)

        PaymentConfiguration.init(
            this,
            publishableKey = "pk_test_51Pt4DD00LCNzN9HEPfW4yrjF8L9J2iSgzSueSBp1NP9ycO8IXy" +
                "rSs1qnzyMvoplz5izFzqX2TlU5DKxnWhVKVmYx00U7ntgAo6"
        )

        setContent {
//            MoneySwiftTheme {
            App(
                activity = this
            )
//            }
        }
    }

    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        // implement this later
    }

    private fun handleCheckoutButtonPressed() {
        applicationContext?.let {
            val intentConfig = PaymentSheet.IntentConfiguration(
                mode = PaymentSheet.IntentConfiguration.Mode.Payment(
                    amount = 1099,
                    currency = "usd"
                )
                // Other configuration options...
            )

            paymentSheet.presentWithIntentConfiguration(
                intentConfiguration = intentConfig,
                // Optional configuration - See the "Customize the sheet" section in this guide
                configuration = PaymentSheet.Configuration(
                    merchantDisplayName = "Example Inc."
                )
            )
        } ?: run {
            // Handle the case where applicationContext is null
            Log.e("MainActivity", "Application context is null")
        }
    }

    override fun test() {
        handleCheckoutButtonPressed()
    }
}
