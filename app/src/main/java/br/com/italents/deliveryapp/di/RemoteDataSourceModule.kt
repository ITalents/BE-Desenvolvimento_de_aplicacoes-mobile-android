package br.com.italents.deliveryapp.di

import br.com.italents.deliveryapp.data.remote.CartRemoteDataSource
import br.com.italents.deliveryapp.data.remote.LoginRemoteDataSource
import br.com.italents.deliveryapp.data.remote.ProductRemoteDataSource
import br.com.italents.deliveryapp.data.remote.UserRemoteDataSource
import br.com.italents.deliveryapp.data.service.CartService
import br.com.italents.deliveryapp.data.service.LoginService
import br.com.italents.deliveryapp.data.service.ProductService
import br.com.italents.deliveryapp.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providesLoginRemoteDataSource(loginService: LoginService): LoginRemoteDataSource =
        LoginRemoteDataSource(loginService)

    @Singleton
    @Provides
    fun provideProductRemoteDataSource(productService: ProductService): ProductRemoteDataSource =
        ProductRemoteDataSource(productService)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: UserService): UserRemoteDataSource =
        UserRemoteDataSource(userService)

    @Singleton
    @Provides
    fun provideCartRemoteDataSource(cartService: CartService): CartRemoteDataSource =
        CartRemoteDataSource(cartService)
}