package co.ke.imbank.feature.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ke.imbank.core.network.utils.NetworkResult
import co.ke.imbank.data.product.local.SearchManager
import co.ke.imbank.domain.cart.model.CartDomain
import co.ke.imbank.domain.cart.repository.CartRepository
import co.ke.imbank.domain.product.repository.ProductRepository
import co.ke.imbank.feature.product.mapper.toProductPresentation
import co.ke.imbank.feature.product.state.ProductListState
import co.ke.imbank.feature.product.utils.Constants.PRODUCTLIST_CLIENT_ERRORMESSAGE
import co.ke.imbank.feature.product.utils.Constants.PRODUCTLIST_NETWORK_ERRORMESSAGE
import co.ke.imbank.feature.product.utils.Constants.PRODUCTLIST_SERVER_ERRORMESSAGE
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val searchManager: SearchManager
) : ViewModel() {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState: StateFlow<ProductListState> get() = _productListState.asStateFlow()

    private var searchJob: Job? = null

    init {
        getProductListFromServer()
        fetchProductListFromLocalStorage()
    }

    private fun fetchProductListFromLocalStorage() {
        viewModelScope.launch {
            val productList =
                searchManager.fetchProductListFromLocalStorage().map { it.toProductPresentation() }
            _productListState.value = ProductListState(
                productList = productList, isLoading = false, errorMessage = null
            )

        }
    }

    private fun getProductListFromServer() {
        viewModelScope.launch {
            runBlocking {
                _productListState.value = ProductListState(
                    productList = null, isLoading = true, errorMessage = null
                )

                productRepository.getProductListFromServer().collect { result ->
                    when (result) {
                        is NetworkResult.ClientError -> {
                            updateErrorMessage(PRODUCTLIST_CLIENT_ERRORMESSAGE)
                        }

                        is NetworkResult.NetworkError -> {
                            updateErrorMessage(PRODUCTLIST_NETWORK_ERRORMESSAGE)
                        }

                        is NetworkResult.ServerError -> {
                            updateErrorMessage(PRODUCTLIST_SERVER_ERRORMESSAGE)
                        }

                        is NetworkResult.Success -> {
                            searchManager.addProductList(result.data)
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(errorMessage: String) {
        _productListState.update {
            it.copy(
                productList = null, isLoading = false, errorMessage = errorMessage
            )
        }
    }

    fun addCartItem(cartItem: CartDomain) {
        viewModelScope.launch {
            cartRepository.addCartItem(cartItem)
        }
    }

    fun searchProductList(query: String) {
        _productListState.value = ProductListState(
            searchQuery = query
        )
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            val productList = searchManager.searchProductList(query)

            _productListState.value = ProductListState(
                productList = productList.map { it.toProductPresentation() },
                isLoading = false,
                errorMessage = null,
                searchQuery = query
            )

        }
    }

    override fun onCleared() {
        searchManager.closeSession()
        super.onCleared()
    }

}