package com.example.aplikasialatpertanian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Kelas ini memegang referensi ke view (TextView, dll) di item_product.xml
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvProductName)
        val priceTextView: TextView = itemView.findViewById(R.id.tvProductPrice)
    }

    // Membuat ViewHolder baru (dipanggil oleh RecyclerView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    // Mengikat data dari list ke view di dalam ViewHolder (dipanggil oleh RecyclerView)
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.nameTextView.text = currentItem.name
        holder.priceTextView.text = "Rp ${currentItem.price.toInt()}" // Format harga
    }

    // Mengembalikan jumlah total item dalam list (dipanggil oleh RecyclerView)
    override fun getItemCount() = productList.size
}