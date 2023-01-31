package br.com.italents.deliveryapp.data.service

import br.com.italents.deliveryapp.data.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("usuario/create")
    suspend fun createUser(@Body user: User): Response<User>
}