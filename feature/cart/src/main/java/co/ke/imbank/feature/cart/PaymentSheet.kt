package co.ke.imbank.feature.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import co.ke.imbank.feature.cart.model.PaymentMethod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSheetContent(
    onCancel: () -> Unit, onConfirm: () -> Unit
) {

    var selectedPaymentMethod by remember { mutableStateOf<PaymentMethod?>(null) }

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

        PaymentMethodOption(paymentMethodsOptions) { paymentMethod ->
            selectedPaymentMethod = paymentMethod
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display form based on selected payment method
        selectedPaymentMethod?.let {
            when (it.name) {
                "Card" -> CardForm()
                "M-Pesa" -> MpesaForm()
                "Airtel Money" -> AirtelMoneyForm()
                "Bitcoin" -> BitcoinForm()
                "Kidney" -> KidneyForm()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

    }


}


@Composable
fun PaymentMethodOption(paymentMethods: List<PaymentMethod>, onPaymentMethodSelected: (PaymentMethod) -> Unit) {
    LazyRow {
        items(paymentMethods) { paymentMethod ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onPaymentMethodSelected(paymentMethod) }
            ) {
                Image(
                    painter = painterResource(id = paymentMethod.image),
                    contentDescription = paymentMethod.name,
                    modifier = Modifier.size(70.dp),
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
    }
}

@Composable
fun KidneyForm() {
        Text(
            text = "Unfortunately, we do not accept Kidney payments! Please try another payment method.",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.Red
        )
}

val paymentMethodsOptions = listOf(
    PaymentMethod(
        name = "Card", image = R.drawable.card
    ),

    PaymentMethod(
        name = "M-Pesa", image = R.drawable.mpesa
    ),

    PaymentMethod(
        name = "Airtel Money", image = R.drawable.airtelmoney
    ),

    PaymentMethod(
        name = "Bitcoin", image = R.drawable.bitcoin
    ),

    PaymentMethod(
        name = "Kidney", image = R.drawable.kidney
    ),

    )