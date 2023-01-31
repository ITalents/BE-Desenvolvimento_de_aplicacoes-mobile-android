package br.com.italents.deliveryapp.data.repositories

import br.com.italents.deliveryapp.data.models.Product
import br.com.italents.deliveryapp.data.remote.ProductRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productRemoteDataSource: ProductRemoteDataSource) {

    suspend fun getProducts(): Response<List<Product>> {
       return productRemoteDataSource.getProduct()
    }

    suspend fun createProduct(product: Product): Response<Product> {
        return productRemoteDataSource.createProduct(product)
    }

    suspend fun updateProduct(product: Product): Response<Product> {
        return productRemoteDataSource.updateProduct(product)
    }

    suspend fun deleteProduct(productId: String): Response<Product> {
        return productRemoteDataSource.deleteProduct(productId)
    }
}