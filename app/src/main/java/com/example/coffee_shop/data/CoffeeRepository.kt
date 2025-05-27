package com.example.coffee_shop.data

import com.example.coffee_shop.R

object CoffeeRepository {
    fun getCoffeeList(): List<Coffee> {
        return listOf(
            // Hot Coffee
            Coffee(1, "Cappuccino", 10.00, 5.0f, R.drawable.cappuccino, true),
            Coffee(2, "Espresso", 10.00, 5.0f, R.drawable.espresso, true),
            Coffee(3, "Americano", 10.00, 5.0f, R.drawable.americano, true),
            Coffee(4, "Latte", 10.00, 5.0f, R.drawable.latte, true),
            Coffee(5, "Macchiato", 10.00, 5.0f, R.drawable.macchiato, true),
            Coffee(6, "Mocha", 10.00, 5.0f, R.drawable.mocha, true),
            
            // Cold Coffee
            Coffee(7, "Iced Cappuccino", 10.00, 5.0f, R.drawable.icedcappuccino, false),
            Coffee(8, "Iced Latte", 10.00, 5.0f, R.drawable.icedlatte, false),
            Coffee(9, "Cold Brew", 10.00, 5.0f, R.drawable.coldbrew, false),
            Coffee(10, "Frappé", 10.00, 5.0f, R.drawable.frappuccino, false),
            Coffee(11, "Iced Americano", 10.00, 5.0f, R.drawable.icedamericano, false),
            Coffee(12, "Iced Mocha", 10.00, 5.0f, R.drawable.icedmocha, false)
        )
    }
    
    fun getHotCoffee(): List<Coffee> {
        return getCoffeeList().filter { it.isHot }
    }
    
    fun getColdCoffee(): List<Coffee> {
        return getCoffeeList().filter { !it.isHot }
    }
    
    fun getFeaturedCoffee(): List<Coffee> {
        return listOf(
            Coffee(1, "Cappuccino", 10.00, 5.0f, R.drawable.cappuccino, true),
            Coffee(4, "Latte", 10.00, 5.0f, R.drawable.latte, true),
            Coffee(9, "Cold Brew", 10.00, 5.0f, R.drawable.coldbrew, false)
        )
    }
    
    fun getSpecialOffers(): List<SpecialOffer> {
        return listOf(
            SpecialOffer(1, "Buy 2 Get 1 Free", "On all Espresso drinks", "30% OFF", "espresso_offer"),
            SpecialOffer(2, "Happy Hour", "50% off Cold Brew 3-5 PM", "50% OFF", "happy_hour"),
            SpecialOffer(3, "Student Discount", "Show your ID for discount", "20% OFF", "student_offer")
        )
    }
    
    fun getCategories(): List<Category> {
        return listOf(
            Category(1, "Coffee", "coffee_category", 24),
            Category(2, "Tea", "tea_category", 12),
            Category(3, "Pastries", "pastry_category", 18),
            Category(4, "Sandwiches", "sandwich_category", 8)
        )
    }
}

data class SpecialOffer(
    val id: Int,
    val title: String,
    val description: String,
    val discount: String,
    val imageRes: String
)

data class Category(
    val id: Int,
    val name: String,
    val imageRes: String,
    val itemCount: Int
) 