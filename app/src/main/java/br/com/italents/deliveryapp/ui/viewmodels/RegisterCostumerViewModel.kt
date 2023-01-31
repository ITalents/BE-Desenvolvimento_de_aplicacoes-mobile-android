package br.com.italents.deliveryapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.italents.deliveryapp.data.Resource
import br.com.italents.deliveryapp.data.models.User
import br.com.italents.deliveryapp.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterCostumerViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun createUser(user: User): LiveData<Resource<User>> = liveData {
        emit(Resource.loading())

        try{
            val result = userRepository.createUser(user)

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