package br.com.italents.deliveryapp.data.remote

import br.com.italents.deliveryapp.data.models.User
import br.com.italents.deliveryapp.data.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRemoteDataSource constructor(private val userService: UserService) {

    suspend fun createUser(user: User): Response<User> {
        var result: Response<User>

        withContext(Dispatchers.IO) {
          result = userService.createUser(user)
        }

        return result
    }
}