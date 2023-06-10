package br.com.italents.deliveryapp.menu

import android.content.Context
import android.content.Intent
import br.com.italents.deliveryapp.R
import br.com.italents.deliveryapp.ui.CartActivity
import br.com.italents.deliveryapp.ui.FavoriteActivity
import br.com.italents.deliveryapp.ui.HomeActivity
import br.com.italents.deliveryapp.ui.RegisterCustomerActivity
import br.com.italents.deliveryapp.ui.RegisterProductActivity

object Menu {

    fun handleMenu(itemId: Int, context: Context) {
        when (itemId) {
            R.id.menu_register_customer -> {
                context.startActivity(Intent(context, RegisterCustomerActivity::class.java))
            }
            R.id.menu_cart -> {
                context.startActivity(Intent(context, CartActivity::class.java))
            }
            R.id.menu_home -> {
                context.startActivity(Intent(context, HomeActivity::class.java))
            }
            R.id.menu_register_product -> {
                context.startActivity(Intent(context, RegisterProductActivity::class.java))
            }
            R.id.menu_products_favorite -> {
                context.startActivity(Intent(context, FavoriteActivity::class.java))
            }
            else -> {
                context.startActivity(Intent(context, HomeActivity::class.java))
            }
        }
    }
}