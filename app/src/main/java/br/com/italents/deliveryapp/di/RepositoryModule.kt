package br.com.italents.deliveryapp.di

import br.com.italents.deliveryapp.data.local.datasource.AddressLocalDataSource
import br.com.italents.deliveryapp.data.local.datasource.ProductFavoriteLocalDataSource
import br.com.italents.deliveryapp.data.remote.CartRemoteDataSource
import br.com.italents.deliveryapp.data.remote.LoginRemoteDataSource
import br.com.italents.deliveryapp.data.remote.ProductRemoteDataSource
import br.com.italents.deliveryapp.data.remote.UserRemoteDataSource
import br.com.italents.deliveryapp.data.repositories.AddressRepository
import br.com.italents.deliveryapp.data.repositories.CartRepository
import br.com.italents.deliveryapp.data.repositories.LoginRepository
import br.com.italents.deliveryapp.data.repositories.ProductRepository
import br.com.italents.deliveryapp.data.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesLoginRepository(loginRemoteDataSource: LoginRemoteDataSource): LoginRepository =
        LoginRepository(loginRemoteDataSource)

    @Singleton
    @Provides
    fun providesProductRepository(productRemoteDataSource: ProductRemoteDataSource, productFavoriteLocalDataSource: ProductFavoriteLocalDataSource): ProductRepository =
        ProductRepository(productRemoteDataSource, productFavoriteLocalDataSource)

    @Singleton
    @Provides
    fun providesUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository =
        UserRepository(userRemoteDataSource)

    @Singleton
    @Provides
    fun providesCartRepository(cartRemoteDataSource: CartRemoteDataSource): CartRepository =
        CartRepository(cartRemoteDataSource)

    @Singleton
    @Provides
    fun provideAddressRepository(addressLocalDataSource: AddressLocalDataSource): AddressRepository =
        AddressRepository(addressLocalDataSource)
}