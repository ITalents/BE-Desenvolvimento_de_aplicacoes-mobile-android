package br.com.italents.deliveryapp.di

import br.com.italents.deliveryapp.data.local.dao.AddressDao
import br.com.italents.deliveryapp.data.local.dao.ProductFavoriteDao
import br.com.italents.deliveryapp.data.local.datasource.AddressLocalDataSource
import br.com.italents.deliveryapp.data.local.datasource.ProductFavoriteLocalDataSource
import br.com.italents.deliveryapp.data.remote.LoginRemoteDataSource
import br.com.italents.deliveryapp.data.service.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideProductFavoriteLocalDataSource(productFavoriteDao: ProductFavoriteDao): ProductFavoriteLocalDataSource =
        ProductFavoriteLocalDataSource(productFavoriteDao)

    @Singleton
    @Provides
    fun provideAddressLocalDataSource(addressDao: AddressDao): AddressLocalDataSource =
        AddressLocalDataSource(addressDao)
}


