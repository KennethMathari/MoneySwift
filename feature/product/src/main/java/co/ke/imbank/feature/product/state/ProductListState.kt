package co.ke.imbank.feature.product.state

import co.ke.imbank.feature.product.model.ProductPresentation

data class ProductListState(
    val productList: List<ProductPresentation>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)
