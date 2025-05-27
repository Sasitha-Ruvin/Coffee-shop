package com.example.coffee_shop.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class CartItem(
    val coffee: Coffee,
    var quantity: Int = 1
) {
    val totalPrice: Double
        get() = coffee.price * quantity
}

object CartRepository {
    private val _cartItems: SnapshotStateList<CartItem> = mutableStateListOf()
    val cartItems: List<CartItem> = _cartItems
    
    val totalItems: Int
        get() = _cartItems.sumOf { it.quantity }
    
    val totalPrice: Double
        get() = _cartItems.sumOf { it.totalPrice }
    
    fun addToCart(coffee: Coffee) {
        val existingItem = _cartItems.find { it.coffee.id == coffee.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            _cartItems.add(CartItem(coffee))
        }
    }
    
    fun removeFromCart(coffee: Coffee) {
        _cartItems.removeAll { it.coffee.id == coffee.id }
    }
    
    fun updateQuantity(coffee: Coffee, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeFromCart(coffee)
        } else {
            val existingItem = _cartItems.find { it.coffee.id == coffee.id }
            existingItem?.let { it.quantity = newQuantity }
        }
    }
    
    fun clearCart() {
        _cartItems.clear()
    }
    
    fun getCartItemQuantity(coffeeId: Int): Int {
        return _cartItems.find { it.coffee.id == coffeeId }?.quantity ?: 0
    }
} 