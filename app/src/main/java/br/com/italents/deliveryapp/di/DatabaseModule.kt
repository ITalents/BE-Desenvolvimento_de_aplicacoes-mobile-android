package br.com.italents.deliveryapp.di

import android.content.Context
import androidx.room.Room
import br.com.italents.deliveryapp.data.local.DeliveryDatabase
import br.com.italents.deliveryapp.data.local.dao.AddressDao
import br.com.italents.deliveryapp.data.local.dao.OrderDao
import br.com.italents.deliveryapp.data.local.dao.ProductFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): DeliveryDatabase {
        return Room.databaseBuilder(context, DeliveryDatabase::class.java, "productFavorite.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductFavoriteDao(database: DeliveryDatabase): ProductFavoriteDao =
        database.productFavoriteDao()

    @Provides
    fun provideAddressDao(database: DeliveryDatabase): AddressDao =
        database.addressDao()

    @Provides
    fun provideOrderDao(database: DeliveryDatabase): OrderDao =
        database.orderDao()
}