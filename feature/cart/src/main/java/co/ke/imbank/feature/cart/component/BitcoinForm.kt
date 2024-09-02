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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun BitcoinForm() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Bitcoin Wallet Address
        OutlinedTextField(
            value = "", // Replace with a state variable
            onValueChange = { /* Update the state variable */ },
            label = { Text(text = "Bitcoin Wallet Address") },
            placeholder = { Text(text = "e.g. 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Amount in Bitcoin
        OutlinedTextField(
            value = "", // Replace with a state variable
            onValueChange = { /* Update the state variable */ },
            label = { Text(text = "Amount in BTC") },
            placeholder = { Text(text = "e.g. 0.01 BTC") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Transaction Fee (optional)
        OutlinedTextField(
            value = "", // Replace with a state variable
            onValueChange = { /* Update the state variable */ },
            label = { Text(text = "Transaction Fee (Optional)") },
            placeholder = { Text(text = "e.g. 0.0001 BTC") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Memo (optional)
        OutlinedTextField(
            value = "", // Replace with a state variable
            onValueChange = { /* Update the state variable */ },
            label = { Text(text = "Memo (Optional)") },
            placeholder = { Text(text = "e.g. Payment for invoice #123") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Button
        Button(
            onClick = {
                // Handle the Bitcoin payment confirmation
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Pay With Bitcoin")
        }
    }
}
