package br.com.italents.deliveryapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.italents.deliveryapp.data.Resource
import br.com.italents.deliveryapp.data.models.Address
import br.com.italents.deliveryapp.data.models.User
import br.com.italents.deliveryapp.data.util.SharedPreferences
import br.com.italents.deliveryapp.databinding.ActivityRegisterCustomerBinding
import br.com.italents.deliveryapp.ui.viewmodels.RegisterCostumerViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class RegisterCustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterCustomerBinding
    private val registerCostumerViewModel: RegisterCostumerViewModel by viewModels()
    private var isAdmin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterCustomerBinding.inflate(layoutInflater)
        binding.activity = this

        if (SharedPreferences.isAdmin != null && SharedPreferences.isAdmin == true) {
            binding.radioGroup.visibility = View.VISIBLE
        }

        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            it.visibility = View.GONE

            binding.let { registerUser ->
                var user: User? = null

                user = User(
                    registerUser.edtName.text.toString(),
                    registerUser.edtEmail.text.toString(),
                    registerUser.edtPassword.text.toString(),
                    registerUser.edtImagem.text.toString(),
                    isAdmin,
                    listOf(
                        Address(
                            registerUser.edtStreet.text.toString(),
                            registerUser.edtNumberOfStreet.text.toString().toInt(),
                            registerUser.edtComplement.text.toString(),
                            registerUser.edtCep.text.toString()
                        )
                    )
                )

                registerCostumerViewModel.createUser(user).observe(this) { response ->
                    when (response.status) {
                        Resource.Status.LOADING -> {
                            //
                        }
                        Resource.Status.ERROR -> {
                            Toasty.error(this, "Erro ao registrar um usuario", Toast.LENGTH_LONG, true).show();
                        }
                        Resource.Status.SUCCESS -> {
                            Toasty.success(
                                this,
                                "Usuario registrado com sucesso!",
                                Toast.LENGTH_LONG,
                                true
                            ).show();
                            response.data?.let {
                                finish()
                            }
                        }
                    }
                }
            }
        }
    }

    fun onRadioButtonClick(view: View) {
        when (view.id) {
            binding.radioAdmin.id -> {
                isAdmin = true
            }
            binding.radioComum.id -> {
                isAdmin = false
            }
        }
    }
}