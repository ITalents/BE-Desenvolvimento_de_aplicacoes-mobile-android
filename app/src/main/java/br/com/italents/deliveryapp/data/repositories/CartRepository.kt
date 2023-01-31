package br.com.italents.deliveryapp.data.repositories

import br.com.italents.deliveryapp.data.models.CartProduct
import br.com.italents.deliveryapp.data.models.CartResponse
import br.com.italents.deliveryapp.data.remote.CartRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartRemoteDataSource: CartRemoteDataSource) {

    suspend fun createCart(cartCreate: CartProduct): Response<CartResponse> {
        return cartRemoteDataSource.createCart(cartCreate)
    }

    suspend fun getCart(idCart: String): Response<CartResponse> {
        return cartRemoteDataSource.getCart(idCart)
    }

    suspend fun updateCart(idCart: String, cartProduct: CartProduct): Response<CartResponse> {
        return cartRemoteDataSource.updateCart(idCart, cartProduct)
    }
}