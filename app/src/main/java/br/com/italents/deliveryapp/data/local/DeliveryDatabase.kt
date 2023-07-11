package br.com.italents.deliveryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.italents.deliveryapp.data.local.dao.AddressDao
import br.com.italents.deliveryapp.data.local.dao.OrderDao
import br.com.italents.deliveryapp.data.local.dao.ProductFavoriteDao
import br.com.italents.deliveryapp.data.local.entity.AddressEntity
import br.com.italents.deliveryapp.data.local.entity.OrderEntity
import br.com.italents.deliveryapp.data.local.entity.ProductFavoriteEntity
import br.com.italents.deliveryapp.data.util.Converter

@Database(
    entities = [ProductFavoriteEntity::class, AddressEntity::class, OrderEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class DeliveryDatabase : RoomDatabase() {
    abstract fun productFavoriteDao(): ProductFavoriteDao
    abstract fun addressDao(): AddressDao
    abstract fun orderDao(): OrderDao
}