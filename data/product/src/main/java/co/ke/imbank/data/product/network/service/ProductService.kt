package co.ke.imbank.data.product.network.service

import co.ke.imbank.data.product.network.model.ProductDTO
import retrofit2.http.GET

interface ProductService {
    @GET("/carry1stdeveloper/mock-product-api/productBundles")
    suspend fun getProductList(): List<ProductDTO>
}