package br.com.italents.deliveryapp.data.local.datasource

import br.com.italents.deliveryapp.data.local.dao.OrderDao
import br.com.italents.deliveryapp.data.local.entity.OrderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderLocalDataSource constructor(private val orderDao: OrderDao) {

    suspend fun insertNewOrder(totalValue: String) {
        val order = OrderEntity(totalValueOrder = totalValue)

        withContext(Dispatchers.IO) {
            orderDao.insertNewOrder(order)
        }
    }

    suspend fun getAllOrders(): List<OrderEntity> {
        var result: List<OrderEntity> = listOf()

        withContext(Dispatchers.IO) {
            result = orderDao.allOrders()
        }

        return result
    }
}