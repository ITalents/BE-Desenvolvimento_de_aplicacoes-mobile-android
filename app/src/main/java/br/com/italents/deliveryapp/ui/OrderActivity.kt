package br.com.italents.deliveryapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.italents.deliveryapp.databinding.ActivityOrderBinding
import br.com.italents.deliveryapp.ui.adapters.OrderAdapter
import br.com.italents.deliveryapp.ui.viewmodels.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var adapter: OrderAdapter
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = OrderAdapter()
        binding.rcOrder.adapter = adapter

        orderViewModel.getAllOrders().observe(this) {
            adapter.setOrders(it)
        }
    }
}