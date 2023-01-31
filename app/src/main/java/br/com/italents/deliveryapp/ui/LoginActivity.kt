package br.com.italents.deliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.italents.deliveryapp.data.Resource
import br.com.italents.deliveryapp.data.models.UserLogin
import br.com.italents.deliveryapp.data.util.SharedPreferences
import br.com.italents.deliveryapp.databinding.ActivityLoginBinding
import br.com.italents.deliveryapp.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        onClickListeners()
        setContentView(binding.root)
    }

    private fun onClickListeners() {
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterCustomerActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            loginViewModel.makeLogin(UserLogin(username, password)).observe(this) { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.btnLogin.visibility = View.GONE
                        binding.btnRegister.visibility = View.GONE
                    }
                    Resource.Status.ERROR -> {
                        binding.loading.visibility = View.GONE
                        binding.error.visibility = View.VISIBLE
                        binding.btnLogin.visibility = View.VISIBLE
                        binding.btnRegister.visibility = View.VISIBLE
                    }
                    Resource.Status.SUCCESS -> {
                        binding.loading.visibility = View.GONE
                        binding.btnLogin.visibility = View.VISIBLE

                        Toasty.success(
                            this,
                            "Login efetuado com sucesso!",
                            Toast.LENGTH_LONG,
                            true
                        ).show();

                        response.data?.let { user ->
                            SharedPreferences.initSharedPreferences(this)
                            SharedPreferences.isAdmin = user.admin
                            SharedPreferences.token = user.token
                        }

                        goToHome()
                    }
                }
            }
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}