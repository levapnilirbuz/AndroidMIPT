package edu.phystech.pf.zubtilin.androidmipt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.phystech.pf.zubtilin.androidmipt.screens.login.signIn.SignInScreen
import edu.phystech.pf.zubtilin.androidmipt.screens.login.signUp.SignUpScreen
import edu.phystech.pf.zubtilin.androidmipt.ui.theme.AndroidMiptTheme
import edu.phystech.pf.zubtilin.androidmipt.screens.restaurants.RestaurantScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.phystech.pf.zubtilin.androidmipt.screens.login.Logo
import edu.phystech.pf.zubtilin.androidmipt.screens.restaurants.RestaurantViewModel
import edu.phystech.pf.zubtilin.androidmipt.screens.restaurants.detail.DetailScreen
import edu.phystech.pf.zubtilin.androidmipt.screens.login.signIn.SignInScreenViewModel
import edu.phystech.pf.zubtilin.androidmipt.screens.login.signUp.SignUpScreenViewModel
import java.net.URLDecoder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            AndroidMiptTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "sign_up") {
                        composable("sign_in") {
                            val signInViewModel = hiltViewModel<SignInScreenViewModel>()
                            SignInScreen(
                                signInScreenViewModel = signInViewModel,
                                navController = navController
                            )
                        }
                        composable("sign_up") {
                            val signUpViewModel = hiltViewModel<SignUpScreenViewModel>()
                            SignUpScreen(
                                signUpScreenViewModel = signUpViewModel,
                                navController = navController
                            )
                        }
                        composable("restaurants") {
                            val restaurantViewModel = hiltViewModel<RestaurantViewModel>()
                            RestaurantScreen(
                                restaurantViewModel = restaurantViewModel,
                                navController = navController
                            )
                        }
                        composable(
                            "detail/{name}/{image}/{deliveryTime}",
                            arguments = listOf(navArgument("name") {type = NavType.StringType},
                                navArgument("image") {type = NavType.StringType},
                                navArgument("deliveryTime") {type = NavType.StringType}
                            )
                        ) { backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name")
                            val image = URLDecoder.decode(backStackEntry.arguments?.getString("image"), "UTF-8")
                            val deliveryTime = backStackEntry.arguments?.getString("deliveryTime")
                            DetailScreen(name.orEmpty(), image.orEmpty(), deliveryTime.orEmpty())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidMiptTheme {
        Logo()
    }
}