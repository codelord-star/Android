package com.example.firstapp.screens.product

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.firstapp.data.ProductViewModel
import com.example.firstapp.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("PRODUCT LIST", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue
                )
            )
        }
    ) { innerpadding ->
        var context = LocalContext.current
        var myprocuctviewmodel = ProductViewModel(navController, context)

        val product = remember { mutableStateOf(Product("", "", "", "", "")) }
        val products = remember { mutableStateListOf<Product>() }
        LaunchedEffect(Unit) {
            myprocuctviewmodel.allProducts(product, products)
        }
        LazyColumn(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(products) { productItem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    //image preview
                    AsyncImage(
                        model = productItem.imageUri,
                        contentDescription = "product",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                    Text(
                        text = productItem.nmae,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "PRICE :KSH.${productItem.price}"
                    )
                    Text(
                        text = productItem.desc
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListpreview(){
    ProductListScreen(rememberNavController())
}