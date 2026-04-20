package com.example.firstapp.screens.dashboard

import android.R
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.data.AuthViewModel
import com.example.firstapp.navigation.ROUTE_ADDPRODUCT
import com.example.firstapp.navigation.ROUTE_LISTPRODUCTS

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dashboard(navController: NavHostController){
    val context= LocalContext.current
    val myauth= AuthViewModel(navController,context)
    Scaffold(
        //top bar
        topBar = {
            TopAppBar(
                title = {Text("first app")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                ),
                actions= {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings,
                            contentDescription = "settings")
                    }
                    IconButton(onClick = {myauth.signout()}) {
                        Icon(Icons.Default.ExitToApp,
                            contentDescription = "sign out")
                    }
                }
            )
        },
        //bottom bar
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Blue,
                contentColor =  Color.White,
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home,
                        contentDescription = "home icon") },
                    label = { Text("HOME",
                        color = Color.White) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person,
                        contentDescription = "person icon") },
                    label = { Text("PROFILE ",
                        color = Color.White) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Settings,
                        contentDescription = "settings icon") },
                    label = { Text("SETTINGS",
                        color = Color.White)}
                )
            }
        }
    ) {
        innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .background(Color.White),
        ) {
            Text("Welcome to my app")

            //User info
            var username by remember { mutableStateOf("Loading") }
            LaunchedEffect(Unit) {
                myauth.getCurrentUserName(){
                    username = it
                }
            }
            Text(text = "Welcome $username",
                color = Color.Black)

            //card
            Card(modifier = Modifier
                .width(200.dp)
                .padding(16.dp)
                .height(150.dp)
                .clickable{navController.navigate(ROUTE_ADDPRODUCT)},
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.Black,
                )
            ) {
                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,) {
                    Text("HELLO")
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "person icon",

                    )
                }
            }
            Button(onClick = {
                navController.navigate(ROUTE_LISTPRODUCTS)
            }) {
                Text("PRODUCT LIST")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Dashboardpreview(){
    Dashboard(rememberNavController())
}