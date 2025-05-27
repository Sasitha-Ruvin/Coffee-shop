package com.example.coffee_shop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.coffee_shop.data.CoffeeRepository
import com.example.coffee_shop.ui.screens.CartScreen
import com.example.coffee_shop.ui.screens.CheckoutScreen
import com.example.coffee_shop.ui.screens.FavoriteScreen
import com.example.coffee_shop.ui.screens.HomeScreen
import com.example.coffee_shop.ui.screens.LoginScreen
import com.example.coffee_shop.ui.screens.NotificationScreen
import com.example.coffee_shop.ui.screens.ProductDetailScreen
import com.example.coffee_shop.ui.screens.ProfileScreen
import com.example.coffee_shop.ui.screens.SignUpScreen
import com.example.coffee_shop.viewmodels.AuthViewModel


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,authViewModel: AuthViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("signup"){
            SignUpScreen(navController,authViewModel)
        }
        composable("home"){
            HomeScreen(navController,authViewModel)
        }
        composable("cart"){
            CartScreen(navController)
        }
        composable("checkout"){
            CheckoutScreen(navController)
        }
        composable("product_detail/{coffeeId}") { backStackEntry ->
            val coffeeId = backStackEntry.arguments?.getString("coffeeId")?.toIntOrNull() ?: 1
            val allCoffees = CoffeeRepository.getHotCoffee() + CoffeeRepository.getColdCoffee() + CoffeeRepository.getFeaturedCoffee()
            val coffee = allCoffees.find { it.id == coffeeId } ?: allCoffees.first()
            ProductDetailScreen(navController, coffee)
        }
        composable("favorites"){
            FavoriteScreen(navController)
        }
        composable("profile"){
            ProfileScreen(navController,authViewModel)
        }
        composable("notifications"){
            NotificationScreen(navController)
        }
    }
} 