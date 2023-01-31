package br.com.italents.deliveryapp.data.repositories

import br.com.italents.deliveryapp.data.models.User
import br.com.italents.deliveryapp.data.remote.UserRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) {

    suspend fun createUser(user: User): Response<User> {
        return userRemoteDataSource.createUser(user)
    }
}