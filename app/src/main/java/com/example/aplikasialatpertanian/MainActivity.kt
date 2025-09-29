package com.example.aplikasialatpertanian

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.aplikasialatpertanian.ProductAdapter

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Menyiapkan RecyclerView dari layout XML
        recyclerView = findViewById(R.id.rvProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Membuat Adapter dan menghubungkannya ke RecyclerView
        //    Adapter ini awalnya menggunakan list produk yang masih kosong
        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter

        // 3. Memanggil fungsi untuk mengambil data dari Firestore
        getAllProducts()
    }

    private fun getAllProducts() {
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                // Mengosongkan list terlebih dahulu untuk menghindari data duplikat
                productList.clear()

                // Mengubah setiap dokumen dari Firestore menjadi objek Product
                for (document in result) {
                    val product = document.toObject(Product::class.java)
                    product.id = document.id // Menyimpan ID dokumen
                    productList.add(product)
                }

                // 4. Memberi tahu Adapter bahwa datanya sudah diperbarui
                //    Ini akan memicu RecyclerView untuk menampilkan data baru
                productAdapter.notifyDataSetChanged()

                Log.d("Firestore", "Sukses! Jumlah produk yang diambil: ${productList.size}")
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Gagal mengambil data.", exception)
            }
    }
}