package com.example.aplikasialatpertanian

data class Product(
    // Properti ini bisa diisi null saat membuat objek baru sebelum ID didapat dari Firestore
    var id: String? = null,
    val name: String = "",
    val price: Double = 0.0,
    val stock: Int = 0,
    val description: String = ""
)