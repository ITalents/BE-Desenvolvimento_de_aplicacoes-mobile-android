package br.com.italents.deliveryapp

import br.com.italents.deliveryapp.data.models.UserLogin
import br.com.italents.deliveryapp.data.models.UserLoginResponse
import br.com.italents.deliveryapp.data.remote.LoginRemoteDataSource
import br.com.italents.deliveryapp.data.repositories.LoginRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class LoginRepositoryTest {
    private val loginRemoteDataSource: LoginRemoteDataSource = mockk()
    private val loginRepository: LoginRepository = LoginRepository(loginRemoteDataSource)
    private val userLogin = UserLogin("username", "password")

    @Before
    fun setup() {
        coEvery { loginRemoteDataSource.makeLogin(userLogin) } returns Response.success(
            UserLoginResponse(
                "email", "token",
                true
            )
        )
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `Quando chamamos makelogin ele nos retorna uma resposta de sucesso`() = runBlocking {
        val result = loginRepository.makeLogin(userLogin)

        assertEquals(result.body()?.admin, true)
        assertEquals(result.body()?.email, "email")
    }
}