package com.example.coffee_shop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffee_shop.R
import kotlinx.coroutines.delay

@Composable
fun AutoSlideshow(
    images: List<String>, // Image resource names
    autoScrollDuration: Long = 3000L // 3 seconds
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    
    // Auto-scroll effect
    LaunchedEffect(currentIndex) {
        delay(autoScrollDuration)
        currentIndex = (currentIndex + 1) % images.size
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Image Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2A2A2A)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                // Image placeholder - you can replace this with actual Image composable when images are added
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF3A3A3A)),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.Text(
                        text = images[currentIndex],
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                

                Image(
                    painter = painterResource(
                        id = when (images[currentIndex]) {
                            "slide1" -> R.drawable.slide1
                            "slide2" -> R.drawable.slide2
                            "slide3" -> R.drawable.slide3
                            else -> R.drawable.slide1
                        }
                    ),
                    contentDescription = "Slide ${currentIndex + 1}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Dots Indicator
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == currentIndex) Color(0xFFD2691E)
                            else Color.Gray.copy(alpha = 0.5f)
                        )
                )
                if (index < images.size - 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
} 