package co.ke.imbank.feature.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.ke.imbank.feature.cart.component.AirtelMoneyForm
import co.ke.imbank.feature.cart.component.BitcoinForm
import co.ke.imbank.feature.cart.component.CardForm
import co.ke.imbank.feature.cart.component.MpesaForm
import co.ke.imbank.feature.cart.model.PaymentMethodOption

@Composable
fun PaymentSheetContent(
    onCancel: () -> Unit
) {
    var selectedPaymentMethodOption by remember { mutableStateOf<PaymentMethodOption?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Choose Payment Method",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PaymentMethodOption(paymentMethodsOptionOptions) { paymentMethod ->
            selectedPaymentMethodOption = paymentMethod
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display form based on selected payment method
        selectedPaymentMethodOption?.let {
            when (it.name) {
                "Card" -> CardForm(
                    onCancel = {
                        onCancel()
                    }
                )
                "M-Pesa" -> MpesaForm(
                    onCancel = {
                        onCancel()
                    }
                )
                "Airtel Money" -> AirtelMoneyForm()
                "Bitcoin" -> BitcoinForm()
                "Kidney" -> KidneyForm()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PaymentMethodOption(
    paymentMethodOptions: List<PaymentMethodOption>,
    onPaymentMethodSelected: (PaymentMethodOption) -> Unit
) {
    LazyRow {
        items(paymentMethodOptions) { paymentMethod ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onPaymentMethodSelected(paymentMethod) }
            ) {
                Image(
                    painter = painterResource(id = paymentMethod.image),
                    contentDescription = paymentMethod.name,
                    modifier = Modifier.size(70.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Composable
fun KidneyForm() {
    Text(
        text = "Unfortunately, the Kidney payment method is currently not available!",
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        color = Color.Red
    )
}

val paymentMethodsOptionOptions = listOf(
    PaymentMethodOption(
        name = "Card",
        image = R.drawable.card
    ),

    PaymentMethodOption(
        name = "M-Pesa",
        image = R.drawable.mpesa
    ),

    PaymentMethodOption(
        name = "Airtel Money",
        image = R.drawable.airtelmoney
    ),

    PaymentMethodOption(
        name = "Bitcoin",
        image = R.drawable.bitcoin
    ),

    PaymentMethodOption(
        name = "Kidney",
        image = R.drawable.kidney
    )

)
