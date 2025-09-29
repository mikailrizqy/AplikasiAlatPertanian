package com.example.aplikasialatpertanian

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // Inisialisasi Firestore
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Panggil fungsi untuk menambahkan produk (contoh)
        // Anda bisa pindahkan ini ke dalam OnClickListener sebuah tombol
        addProduct("Cangkul Baja", 150000.0, 50, "Cangkul super tajam dari baja pilihan.")
    }

    private fun addProduct(name: String, price: Double, stock: Int, description: String) {
        // Buat objek produk baru
        val newProduct = Product(
            name = name,
            price = price,
            stock = stock,
            description = description
        )

        // "products" adalah nama koleksi (seperti tabel di database tradisional)
        db.collection("products")
            .add(newProduct) // Perintah untuk menambahkan data
            .addOnSuccessListener { documentReference ->
                // Jika berhasil
                Log.d("Firestore", "Produk berhasil ditambahkan dengan ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                // Jika gagal
                Log.w("Firestore", "Gagal menambahkan produk", e)
            }
    }
}