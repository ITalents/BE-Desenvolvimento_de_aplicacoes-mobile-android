package br.com.italents.deliveryapp.data.repositories

import br.com.italents.deliveryapp.data.models.UserLogin
import br.com.italents.deliveryapp.data.models.UserLoginResponse
import br.com.italents.deliveryapp.data.remote.LoginRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginRemoteDataSource: LoginRemoteDataSource) {

    suspend fun makeLogin(userLogin: UserLogin): Response<UserLoginResponse> {
        return loginRemoteDataSource.makeLogin(userLogin)
    }
}