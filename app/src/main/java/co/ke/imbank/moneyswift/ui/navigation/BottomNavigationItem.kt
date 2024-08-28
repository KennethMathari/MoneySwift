package co.ke.imbank.moneyswift.ui.navigation

import kotlinx.serialization.Serializable


@Serializable
data class BottomNavigationItem<T>(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasUpdate: Boolean,
    val badgeCount: Int? = null,
    val route: T
)