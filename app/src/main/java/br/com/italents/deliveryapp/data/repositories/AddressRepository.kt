package br.com.italents.deliveryapp.data.repositories

import br.com.italents.deliveryapp.data.local.datasource.AddressLocalDataSource
import br.com.italents.deliveryapp.data.local.entity.AddressEntity
import br.com.italents.deliveryapp.data.models.User
import retrofit2.Response
import javax.inject.Inject

class AddressRepository @Inject constructor(private val addressLocalDataSource: AddressLocalDataSource) {

    suspend fun insertNewAddress(address: String) {
        addressLocalDataSource.insertNewAddress(address = address)
    }

    suspend fun getAllAddress(): List<AddressEntity> {
        return addressLocalDataSource.getAllAddress()
    }

    suspend fun updateAddressSelected(address: String) {
        addressLocalDataSource.updateAddressSelected(address = address)
    }
}
