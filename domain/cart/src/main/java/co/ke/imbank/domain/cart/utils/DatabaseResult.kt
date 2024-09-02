package co.ke.imbank.domain.cart.utils

sealed interface DatabaseResult<out D> {
    data class Success<out D>(val data: D) : DatabaseResult<D>

    data class Error(val error: Throwable) : DatabaseResult<Nothing>
}
