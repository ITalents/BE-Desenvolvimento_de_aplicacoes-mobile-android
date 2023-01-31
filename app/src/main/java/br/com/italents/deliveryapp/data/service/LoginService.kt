package br.com.italents.deliveryapp.data.service

import br.com.italents.deliveryapp.data.models.UserLogin
import br.com.italents.deliveryapp.data.models.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("auth/login")
    suspend fun makeLogin(@Body userLogin: UserLogin): Response<UserLoginResponse>

}