package br.com.italents.deliveryapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.italents.deliveryapp.data.Resource
import br.com.italents.deliveryapp.data.models.UserLogin
import br.com.italents.deliveryapp.data.models.UserLoginResponse
import br.com.italents.deliveryapp.data.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    fun makeLogin(userLogin: UserLogin): LiveData<Resource<UserLoginResponse>> = liveData {
        emit(Resource.loading())

        try{
            val result = loginRepository.makeLogin(userLogin)

            if (result.isSuccessful && result.body() != null) {
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception no make login ${e.toString()}")
            emit(Resource(Resource.Status.ERROR))
        }
    }
}