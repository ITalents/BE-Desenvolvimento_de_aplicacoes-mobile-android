package br.com.italents.deliveryapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import br.com.italents.deliveryapp.data.Resource
import br.com.italents.deliveryapp.data.local.entity.AddressEntity
import br.com.italents.deliveryapp.data.models.Address
import br.com.italents.deliveryapp.data.models.Product
import br.com.italents.deliveryapp.data.repositories.AddressRepository
import br.com.italents.deliveryapp.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val addressRepository: AddressRepository
) : ViewModel() {
    fun getProducts(): LiveData<Resource<List<Product>>> = liveData {
        emit(Resource.loading())

        try {
            val result = productRepository.getProducts()
            val productsFavorite = productRepository.getAllProducts()

            if (result.isSuccessful && result.body() != null) {
                result.body()?.forEach { product ->
                    productsFavorite.forEach { productFavorite ->
                        product.isFavorite = productFavorite.productId == product.productId
                    }
                }
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception no make login ${e.toString()}")
            emit(Resource(Resource.Status.ERROR))
        }
    }

    fun insertAddress(address: String) {
        viewModelScope.launch {
            addressRepository.insertNewAddress(address)
        }
    }

    fun getAllAddress(): LiveData<Resource<List<AddressEntity>>> = liveData {
        emit(Resource.loading())

        try {
            val result = addressRepository.getAllAddress()
            emit(Resource(Resource.Status.SUCCESS, result))
        } catch (e: Exception) {
            emit(Resource(Resource.Status.ERROR))
        }
    }

    fun updateAddressSelection(address: String) {
        viewModelScope.launch {
            addressRepository.updateAddressSelected(address)
        }
    }
}