package com.example.coffee_shop.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(
    selectedIndex: Int = 0,
    onItemSelected: (Int) -> Unit = {},
    navController: NavController? = null
) {
    val navigationItems = listOf(
        NavigationItem(Icons.Default.Home, "Home", "home"),
        NavigationItem(Icons.Default.ShoppingCart, "Cart", "cart"),
        NavigationItem(Icons.Default.Favorite, "Favorites", "favorites"),
        NavigationItem(Icons.Default.Notifications, "Notifications", "notifications"),
        NavigationItem(Icons.Default.Person, "Profile", "profile")
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationItems.forEachIndexed { index, item ->
                IconButton(
                    onClick = { 
                        onItemSelected(index)
                        navController?.navigate(item.route)
                    },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (selectedIndex == index) Color(0xFFD2691E) else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

data class NavigationItem(
    val icon: ImageVector,
    val label: String,
    val route: String
) 