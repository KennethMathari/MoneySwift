package co.ke.imbank.core.network.utils

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(apiCall())
    } catch (e: IOException) {
        e.printStackTrace()
        NetworkResult.NetworkError(e)
    } catch (e: HttpException) {
        e.printStackTrace()
        extractHttpException(e)
    } catch (e: ConnectException) {
        e.printStackTrace()
        NetworkResult.NetworkError(e)
    } catch (e: Exception) {
        e.printStackTrace()
        NetworkResult.NetworkError(e)
    }
}
fun extractHttpException(exception: HttpException) = when (exception.code()) {
    in 400..499 -> {
        // Client errors (4xx)
        exception.printStackTrace()
        NetworkResult.ClientError(exception)
    }

    in 500..599 -> {
        // Server errors (5xx)
        exception.printStackTrace()
        NetworkResult.ServerError(exception)
    }

    else -> {
        exception.printStackTrace()
        NetworkResult.NetworkError(exception)
    }
}
