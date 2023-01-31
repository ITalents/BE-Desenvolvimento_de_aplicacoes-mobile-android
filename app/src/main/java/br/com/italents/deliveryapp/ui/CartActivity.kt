package br.com.italents.deliveryapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.italents.deliveryapp.R
import br.com.italents.deliveryapp.data.Resource
import br.com.italents.deliveryapp.data.models.CartProduct
import br.com.italents.deliveryapp.data.models.Product
import br.com.italents.deliveryapp.data.util.SharedPreferences
import br.com.italents.deliveryapp.databinding.ActivityCartBinding
import br.com.italents.deliveryapp.menu.Menu
import br.com.italents.deliveryapp.ui.adapters.CartAdapter
import br.com.italents.deliveryapp.ui.interfaces.ProductCart
import br.com.italents.deliveryapp.ui.viewmodels.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class CartActivity : AppCompatActivity(), ProductCart {
    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private val cartViewModel: CartViewModel by viewModels()
    private var product: Product? = null
    private var isNotNullProduct: Boolean = false
    private var existCart: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        handleMenu()

        product = intent.getParcelableExtra("product")

        product?.let {
            it.priceUnit = it.priceUnit * it.quantity
            isNotNullProduct = true
            cartViewModel.setProduct(product!!)
        }

        existCart = SharedPreferences.existCart ?: false

        handleObservers()
    }

    private fun handleObservers() {
        if (existCart) {
            cartViewModel.getCart(SharedPreferences.idCart!!).observe(this) { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        //
                    }
                    Resource.Status.ERROR -> {
                        Toasty.error(this, "Um erro ocorreu ao buscar os produtos do carrinho", Toast.LENGTH_LONG, true).show();
                    }
                    Resource.Status.SUCCESS -> {
                        if (isNotNullProduct) {
                            cartViewModel.setProduct(product!!)
                        } else {
                            response.data?.products?.let {
                                if (it.isEmpty()) {
                                    binding.totalPrice.visibility = View.GONE
                                    binding.textTotalPrice.visibility = View.GONE
                                    binding.buttonFinishPay.visibility = View.GONE
                                    binding.rcProductsCart.visibility = View.GONE
                                    binding.emptyCart.visibility = View.VISIBLE
                                }
                            }
                        }

                        updateCart()
                    }
                }
            }
        } else {
            cartViewModel.createProduct(createProductCart()).observe(this) { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        //
                    }
                    Resource.Status.ERROR -> {
                        Toasty.error(this, "Um erro ocorreu ao criar o carrinho", Toast.LENGTH_LONG, true).show();
                    }
                    Resource.Status.SUCCESS -> {
                        response.data?.let {
                            binding.totalPrice.text = cartViewModel.getTotalPrice().toString()
                            SharedPreferences.existCart = true
                            SharedPreferences.idCart = it.cartId
                            adapter.setItems(cartViewModel.product.value!!)
                        }
                    }
                }
            }
        }
    }

    private fun createProductCart(): CartProduct {
        return CartProduct(
            cartViewModel.product.value as List<Product>,
            cartViewModel.getTotalPrice(),
            10.0
        )

    }

    private fun updateCart() {
        cartViewModel.updateCart(SharedPreferences.idCart!!, createProductCart())
            .observe(this) { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        //
                    }
                    Resource.Status.ERROR -> {
                        Toasty.error(this, "Um erro ocorreu ao atualizar o carrinho", Toast.LENGTH_LONG, true).show();
                    }
                    Resource.Status.SUCCESS -> {
                        response.data?.products?.let {
                            if (it.isEmpty()) {
                                binding.totalPrice.visibility = View.GONE
                                binding.textTotalPrice.visibility = View.GONE
                                binding.buttonFinishPay.visibility = View.GONE
                                binding.rcProductsCart.visibility = View.GONE
                                binding.emptyCart.visibility = View.VISIBLE
                            } else {
                                binding.totalPrice.text = cartViewModel.getTotalPrice().toString()
                            }
                        }

                        adapter.setItems(cartViewModel.product.value!!)
                    }
                }
            }
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter()
        binding.rcProductsCart.adapter = adapter
        adapter.setListener(this)
    }

    private fun handleMenu() {
        val menu = binding.menu.bottomNavigation

        SharedPreferences.isAdmin?.let { admin ->
            if (!admin) {
                binding.menu.bottomNavigation.menu.findItem(R.id.menu_register_product).isVisible =
                    false

                binding.menu.bottomNavigation.menu.findItem(R.id.menu_register_customer).isVisible =
                    false
            }
        }

        menu.selectedItemId = R.id.menu_cart

        menu.setOnNavigationItemSelectedListener {
            Menu.handleMenu(it.itemId, this)
            true
        }
    }

    override fun deleteProduct(product: Product) {
        val items = cartViewModel.deleteProduct(product)
        adapter.setItems(items)
        updateCart()
    }

    override fun incrementProduct(product: Product) {
        val items = cartViewModel.incrementQuantity(product)
        adapter.setItems(items)
        updateCart()
        Toasty.error(this, "Produto deletado com sucesso!", Toast.LENGTH_LONG, true).show();
    }

    override fun decrementProduct(product: Product) {
        val items = cartViewModel.decrementQuantity(product)
        adapter.setItems(items)
        updateCart()
    }
}