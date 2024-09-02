package co.ke.imbank.moneyswift.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import co.ke.imbank.moneyswift.ui.component.BottomAppNavigation
import co.ke.imbank.moneyswift.ui.navigation.Navigation

@Composable
fun App(
    activity: ComponentActivity
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppNavigation(navHostController = navController)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Navigation(
            activity = activity,
            navHostController = navController,
            snackbarHostState = snackbarHostState
        )
    }
}
