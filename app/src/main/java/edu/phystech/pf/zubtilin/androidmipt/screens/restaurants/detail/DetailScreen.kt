package edu.phystech.pf.zubtilin.androidmipt.screens.restaurants.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import edu.phystech.pf.zubtilin.androidmipt.R

@Composable
fun DetailScreen(name: String, image: String, deliveryTime: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = image,
                contentDescription = name,
                error = painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .size(300.dp)
                    .padding(20.dp)
            )
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 50.sp)
            Text(text = deliveryTime, fontWeight = FontWeight.Bold, fontSize = 50.sp)
        }
    }
}