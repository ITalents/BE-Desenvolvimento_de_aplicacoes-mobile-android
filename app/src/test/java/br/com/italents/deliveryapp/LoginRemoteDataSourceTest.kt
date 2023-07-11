package br.com.italents.deliveryapp

import br.com.italents.deliveryapp.data.models.UserLogin
import br.com.italents.deliveryapp.data.models.UserLoginResponse
import br.com.italents.deliveryapp.data.remote.LoginRemoteDataSource
import br.com.italents.deliveryapp.data.service.LoginService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class LoginRemoteDataSourceTest {
    private val loginService: LoginService = mockk()
    private val loginRemoteDataSource = LoginRemoteDataSource(loginService)
    private val userLogin = UserLogin("username", "password")

    @Test
    fun `Quando chamamos makelogin ele nos retorna uma resposta de sucesso`() = runBlocking {
        coEvery { loginService.makeLogin(userLogin) } returns Response.success(
            UserLoginResponse(
                "email", "token",
                true
            )
        )

        val result = loginRemoteDataSource.makeLogin(userLogin)

        Assert.assertEquals(result.body()?.admin, true)
        Assert.assertEquals(result.body()?.email, "email")
    }
}