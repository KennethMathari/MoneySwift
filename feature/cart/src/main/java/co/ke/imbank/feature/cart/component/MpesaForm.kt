package co.ke.imbank.feature.cart.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.ke.imbank.feature.cart.R
import co.ke.imbank.feature.cart.model.MpesaPaymentPresentation
import co.ke.imbank.feature.cart.viewmodel.CartViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MpesaForm(
    onCancel: ()-> Unit,
    cartViewModel: CartViewModel = koinViewModel()
){

    val cartState by cartViewModel.cartState.collectAsStateWithLifecycle()

    var phoneNumber by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // Full Name
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Phone Number
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        val mpesaPaymentPresentation = MpesaPaymentPresentation(
            fullname = fullName,
            phoneNumber = phoneNumber,
            type = "M-PESA",
            mpesaImage = R.drawable.mpesa
        )

        Button(
            onClick = {
                cartViewModel.mpesaPayment(
                    mpesaPaymentPresentation = mpesaPaymentPresentation,
                    cartList = cartState.cartList ?: emptyList()
                )
                onCancel()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Pay with M-Pesa")
        }
    }

}