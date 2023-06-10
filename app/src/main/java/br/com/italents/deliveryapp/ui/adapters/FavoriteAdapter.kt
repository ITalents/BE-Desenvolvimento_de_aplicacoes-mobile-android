package br.com.italents.deliveryapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.italents.deliveryapp.R
import br.com.italents.deliveryapp.data.models.ProductFavorite
import com.bumptech.glide.Glide

class FavoriteAdapter :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var productsFavorite: List<ProductFavorite> = listOf()

    fun setProductsFavorites(products: List<ProductFavorite>) {
        this.productsFavorite = products
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageProductFavorite: ImageView
        val nameProductFavorite: TextView
        val priceUnitProductFavorite: TextView

        init {
            imageProductFavorite = view.findViewById(R.id.imageProductFavorite)
            nameProductFavorite = view.findViewById(R.id.nameProductFavorite)
            priceUnitProductFavorite = view.findViewById(R.id.priceUnitProductFavorite)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context

        val view =
            LayoutInflater.from(context).inflate(R.layout.favorite_item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(context).load(productsFavorite[position].image)
            .into(viewHolder.imageProductFavorite)
        viewHolder.nameProductFavorite.text = productsFavorite[position].name
        viewHolder.priceUnitProductFavorite.text = productsFavorite[position].priceUnit.toString()
    }

    override fun getItemCount() = this.productsFavorite.size
}