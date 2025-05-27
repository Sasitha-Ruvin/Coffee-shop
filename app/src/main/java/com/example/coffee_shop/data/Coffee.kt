package com.example.coffee_shop.data

data class Coffee(
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Float,
    val imageRes: Int, // Changed from String to Int for drawable resources
    val isHot: Boolean
) 