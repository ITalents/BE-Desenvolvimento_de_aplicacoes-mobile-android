package br.com.italents.deliveryapp.data.repositories

import br.com.italents.deliveryapp.data.local.datasource.OrderLocalDataSource
import br.com.italents.deliveryapp.data.local.entity.AddressEntity
import br.com.italents.deliveryapp.data.local.entity.OrderEntity
import javax.inject.Inject

class OrderRepository @Inject constructor(private val orderLocalDataSource: OrderLocalDataSource) {

    suspend fun insertNewOrder(totalValue: String) {
        orderLocalDataSource.insertNewOrder(totalValue)
    }

    suspend fun getAllOrders(): List<OrderEntity> {
        return orderLocalDataSource.getAllOrders()
    }
}
