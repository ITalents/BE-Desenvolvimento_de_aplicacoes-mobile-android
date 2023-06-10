package br.com.italents.deliveryapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import br.com.italents.deliveryapp.data.Resource
import br.com.italents.deliveryapp.data.models.Product
import br.com.italents.deliveryapp.data.models.ProductFavorite
import br.com.italents.deliveryapp.data.repositories.ProductRepository
import br.com.italents.deliveryapp.databinding.ActivityRegisterProductBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterProductViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private val _product: MutableLiveData<Product> =
        MutableLiveData<Product>()

    val product: LiveData<Product> = _product

    fun createProduct(product: Product): LiveData<Resource<Product>> = liveData {
        emit(Resource.loading())

        try {
            val result = productRepository.createProduct(product)

            if (result.isSuccessful && result.body() != null) {
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception no make login ${e.toString()}")
            emit(Resource(Resource.Status.ERROR))
        }
    }

    fun isValidProduct(binding: ActivityRegisterProductBinding): Boolean {
        if (binding.edtNameProduct.text.toString().isEmpty() ||
            binding.edtDescriptionProduct.text.toString().isEmpty() ||
            binding.edtImage.text.toString().isEmpty() ||
            binding.edtUnitPriceProduct.text.toString().isEmpty() ||
            binding.edtCode.text.toString().isEmpty()
        ) {
            return false
        }

        return true
    }

    fun updateProduct(product: Product): LiveData<Resource<Product>> = liveData {
        emit(Resource.loading())

        try {
            val result = productRepository.updateProduct(product)

            if (result.isSuccessful && result.body() != null) {
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception problem with update product ${e.toString()}")
            emit(Resource(Resource.Status.ERROR))
        }
    }

    fun deleteProduct(productId: String): LiveData<Resource<Product>> = liveData {
        emit(Resource.loading())

        try {
            val result = productRepository.deleteProduct(productId)

            if (result.isSuccessful && result.body() != null) {
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception problem with delete product ${e.toString()}")
            emit(Resource(Resource.Status.ERROR))
        }
    }

    fun incrementQuantity(product: Product) {
        val priceUnitProduct = product.priceUnit / product.quantity

        product.quantity++
        product.priceUnit += priceUnitProduct

        _product.value = product
    }

    fun decrementQuantity(product: Product) {
        val priceUnitProduct = product.priceUnit / product.quantity

        if (product.quantity == 1) {
            product.priceUnit = priceUnitProduct
        } else {
            product.quantity--
            product.priceUnit -= priceUnitProduct
        }

        _product.value = product
    }

    fun setProduct(product: Product) {
        _product.value = product
    }

    fun handleFavorite(favorite: Boolean, product: Product) {
        _product.value = _product.value?.copy(isFavorite = favorite)

        viewModelScope.launch {
            if (favorite) {
                productRepository.insertProductFavorite(product)
            } else {
                productRepository.deleteProductFavorite(product)
            }
        }
    }

    fun getAllProductsFavorite(): LiveData<Resource<List<ProductFavorite>>> = liveData {
        emit(Resource.loading())

        try {
            val result = productRepository.getAllProducts()
            emit(Resource(Resource.Status.SUCCESS, result))
        } catch (e: Exception) {
            Log.i("Exception", "Exception get all products favorite database ${e.toString()}")
            emit(Resource(Resource.Status.ERROR))
        }
    }
}