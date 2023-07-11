package br.com.italents.deliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.italents.deliveryapp.data.local.entity.OrderEntity

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewOrder(orderEntity: OrderEntity)

    @Query("SELECT * FROM orders")
    suspend fun allOrders(): List<OrderEntity>
}