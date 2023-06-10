package br.com.italents.deliveryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.italents.deliveryapp.data.local.dao.AddressDao
import br.com.italents.deliveryapp.data.local.dao.ProductFavoriteDao
import br.com.italents.deliveryapp.data.local.entity.AddressEntity
import br.com.italents.deliveryapp.data.local.entity.ProductFavoriteEntity

@Database(entities = [ProductFavoriteEntity::class, AddressEntity::class], version = 3, exportSchema = false)
abstract class DeliveryDatabase : RoomDatabase() {
    abstract fun productFavoriteDao(): ProductFavoriteDao
    abstract fun addressDao(): AddressDao
}