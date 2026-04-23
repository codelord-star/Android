package com.example.firstapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.screens.dashboard.Dashboard
import com.example.firstapp.screens.login.LoginScreen
import com.example.firstapp.screens.product.AddProduct
import com.example.firstapp.screens.product.ProductListScreen
import com.example.firstapp.screens.product.UpdateProductScreen
import com.example.firstapp.screens.profile.ProfileScreen
import com.example.firstapp.screens.register.RegisterScreen
import com.example.firstapp.screens.splashscreen.Splashscreen


//main function called when opening the app
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(), //NavHostController= rememberNavController()
    startDestination: String=ROUTE_SPLASH
){
    NavHost(
        navController=navController,
        modifier=modifier,
        startDestination = startDestination
    ){
        composable (ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable (ROUTE_SPLASH){
            Splashscreen(navController)
        }
        composable (ROUTE_DASHBOARD){
            Dashboard(navController)
        }
        composable (ROUTE_ADDPRODUCT) {
            AddProduct(navController)
        }
        composable (ROUTE_LISTPRODUCTS) {
            ProductListScreen(navController)
        }
        composable (ROUTE_UPDATEPRODUCT + "/{productId}"){ backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")!!
            UpdateProductScreen(navController, productId)
        }
        composable (ROUTE_PROFILE){
            ProfileScreen(navController)
        }
    }
}



