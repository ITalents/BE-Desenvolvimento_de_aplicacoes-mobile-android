package br.com.italents.deliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.italents.deliveryapp.R
import br.com.italents.deliveryapp.data.Resource
import br.com.italents.deliveryapp.data.models.Product
import br.com.italents.deliveryapp.data.util.SharedPreferences
import br.com.italents.deliveryapp.databinding.ActivityHomeBinding
import br.com.italents.deliveryapp.menu.Menu
import br.com.italents.deliveryapp.ui.adapters.ProductAdapter
import br.com.italents.deliveryapp.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), ProductAdapter.ProductHome {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: ProductAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleMenu()
        setupRecyclerView()

        homeViewModel.getProducts().observe(this) { response ->
            when(response.status) {
                Resource.Status.LOADING -> {
                    //
                }
                Resource.Status.ERROR -> {
                    Toasty.error(this, "Um erro ocorreu ao buscar os produtos", Toast.LENGTH_LONG, true).show();
                }
                Resource.Status.SUCCESS -> {
                    response.data?.let {
                        adapter.setProducts(it)
                    }
                }
            }
        }

        binding.nameDelivery.setOnClickListener {
            SharedPreferences.isAdmin = false
            SharedPreferences.token = ""
            finish()
        }

    }


    private fun setupRecyclerView() {
        adapter = ProductAdapter()
        adapter.setListener(this)
        binding.rcListProduct.adapter = adapter
    }

    private fun handleMenu() {
        val menu = binding.menu.bottomNavigation
        menu.selectedItemId = R.id.menu_home

        SharedPreferences.isAdmin?.let { admin ->
            if (!admin) {
                binding.menu.bottomNavigation.menu.findItem(R.id.menu_register_product).isVisible =
                    false

                binding.menu.bottomNavigation.menu.findItem(R.id.menu_register_customer).isVisible =
                    false
            }
        }

        menu.setOnNavigationItemSelectedListener {
            Menu.handleMenu(it.itemId, this)
            true
        }
    }

    override fun openProduct(product: Product) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("product", product)

        startActivity(intent)
    }
}



