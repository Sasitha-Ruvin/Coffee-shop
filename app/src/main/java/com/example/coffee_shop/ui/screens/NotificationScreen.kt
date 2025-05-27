package com.example.coffee_shop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffee_shop.ui.components.BottomNavigationBar

data class NotificationItem(
    val id: Int,
    val title: String,
    val message: String,
    val time: String,
    val icon: ImageVector,
    val isRead: Boolean = false,
    val type: NotificationType
)

enum class NotificationType {
    ORDER, PROMOTION, GENERAL
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    var selectedNavIndex by remember { mutableIntStateOf(3) } // Notifications is index 3
    
    val notifications = remember {
        listOf(
            NotificationItem(
                id = 1,
                title = "Order Confirmed!",
                message = "Your Cappuccino order has been confirmed and is being prepared.",
                time = "2 min ago",
                icon = Icons.Default.CheckCircle,
                type = NotificationType.ORDER
            ),
            NotificationItem(
                id = 2,
                title = "Special Offer",
                message = "Get 20% off on all Cold Brew coffees today only!",
                time = "30 min ago",
                icon = Icons.Default.LocalOffer,
                type = NotificationType.PROMOTION
            ),
            NotificationItem(
                id = 3,
                title = "Order Ready",
                message = "Your Latte is ready for pickup at counter #3.",
                time = "1 hour ago",
                icon = Icons.Default.Coffee,
                isRead = true,
                type = NotificationType.ORDER
            ),
            NotificationItem(
                id = 4,
                title = "New Menu Item",
                message = "Try our new Pumpkin Spice Latte - available now!",
                time = "2 hours ago",
                icon = Icons.Default.Restaurant,
                type = NotificationType.GENERAL
            ),
            NotificationItem(
                id = 5,
                title = "Loyalty Points",
                message = "You've earned 50 points! 100 more points for a free coffee.",
                time = "3 hours ago",
                icon = Icons.Default.Star,
                isRead = true,
                type = NotificationType.GENERAL
            ),
            NotificationItem(
                id = 6,
                title = "Weekend Special",
                message = "Buy 2 get 1 free on all Frappés this weekend!",
                time = "1 day ago",
                icon = Icons.Default.LocalOffer,
                isRead = true,
                type = NotificationType.PROMOTION
            ),
            NotificationItem(
                id = 7,
                title = "Order Delivered",
                message = "Your Espresso order has been delivered successfully.",
                time = "2 days ago",
                icon = Icons.Default.CheckCircle,
                isRead = true,
                type = NotificationType.ORDER
            )
        )
    }
    
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedNavIndex,
                onItemSelected = { selectedNavIndex = it },
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Text(
                    text = "Notifications",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                
                IconButton(
                    onClick = { /* Mark all as read */ }
                ) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            // Notifications Count
            Text(
                text = "${notifications.count { !it.isRead }} new notifications",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Notifications List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notifications) { notification ->
                    NotificationCard(
                        notification = notification,
                        onClick = { /* Handle notification click */ }
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: NotificationItem,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (!notification.isRead) 
                MaterialTheme.colorScheme.surface 
            else 
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        when (notification.type) {
                            NotificationType.ORDER -> MaterialTheme.colorScheme.tertiary
                            NotificationType.PROMOTION -> MaterialTheme.colorScheme.primary
                            NotificationType.GENERAL -> MaterialTheme.colorScheme.secondary
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = notification.icon,
                    contentDescription = null,
                    tint = when (notification.type) {
                        NotificationType.ORDER -> MaterialTheme.colorScheme.onTertiary
                        NotificationType.PROMOTION -> MaterialTheme.colorScheme.onPrimary
                        NotificationType.GENERAL -> MaterialTheme.colorScheme.onSecondary
                    },
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = notification.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    if (!notification.isRead) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = notification.message,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = notification.time,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
    }
} 