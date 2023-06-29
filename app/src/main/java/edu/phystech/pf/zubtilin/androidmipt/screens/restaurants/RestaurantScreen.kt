package edu.phystech.pf.zubtilin.androidmipt.screens.restaurants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import edu.phystech.pf.zubtilin.androidmipt.R
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.RemoteRestaurant
import edu.phystech.pf.zubtilin.androidmipt.screens.login.signUp.SignUpEvent
import java.net.URLEncoder

@Composable
fun RestaurantScreen(restaurantViewModel: RestaurantViewModel, navController: NavController) {
    val state by restaurantViewModel.viewState.observeAsState()
    val viewState = state ?: return

    Column(
        modifier = Modifier.padding(10.dp),
    ){
        Text(
            text = stringResource(R.string.restaurant_label),
            style = TextStyle(fontSize = 40.sp)
        )
        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier = Modifier.padding(10.dp)) {
            TextField(
                value = viewState.search,
                onValueChange = {},
                placeholder = {
                    Text(
                        text = stringResource(R.string.restaurant_search_placeholder),
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    placeholderColor = Color(0xFFFF7400),
                    containerColor = Color(0x3FFF9640),
                    focusedLeadingIconColor = Color(0xFFFF7400)
                )
            )
            Image(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "filter",
                modifier = Modifier
                    .size(100.dp)
                    .padding(0.dp, 0.dp, 0.dp, 40.dp)
            )
        }


        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(40.dp)) {
            item { Text(text = stringResource(R.string.popular), fontSize = 40.sp)}
            items(viewState.popularRestaurants) { restaurant ->
                RestaurantImage(restaurant = restaurant, navController)
            }
            item {Text(text = stringResource(R.string.nearest), fontSize = 40.sp)}
            items(viewState.nearestRestaurants) {
                    restaurant ->
                RestaurantImage(restaurant = restaurant, navController)
            }
        }

    }


}

@Composable
fun RestaurantImage(restaurant: RemoteRestaurant, navController: NavController) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .border(BorderStroke(5.dp, SolidColor(Color.Transparent)))
            .shadow(3.dp)
            .padding(15.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = restaurant.image,
                contentDescription = restaurant.name,
                error = painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .size(100.dp)
                    .padding(15.dp)
                    .clickable {
                        navController.navigate(
                            "detail/${restaurant.name}/${
                                URLEncoder.encode(
                                    restaurant.image,
                                    "UTF-8"
                                )
                            }/${restaurant.deliveryTime}"
                        )
                    }
            )
            Text(text = restaurant.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = restaurant.deliveryTime)
        }
    }
}