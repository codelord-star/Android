package com.example.firstapp.screens.product

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.firstapp.R

/*
create addproduct screen
scaffold top bar
add product text
textfields - name, price, description, image
save product button
 */

@Composable
fun AddProduct(navController: NavHostController){
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ) {
        Text(
            text = "ADD PRODUCT",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            )
        var productname by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var desc by remember { mutableStateOf("") }
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        //imagepicker launcher
        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) {
            uri: Uri? ->
            imageUri=uri
        }

        OutlinedTextField(
            value = productname,
            onValueChange = { productname = it },
            label = {Text("Product Name")},
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = price,
            onValueChange = {price = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {Text("Price")},
            modifier = Modifier.fillMaxWidth(),
            )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = desc,
            onValueChange = {desc = it},
            label = {Text("Description")},
            modifier = Modifier.fillMaxWidth(),
            )
        Spacer(modifier = Modifier.height(20.dp))
        //image picker and preview
        Card(
            modifier = Modifier
                .size(140.dp)
                .clickable{imagePickerLauncher.launch("image/*")},
            shape = CircleShape,
        ) {
            AsyncImage(
                model = imageUri?: R.drawable.logo,
                contentDescription = "product",
                modifier = Modifier.size(140.dp),
                contentScale = ContentScale.Crop,
            )
        }
        OutlinedButton(onClick = {imagePickerLauncher.launch("image/*")}) {
            Text("Choose an image")
        }

        Button(onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
        ) {
            Text("Add Product",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AddProductpreview(){
    AddProduct(rememberNavController())
}